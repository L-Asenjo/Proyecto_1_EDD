/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Clase LifoLL de tipo T, para implementación de pilas (Lifo). El tipo T me
 * permite tener nodos con valores de cualquier tipo.
 */

public class LifoLL<T> implements Lifo<T> {
    /**
     * La lista de valores de la pila.
     */
    private LinkedList<T> list;

    /**
     * Constructor de la clase.
     */
    public LifoLL() {
        list = new LinkedList<>();
    }

    /**
     * Método que devuelve el tamaño de la pila.
     * 
     * @return el tamaño de la pila
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Método que devuelve true si la pila esta vacía.
     * 
     * @return true si la pila esta vacía, false en caso contrario.
     */
    @Override
    public boolean vacia() {
        return list.vacia();
    }

    /**
     * Método que agrega un valor al final de la pila.
     * 
     * @param valor el valor a insertar
     * @return true si el valor fue insertado, false en caso contrario
     */
    @Override
    public boolean apilar(T valor) {
        return list.agregar(valor);
    }

    /**
     * Método que elimina el valor del tope de la pila y lo devuelve.
     * 
     * @return el valor eliminado
     */
    @Override
    public T desapilar() {
        if (list.vacia()) {
            return null;
        }
        return list.removerCola();
    }

    /**
     * Método que devuelve el valor del tope de la pila. No lo elimina.
     * 
     * @return el valor del tope de la pila
     */
    @Override
    public T tope() {
        if (list.vacia()) {
            return null;
        }
        return list.getValor(size() - 1);
    }

    /**
     * Método que vacia la pila.
     */
    @Override
    public void vaciar() {
        list.vaciar();
    }

    /**
     * Método que devuelve una representación en cadena de la pila.
     */

    @Override
    public String toString() {
        String txt = "Lifo(" + size() + "): ";

        if (vacia()) {
            txt += "//";
            return txt;
        }
        for (int i = 0; i < size(); i++) {
            txt += list.getValor(i).toString() + " -> ";
        }
        txt += "//";
        return txt;
    }
}
