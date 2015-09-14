package processor;

import spoon.processing.AbstractProcessor;

import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.reference.CtTypeReference;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import annotation.FeatureAnnotation;

public class Processor extends AbstractProcessor {

	List<CtAnnotation<? extends Annotation>> codeAnnotations;
	
	@Override
	public void init() {
		System.out.println("Empieza procesamiento");
		codeAnnotations = new ArrayList<CtAnnotation<? extends Annotation>>();
		super.init();
	}
	
	@Override
	public void processingDone() {		

		super.processingDone();
		//// Genera el archivo xml con la representación del arbol según anotaciones
		FeatureIdeWriter featureIdeWriter = new FeatureIdeWriter(codeAnnotations);
		featureIdeWriter.WriteFeatureIdeFile("./featureIdeModel/featureIde.xml");
		
		System.out.println("Termina procesamiento, archivo creado ./featureIdeModel/featureIde.xml ");
	}

	@Override
	public void process(CtElement element) 
	{
		//// Lee todas las anotaciones de tipo CtAnnotation<? extends Annotation> y las almacena en una lista
		List<CtAnnotation<? extends Annotation>> annotations = element.getAnnotations();
		for (CtAnnotation<? extends Annotation> annotation : annotations)
		{
			codeAnnotations.add(annotation);
			CtTypeReference<? extends Annotation> annotationType = annotation.getAnnotationType();
			System.out.println("Anotacion encontrada, tipo: "+annotationType );			
			System.out.println("\t usada en: "+annotation.getParent().getSignature());			

			/*
			if (annotationType.getActualClass() == FeatureAnnotation.class)
			{
				FeatureAnnotation actualAnnotation = (FeatureAnnotation)annotation.getActualAnnotation();				
				System.out.println("\t FeatureAnnotation encontrada, atributo: "+actualAnnotation.Name());
			}
			*/
		}
	}	
}
