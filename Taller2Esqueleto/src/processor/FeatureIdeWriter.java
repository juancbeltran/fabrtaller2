package processor;

import java.lang.annotation.Annotation;
import java.math.BigInteger;
import java.util.List;

import featureidejaxb.FeatureModel;
import featureidejaxb.Struct;
import spoon.reflect.declaration.CtAnnotation;

public class FeatureIdeWriter {

	public void WriteFeatureIdeFile(List<CtAnnotation<? extends Annotation>> codeAnnotations) {
		
		FeatureModel featureModel = new FeatureModel();
		featureModel.setChosenLayoutAlgorithm(new BigInteger("1"));
	
	
		Struct featureStruct = new Struct();
		featureModel.setStruct(featureStruct);
		
		JaxbWriterReader.jaxbWriterNoSchema(featureModel, "./featureIdeModel/featureIde.xml");
	}
}
