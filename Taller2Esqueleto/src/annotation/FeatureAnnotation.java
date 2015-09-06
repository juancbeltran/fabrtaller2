package annotation;

public @interface FeatureAnnotation {

	public boolean Mandatory() default true;
	
	public String Name() default "defaultFeature";
	
}
