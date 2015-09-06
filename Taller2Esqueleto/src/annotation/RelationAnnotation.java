package annotation;

//From the Parent to the children
public @interface RelationAnnotation {
	public String[] children();

	public RelationType relationType() ;
}


