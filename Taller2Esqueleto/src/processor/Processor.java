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
	
/*	
	@Override
	public void process(CtAnnotation<FeatureAnnotation> annotation) {
		boolean mandatory = annotation.getActualAnnotation().Mandatory();
		String name = annotation.getActualAnnotation().Name();
		
		System.out.println("Anotacion encontrada, Name: "+name);
		System.out.println("Mandatorio: "+mandatory);
		System.out.println("\t usada en: "+annotation.getParent().getSignature());
	}
*/	
	@Override
	public void processingDone() {
		System.out.println("Termina procesamiento");
		super.processingDone();
		
		FeatureIdeWriter featureIdeWriter = new FeatureIdeWriter();
		featureIdeWriter.WriteFeatureIdeFile(codeAnnotations);
	}

	@Override
	public void process(CtElement element) {
			
		List<CtAnnotation<? extends Annotation>> annotations = element.getAnnotations();
		
		for (CtAnnotation<? extends Annotation> annotation : annotations)
		{
			codeAnnotations.add(annotation);
			CtTypeReference<? extends Annotation> annotationType = annotation.getAnnotationType();
			System.out.println("Anotacion encontrada, tipo: "+annotationType );			
			System.out.println("\t usada en: "+annotation.getParent().getSignature());			

			if (annotationType.getActualClass() == FeatureAnnotation.class)
			{
				FeatureAnnotation actualAnnotation = (FeatureAnnotation)annotation.getActualAnnotation();				
				System.out.println("\t FeatureAnnotation encontrada, atributo: "+actualAnnotation.Name());
			}
		}
	}	
}
