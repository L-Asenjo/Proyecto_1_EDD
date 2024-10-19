/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Interfaz de colas. Todo implementación debe cumplir con esta interfaz.
 */
interface IFifo<T> {
    /**
     * Devuelve el tamaño de la cola
     * 
     * @return el tamaño de la cola
     */
    int size();

    /**
     * Devuelve true si la cola esta vacia
     * 
     * @return true si la cola esta vacia
     */
    boolean vacia();

    /**
     * Agrega un elemento al final de la cola
     * 
     * @return true si el elemento fue agregado
     */
    boolean encolar(T valor);

    /**
     * Elimina el elemento al inicio de la cola y lo devuelve
     * 
     * @return el elemento eliminado
     */
    T desencolar();

    /**
     * Devuelve el elemento al inicio de la cola
     * 
     * @return el elemento al inicio de la cola
     */
    T frente();

    /**
     * Devuelve una representación en cadena de la cola
     * 
     * @return una representación en cadena de la cola
     */
    String toString();

}
