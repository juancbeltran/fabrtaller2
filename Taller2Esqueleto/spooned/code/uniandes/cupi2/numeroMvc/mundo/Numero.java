/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Numero.java,v 1.3 2008/08/13 15:40:20 jua-gome Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_numeroMvc
 * Autor: Pablo Barvo - 02-Mar-2006
 * Modificado por: Juan Erasmo G�mez - 7-Ago-2008  
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.numeroMvc.mundo;

import java.util.Observable;

/**
 * Representa un n�mero que puede ser observado.
 */
public class Numero extends Observable
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * N�mero actual.
     */
    private int numero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el n�mero inicializ�ndolo en 0.
     */
    public Numero( )
    {
        numero = 0;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Cambia el valor del n�mero.
     * @param nuevoNumero Nuevo valor de n�mero.
     */
    public void cambiarNumero( int nuevoNumero )
    {
        // Cambia el n�mero
        numero = nuevoNumero;
        // Notifica a los observadores, informando el nuevo n�mero
        setChanged( );
        notifyObservers( new Integer( numero ) );
    }

    /**
     * Devuelve el valor del n�mero.
     * @return Valor actual del n�mero.
     */
    public int darNumero( )
    {
        return numero;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     * @return respuesta1.
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2.
     * @return respuesta2.
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}