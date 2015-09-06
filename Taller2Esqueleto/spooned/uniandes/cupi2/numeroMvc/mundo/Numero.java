package uniandes.cupi2.numeroMvc.mundo;


/** 
 * Representa un n�mero que puede ser observado.
 */
public class Numero extends java.util.Observable {
    /** 
     * N�mero actual.
     */
    private int numero;
    
    /** 
     * Construye el n�mero inicializ�ndolo en 0.
     */
    public Numero() {
        numero = 0;
    }
    
    /** 
     * Cambia el valor del n�mero.
     * @param nuevoNumero Nuevo valor de n�mero.
     */
    public void cambiarNumero(int nuevoNumero) {
        numero = nuevoNumero;
        setChanged();
        notifyObservers(new java.lang.Integer(numero));
    }
    
    /** 
     * Devuelve el valor del n�mero.
     * @return Valor actual del n�mero.
     */
    public int darNumero() {
        return numero;
    }
    
    /** 
     * M�todo para la extensi�n 1.
     * @return respuesta1.
     */
    public java.lang.String metodo1() {
        return "Respuesta 1";
    }
    
    /** 
     * M�todo para la extensi�n2.
     * @return respuesta2.
     */
    public java.lang.String metodo2() {
        return "Respuesta 2";
    }
    
}

