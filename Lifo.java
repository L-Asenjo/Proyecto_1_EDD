/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Interfaz de pilas. Todo implementación debe cumplir con esta interfaz.
 */

interface Lifo<T> {
    /**
     * Devuelve el tamaño de la pila.
     * 
     * @return el tamaño de la pila
     */
    int size();

    /**
     * Devuelve true si la pila esta vacia
     * 
     * @return true si la pila esta vacia
     */
    boolean vacia();

    /**
     * Coloca un valor en el tope de la pila.
     * 
     * @param valor el valor a apilar
     * @return true si el valor se pudo apilar y false en caso contrario
     */

    boolean apilar(T valor);

    /**
     * Elimina un valor del tope de la pila y lo devuelve.
     * 
     * @return el valor eliminado
     */
    T desapilar();

    /**
     * Devuelve el valor del tope de la pila pero NO lo elimina.
     * 
     * @return el valor del tope de la pila
     */
    T tope();

    /**
     * Vacia la pila
     */
    void vaciar();

    /**
     * Devuelve una representación en cadena de la pila
     * 
     * @return una representación en cadena de la pila
     */
    String toString();
}
