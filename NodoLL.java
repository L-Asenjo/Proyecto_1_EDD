/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Clase NodoLL de tipo T, para listas enlazadas simples (LinkedList). El tipo T
 * me permite tener nodos de cualquier tipo, y tiene una
 * referencia al nodo siguiente.
 */

public class NodoLL<T> {
    /**
     * Valor de tipo T del nodo.
     */
    private T valor;
    /**
     * Referencia al nodo siguiente.
     */
    private NodoLL<T> siguiente;

    /**
     * Constructor de un nodo con un valor null.
     */
    public NodoLL() {
        valor = null;
        siguiente = null;
    }

    /**
     * Constructor de un nodo con un valor de tipo genérico T.
     * 
     * @param valor
     */

    public NodoLL(T valor) {
        this.valor = valor;
        siguiente = null;
    }

    /**
     * Getter de valor.
     * 
     * @return el valor del nodo.
     */
    public T getValor() {
        return valor;
    }

    /**
     * Setter de valor.
     * 
     * @param valor
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * Getter y setter de siguiente.
     * 
     * @param nodo el nodo siguiente.
     * @return el nodo siguiente.
     */

    public void setSiguiente(NodoLL<T> nodo) {
        this.siguiente = nodo;
    }

    public NodoLL<T> getSiguiente() {
        return this.siguiente;
    }

    /**
     * Método toString. Muestra el valor del nodo y una referencia al siguiente.
     * Si el siguiente es null, muestra "//".
     * 
     * @return String
     */

    @Override
    public String toString() {
        String txt = "";
        if (valor == null) {
            txt += "";
        } else {
            txt += String.valueOf(valor);
        }
        if (siguiente != null) {
            txt += " -> ";
        } else {
            txt += " -> //";
        }
        return txt;
    }

    /**
     * Método main, para algunas pruebas.
     * 
     * @param args
     */

    public static void main(String args[]) {
        NodoLL<Integer> nodo = new NodoLL<>(1);
        System.out.println(nodo);
    }

}
