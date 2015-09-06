package processor;

import spoon.processing.AbstractProcessor;

import spoon.reflect.declaration.CtAnnotation;
import annotation.FeatureAnnotation;
import annotation.MyAnnotation;

public class Processor extends AbstractProcessor<CtAnnotation<FeatureAnnotation>>{

	@Override
	public void init() {
		System.out.println("Empieza procesamiento");
		super.init();
	}
	@Override
	public void process(CtAnnotation<FeatureAnnotation> annotation) {
		boolean mandatory = annotation.getActualAnnotation().Mandatory();
		String name = annotation.getActualAnnotation().Name();
		
		System.out.println("Anotacion encontrada, Name: "+name);
		System.out.println("Mandatorio: "+mandatory);
		System.out.println("\t usada en: "+annotation.getParent().getSignature());
	}
	
	@Override
	public void processingDone() {
		System.out.println("Termina procesamiento");
		super.processingDone();
	}

}
