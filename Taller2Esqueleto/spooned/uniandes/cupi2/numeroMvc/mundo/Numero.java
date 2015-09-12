package uniandes.cupi2.numeroMvc.mundo;


@annotation.RelationAnnotation(children = { "Numero" , "cambiarNumero" , "metodo1" , "metodo2" , "darNumero" }, relationType = annotation.RelationType.OR)
@annotation.FeatureAnnotation(Mandatory = true, Name = "RootNumero")
public class Numero extends java.util.Observable {
    private int numero;

    @annotation.FeatureAnnotation(Mandatory = true, Name = "Numero")
    @annotation.RestrictionAnnotation(rules = annotation.Rules.IMPLIES, target = "cambiarNumero")
    public Numero() {
        numero = 0;
    }

    @annotation.FeatureAnnotation(Mandatory = false, Name = "cambiarNumero")
    public void cambiarNumero(int nuevoNumero) {
        numero = nuevoNumero;
        setChanged();
        notifyObservers(new java.lang.Integer(numero));
    }

    @annotation.FeatureAnnotation(Mandatory = true, Name = "darNumero")
    public int darNumero() {
        return numero;
    }

    @annotation.FeatureAnnotation(Mandatory = false, Name = "metodo1")
    @annotation.RestrictionAnnotation(rules = annotation.Rules.NOT_IMPLIES, target = "metodo2")
    public java.lang.String metodo1() {
        return "Respuesta 1";
    }

    @annotation.FeatureAnnotation(Mandatory = false, Name = "metodo2")
    public java.lang.String metodo2() {
        return "Respuesta 2";
    }
}

