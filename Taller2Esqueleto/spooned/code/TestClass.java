package code;


@annotation.MyAnnotation(myAttribute = "TestClass")
public class TestClass {
    int i;

    java.lang.String s;

    java.lang.Float f;

    public TestClass() {
    }

    public void foo() {
        bar();
    }

    @annotation.MyAnnotation(myAttribute = "TestClass")
    private int bar() {
        (this.i)++;
        return i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public java.lang.String getS() {
        return s;
    }

    public void setS(java.lang.String s) {
        this.s = s;
    }

    public java.lang.Float getF() {
        return f;
    }

    public void setF(java.lang.Float f) {
        this.f = f;
    }
}

