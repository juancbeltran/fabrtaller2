package code;

import annotation.MyAnnotation;

@MyAnnotation(myAttribute="TestClass")
public class TestClass {

	int i;
	String s;
	Float f;
	
	public TestClass(){}
	
	public void foo(){
		bar();
	}
	
	@MyAnnotation(myAttribute="TestClass")
	private int bar(){
		this.i++;
		return i;
	}
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Float getF() {
		return f;
	}

	public void setF(Float f) {
		this.f = f;
	}
}
