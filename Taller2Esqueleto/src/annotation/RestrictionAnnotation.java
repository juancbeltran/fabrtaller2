package annotation;

public @interface RestrictionAnnotation {
  public boolean Include() default false;
  public boolean Exclude() default false;
  
  public String Origin() default "defaultOrigin";
  public String Target() default "defaultTarget";
}
