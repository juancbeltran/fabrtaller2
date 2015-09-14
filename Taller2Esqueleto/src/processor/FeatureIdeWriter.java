package processor;

import java.lang.annotation.Annotation;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import annotation.FeatureAnnotation;
import annotation.RelationAnnotation;
import annotation.RelationType;
import annotation.RestrictionAnnotation;
import annotation.Rules;
import featureidejaxb.Alt;
import featureidejaxb.And;
import featureidejaxb.Constraints;
import featureidejaxb.Feature;
import featureidejaxb.FeatureModel;
import featureidejaxb.Imp;
import featureidejaxb.Not;
import featureidejaxb.Or;
import featureidejaxb.Parent;
import featureidejaxb.Rule;
import featureidejaxb.Struct;
import spoon.reflect.declaration.CtAnnotation;

/**
 * Construcción del arbol featureide a partir de una lista de anotaciones
 */
public class FeatureIdeWriter {

	/**
	 * Listas con las diferentes tipos de anotaciones manejadas
	 */
	List<CtAnnotation<FeatureAnnotation>> featureAnnotations;
	
	List<CtAnnotation<RelationAnnotation>> relationAnnotations;
	
	List<CtAnnotation<RestrictionAnnotation>> restrctionAnnotations;
	
	
	public FeatureIdeWriter(List<CtAnnotation<? extends Annotation>> codeAnnotations)
	{
		this.featureAnnotations = codeAnnotations.stream()
				.filter((x) -> x.getAnnotationType().getActualClass() == FeatureAnnotation.class)
				.map((m)-> (CtAnnotation<FeatureAnnotation>)m).collect(Collectors.toList());
		
		this.relationAnnotations = codeAnnotations.stream()
				.filter((x) -> x.getAnnotationType().getActualClass() == RelationAnnotation.class)
				.map((m)-> (CtAnnotation<RelationAnnotation>)m).collect(Collectors.toList());
		
		this.restrctionAnnotations = codeAnnotations.stream()
				.filter((x) -> x.getAnnotationType().getActualClass() == RestrictionAnnotation.class)
				.map((m)-> (CtAnnotation<RestrictionAnnotation>)m).collect(Collectors.toList());		
	}
	
	/**
	 * Adiciona los hijos a un nodo padre
	 */
	private Object AddChildrenToParent(CtAnnotation<FeatureAnnotation> father, boolean isRootNode)
	{
		CtAnnotation<RelationAnnotation> relation = this.FindRelation(father);
		List<CtAnnotation<FeatureAnnotation>> children = this.FindChildren(relation);
		
		if (relation == null || children == null || children.size() == 0)
		{
			Feature childNode = new Feature();
			childNode.setMandatory(father.getActualAnnotation().Mandatory());
			childNode.setName(father.getActualAnnotation().Name());
			return childNode;
		} 
		
		Parent node = this.GetNodeType(relation, isRootNode, father.getActualAnnotation().Mandatory());
		node.setName(father.getActualAnnotation().Name());
	
		for (CtAnnotation<FeatureAnnotation> child : children)
		{
			Object childNode = AddChildrenToParent(child, false);
			node.getAndOrAltOrOr().add(childNode) ;
		}
		
		return node;
	}
	
	/**
	 * Obtiene el tipo de nodo según la relación
	 */
	private Parent GetNodeType(CtAnnotation<RelationAnnotation> relation, boolean isRootNode, boolean mandatory)
	{
		if (isRootNode)
		{
			return new And();
		}
		
		if (!isRootNode && relation.getActualAnnotation().relationType() == RelationType.XOR)
		{
			Or returnOr = new Or();
			returnOr.setMandatory(mandatory);
			return returnOr;
		}
		
		if (!isRootNode && relation.getActualAnnotation().relationType() == RelationType.OR)
		{
			Alt returnAlt = new Alt();
			returnAlt.setMandatory(mandatory);
			return returnAlt;
		}
		
		And returnAnd = new And();
		returnAnd.setMandatory(mandatory);
		return returnAnd;
	}
	
	/**
	 * Genera y escribe el archivo para featureide según la definición de anotaciones
	 */
	public void WriteFeatureIdeFile(String xmlOutputFile) 
	{
		CtAnnotation<FeatureAnnotation> root = this.FindRootNode();
		
		if (root == null)
		{
			System.err.println("Nodo raiz no fue encontrado, el proceos no puede continuar");
			return;
		}
		
		FeatureModel featureModel = new FeatureModel();
		featureModel.setChosenLayoutAlgorithm(new BigInteger("1"));

		Struct featureStruct = new Struct();		
		featureModel.setStruct(featureStruct);
		
		Object rootNode = AddChildrenToParent(root, true);
		featureStruct.setAnd((And)rootNode);
		this.AddRestrictions(featureModel);
	
		JaxbWriterReader.jaxbWriterNoSchema(featureModel, xmlOutputFile);
	}
	
	/**
	 * Adiciona las restricciones al modelo
	 */	
	private void AddRestrictions(FeatureModel featureModel)
	{
		Constraints constraints = new Constraints();

		for (CtAnnotation<RestrictionAnnotation> restriction : this.restrctionAnnotations)
		{
			constraints.getRule().add(this.CreateRule(restriction));
		}			
		
		featureModel.setConstraints(constraints);
	}
	
	/**
	 * Crea una restricción según la anotación
	 */	
	private Rule CreateRule(CtAnnotation<RestrictionAnnotation> restriction)
	{
		Rule returnValue = new Rule();
		CtAnnotation<FeatureAnnotation> targetNode = this.FindFeatureByName(restriction.getActualAnnotation().target());
		CtAnnotation<FeatureAnnotation> sourceNode = this.FindFeatureBySignature(restriction.getParent().getSignature());
		
		Imp implies = new Imp();
		implies.getVarOrNot().add(sourceNode.getActualAnnotation().Name());		
		
		if (restriction.getActualAnnotation().rules() == Rules.IMPLIES)
		{
			implies.getVarOrNot().add(targetNode.getActualAnnotation().Name());
		}
		else
		{
			Not notImplies = new Not();
			notImplies.setVar(targetNode.getActualAnnotation().Name());
			implies.getVarOrNot().add(notImplies);
		}
		
		returnValue.getImp().add(implies);
		
		return returnValue;
	}
	
	/**
	 * Busca una anotación de tipo CtAnnotation<FeatureAnnotation> por la firma del elemento en donde se encuentra
	 */		
	private CtAnnotation<FeatureAnnotation> FindFeatureBySignature(String parentSignature)
	{
		Optional<CtAnnotation<FeatureAnnotation>> relation = this.featureAnnotations.stream().filter((x)-> x.getParent().getSignature().equals(parentSignature)).findFirst();
		return relation.isPresent() ? relation.get() : null;
	}
	
	/**
	 * Busca una anotación de tipo CtAnnotation<FeatureAnnotation> por el nombre del feature
	 */	
	private CtAnnotation<FeatureAnnotation> FindFeatureByName(String nodeName)
	{
		Optional<CtAnnotation<FeatureAnnotation>> relation = this.featureAnnotations.stream().filter((x)-> x.getActualAnnotation().Name().equals(nodeName)).findFirst();
		return relation.isPresent() ? relation.get() : null;
	}
	
	/**
	 * Busca el nodo raiz del arbol
	 */		
	private CtAnnotation<FeatureAnnotation> FindRootNode()
	{
		for (CtAnnotation<FeatureAnnotation> feature : this.featureAnnotations)
		{
			if (this.relationAnnotations.stream().allMatch((x) -> !	Arrays.asList(x.getActualAnnotation().children()).contains(feature.getActualAnnotation().Name())))
			{
				return feature;
			}
		}

		return null;
	}
	
	/**
	 * Busca la relación en la que está contenida un nodo tipo feature.
	 */		
	private CtAnnotation<RelationAnnotation> FindRelation(CtAnnotation<FeatureAnnotation> feature)
	{
		Optional<CtAnnotation<RelationAnnotation>> relation = this.relationAnnotations.stream().filter((x)-> x.getParent().getSignature().equals(feature.getParent().getSignature())).findFirst();
		return relation.isPresent() ? relation.get() : null;
	}
	
	/**
	 * Busca los nodos hijos de un nodo padre usando la anotación de relación
	 */	
	private List<CtAnnotation<FeatureAnnotation>> FindChildren(CtAnnotation<RelationAnnotation> relation)
	{
		if (relation == null)
		{
			return null;
		}
		
		List<CtAnnotation<FeatureAnnotation>> returnValue = new ArrayList<CtAnnotation<FeatureAnnotation>>();
		for (CtAnnotation<FeatureAnnotation> feature : this.featureAnnotations)
		{
			if (Arrays.asList(relation.getActualAnnotation().children()).contains(feature.getActualAnnotation().Name()))
			{
				returnValue.add(feature);
			}
		}

		return returnValue;
	}
}
