/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Clase FifoLL de tipo T, para implementación de colas (Fifo). El tipo T me
 * permite tener nodos con valores de cualquier tipo.
 */
public class Fifo<T> implements IFifo<T> {
    /**
     * La lista de valores de la cola.
     */
    private ArrayList<T> list;

    /**
     * Constructor de la clase.
     */
    public Fifo() {
        list = new ArrayList<>();
    }

    /**
     * Devuelve el tamaño de la cola.
     * 
     * @return el tamaño de la cola
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Devuelve true si la cola esta vacia.
     * 
     * @return true si la cola esta vacia
     */
    @Override
    public boolean vacia() {
        return list.vacia();
    }

    /**
     * Agrega un valor al final de la cola.
     * 
     * @param valor el valor a insertar
     * @return true si el valor fue insertado, false en caso contrario
     */
    @Override
    public boolean encolar(T valor) {
        return list.agregar(valor);
    }

    /**
     * Elimina el elemento al inicio de la cola y lo devuelve.
     * 
     * @return el elemento eliminado
     */
    @Override
    public T desencolar() {
        if (list.vacia()) {
            return null;
        }
        return list.remover(0);
    }

    /**
     * Devuelve el elemento al inicio de la cola. No lo elimina.
     * 
     * @return el elemento al inicio de la cola
     */
    @Override
    public T frente() {
        if (list.vacia()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * Devuelve una representación en cadena de la cola.
     * 
     * @return una representación en cadena de la cola
     */
    @Override
    public String toString() {
        String txt = "Fifo(" + size() + "): ";
        if (vacia()) {
            txt += "//";
            return txt;
        }
        for (int i = 0; i < size(); i++) {
            txt += list.get(i).toString() + " <- ";
        }
        txt += "//";
        return txt;
    }

}
