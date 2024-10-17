/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.Objects;

/**
 * Clase Lista enlazada con nodos de Tipo T @see NodoLL<T>.
 */

public class LinkedList<T> implements List<T> {

    /**
     * Atributos
     * referencia al primer nodo de la lista.
     * 
     * @see NodoLL
     */
    private NodoLL<T> cabeza;

    /**
     * tamaño de la lista.
     */
    private int tam;

    /**
     * Constructor
     */
    public LinkedList() {
        cabeza = null;
        tam = 0;
    }

    /**
     * Getter de cabeza.
     * 
     * @return la cabeza de la lista.
     */
    protected NodoLL<T> getCabeza() {
        return cabeza;
    }

    /**
     * Setter de cabeza. Tener MUCHO cuidado al modificar este atributo.
     * pues no incrementa el tamaño de la lista, y la lista anterior se pierde,
     * pues deja de ser apuntada por la cabeza. Es util si tienes que crear una
     * lista auxiliar, y poner la cabeza a referenciar a esa nueva lista. Pero
     * debes también modificar el tamaño de la lista con @see setTam. Por eso es
     * protected, ya que listas que heredan de esta clase, pueden usarla, con
     * discreción.
     * 
     * @param cabeza la cabeza de la lista.
     */
    protected void setCabeza(NodoLL<T> cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * Getter de tam.
     * 
     * @return el tam de la lista.
     */

    protected int getTam() {
        return tam;
    }

    /**
     * Setter de tam. Tener MUCHO cuidado al modificar este atributo. Pues podría
     * indicar un tamaño incorrecto al del numero de nodos de la lista.
     * Puede ser necesario cuando se filtran listas y se utiliza una copia auxiliar
     * y se cambia la lista actual usando @see setCabeza. Por eso es protected,
     * para que lista que heredan de esta clase, puedan usarla, con discreción.
     * 
     * @param tam tamaño de la lista
     */
    protected void setTam(int tam) {
        this.tam = tam;
    }

    /**
     * Devuelve el tamaño de la lista.
     * 
     * @return el tamaño de la lista.
     */
    @Override
    public int size() {
        return tam;
    }

    /**
     * Verifica si la lista esta vacía.
     * 
     * @return true si la lista esta vacía, false en caso contrario.
     */
    @Override
    public boolean vacia() {
        return size() == 0;
    }

    /**
     * Transforma valores negativo de un index, para ser usado desde la cola hacia
     * la cabeza de la lista. en este caso si el indice es igual a -1 entonces
     * es igual a: size() - 1 (El ultimo indice de la lista).
     * 
     * @param index el indice a transformar
     * @return el indice transformado.
     */
    protected int transformarIndex(int index) {
        if (index < 0 && index >= -tam) {
            return tam + index;
        }
        return index;
    }

    /**
     * Inserta un nuevo nodo en la lista en el indice indicado.
     * Si el indice es negativo, usa transformarIndex para recorrer la lista
     * desde la cola hacia la cabeza. @see transformarIndex
     * 
     * @param nodo  El nodo a insertar. @see NodoLL<T>
     * @param index el indice del nuevo nodo a insertar.
     * @return true si el nodo fue insertado correctamente, false en caso contrario.
     */
    protected boolean insertarNodo(NodoLL<T> nodo, int index) {
        if (nodo == null) {
            return false;
        }
        index = transformarIndex(index);
        if (index < 0 || index > size()) {
            return false;
        }
        nodo.setSiguiente(null);
        if (vacia()) {
            cabeza = nodo;
            tam++;
            return true;
        }

        if (index == 0) {
            nodo.setSiguiente(cabeza);
            cabeza = nodo;
            tam++;
            return true;
        }
        NodoLL<T> aux;
        aux = cabeza;
        for (int i = 0; i < index - 1; i++) {
            aux = aux.getSiguiente();
        }
        nodo.setSiguiente(aux.getSiguiente());
        aux.setSiguiente(nodo);
        tam++;
        return true;
    }

    /**
     * Inserta un nodo con el valor e indice indicado.
     * Si el indice es negativo, usa transformarIndex para recorrer la lista
     * desde la cola hacia la cabeza. @see transformarIndex
     * 
     * @param valor el valor del nuevo nodo a insertar.
     * @param index el indice del nuevo nodo a insertar.
     * @return true si el nodo fue insertado correctamente, false en caso contrario.
     */
    @Override
    public boolean insertar(T valor, int index) {
        NodoLL<T> nodo = new NodoLL<>(valor);
        return insertarNodo(nodo, index);
    }

    /**
     * Inserta un nodo, con el valor indicado, al final de la lista.
     * 
     * @param valor valor del nuevo nodo.
     * @return true si el nodo fue insertado correctamente, false en caso contrario.
     */
    @Override
    public boolean agregar(T valor) {
        return insertar(valor, size());
    }

    /**
     * Inserta un nodo, con el valor indicado, al inicio de la lista.
     * 
     * @param valor
     * @return true si el nodo fue insertado correctamente, false en caso contrario.
     */
    @Override
    public boolean insertarCabeza(T valor) {
        return insertar(valor, 0);
    }

    /**
     * Devuelve el nodo de la lista con el indice especificado.
     * Usar con MUCHO cuidado, ya que por ejemplo cambiar el atributo
     * siguiente @see NodoLL.setSiguiente de un nodo puede cambiar la lista,
     * de manera irreparable. Usar con discreción. Por eso es protected.
     * Es usado por si en una lista que hereda, se necesita para poder recorrer
     * la lista sin necesidad de depender de los indices, o si se quiere cambiar
     * la lista de alguna manera.
     * Si el indice es negativo, usa transformarIndex para recorrer la lista
     * desde la cola hacia la cabeza. @see transformarIndex
     * 
     * @param index el indice del nodo.
     * @return el nodo de la lista.
     */

    protected NodoLL<T> getNodo(int index) {
        if (vacia()) {
            return null;
        }
        index = transformarIndex(index);
        if (index < 0 || index >= size()) {
            return null;
        }
        NodoLL<T> nodo = cabeza;
        for (int i = 0; i < index; i++) {
            nodo = nodo.getSiguiente();
        }
        return nodo;
    }

    /**
     * Devuelve el valor del nodo de la lista con el indice especificado.
     * Si el indice es negativo, usa transformarIndex para recorrer la lista
     * desde la cola hacia la cabeza. @see transformarIndex
     * 
     * @param index el indice del nodo.
     * @return el valor del nodo.
     */

    @Override
    public T getValor(int index) {
        NodoLL<T> nodo;
        nodo = getNodo(index);
        if (nodo == null) {
            return null;
        }
        return nodo.getValor();
    }

    /**
     * Modifica el valor del nodo de la lista con el indice especificado.
     * Si el indice es negativo, usa transformarIndex para recorrer la lista
     * desde la cola hacia la cabeza. @see transformarIndex
     * 
     * @param valor el nuevo valor del nodo.
     * @param index el indice del nodo.
     * @return true si el nodo fue modificado, false en caso contrario.
     */

    @Override
    public boolean setValor(T valor, int index) {
        NodoLL<T> nodo;
        nodo = getNodo(index);
        if (nodo == null) {
            return false;
        }
        nodo.setValor(valor);
        return true;

    }

    /**
     * Elimina el nodo de la lista con el indice indicado, y devuelve el nodo
     * eliminado.
     * Si el indice es negativo, usa transformarIndex para recorrer la lista
     * desde la cola hacia la cabeza. @see transformarIndex
     * 
     * @param index el indice del nodo a eliminar.
     * @return el nodo eliminado o null en caso de no encontrar el nodo o
     *         de haber un problema.
     */

    protected NodoLL<T> removerNodo(int index) {
        if (vacia()) {
            return null;
        }
        index = transformarIndex(index);
        if (index < 0 || index >= size()) {
            return null;
        }
        NodoLL<T> aux;
        if (index == 0) {
            aux = cabeza;
            cabeza = cabeza.getSiguiente();
            aux.setSiguiente(null);
            tam--;
            return aux;
        }
        aux = cabeza;
        for (int i = 0; i < index - 1; i++) {
            aux = aux.getSiguiente();
        }
        NodoLL<T> nodo = aux.getSiguiente();
        aux.setSiguiente(aux.getSiguiente().getSiguiente());
        tam--;
        nodo.setSiguiente(null);
        return nodo;
    }

    /**
     * Elimina el nodo de la lista con el indice indicado, y devuelve el valor del
     * nodo eliminado.
     * Si el indice es negativo, usa transformarIndex para recorrer la lista
     * desde la cola hacia la cabeza. @see transformarIndex
     * 
     * @param index
     * @return el valor del nodo eliminado o null en caso de no encontrar el nodo
     *         o de haber un problema.
     */

    @Override
    public T remover(int index) {
        NodoLL<T> nodo;
        nodo = removerNodo(index);
        if (nodo == null) {
            return null;
        }
        return nodo.getValor();
    }

    /**
     * Elimina el primer nodo de la lista y devuelve su valor.
     * 
     * @return el valor del primer nodo de la lista.
     */

    @Override
    public T removerCabeza() {
        return remover(0);
    }

    /**
     * Elimina el ultimo nodo de la lista y devuelve su valor.
     * 
     * @return el valor del ultimo nodo de la lista.
     */
    @Override
    public T removerCola() {
        return remover(size() - 1);
    }

    /**
     * Elimina todos los nodos de la lista.
     */
    @Override
    public void vaciar() {
        if (vacia()) {
            return;
        }
        NodoLL<T> nodo = cabeza;
        NodoLL<T> siguiente;
        while (nodo != null) {
            siguiente = nodo.getSiguiente();
            nodo.setSiguiente(null);
            nodo = siguiente;
        }
        cabeza = null;
        tam = 0;
    }

    /**
     * Copia la lista en una nueva lista.
     * 
     * @return la nueva lista.
     */
    @Override
    public LinkedList<T> copiar() {
        LinkedList<T> copia = new LinkedList<>();
        if (vacia()) {
            return copia;
        }
        NodoLL<T> nodo = getCabeza();
        T valor;
        while (nodo != null) {
            valor = nodo.getValor();
            copia.agregar(valor);
            nodo = nodo.getSiguiente();
        }
        return copia;
    }

    /**
     * Invierte la lista "InPlace".
     */
    @Override
    public void invertir() {
        if (vacia()) {
            return;
        }
        LinkedList<T> auxLista = new LinkedList<>();
        NodoLL<T> nodo;
        while (size() > 0) {
            nodo = removerNodo(0);
            auxLista.insertarNodo(nodo, 0);
        }
        setCabeza(auxLista.getCabeza());
        setTam(auxLista.size());
    }

    /**
     * Devuelve una copia de la lista invertida.
     * 
     * @return la copia de la lista invertida
     */
    @Override
    public LinkedList<T> invertirCopia() {
        LinkedList<T> copia = new LinkedList<>();
        if (vacia()) {
            return copia;
        }
        NodoLL<T> nodo;
        nodo = getCabeza();
        while (nodo != null) {
            copia.insertarCabeza(nodo.getValor());
            nodo = nodo.getSiguiente();
        }
        return copia;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LinkedList<?> other = (LinkedList<?>) obj;
        if (this.size() != other.size()) {
            return false;
        }

        NodoLL<T> thisNodo = this.getCabeza();
        NodoLL<T> otherNodo = (NodoLL<T>) other.getCabeza();
        while (thisNodo != null) {
            if (Objects.equals(thisNodo.getValor(), otherNodo.getValor())) {
                thisNodo = thisNodo.getSiguiente();
                otherNodo = otherNodo.getSiguiente();
                continue;
            }
            return false;
        }
        return true;
    }
    

    /**
     * Devuelve una cadena de caracteres con la representación de la lista.
     * 
     * @return una cadena de caracteres con la representación de la lista.
     */
    @Override
    public String toString() {
        String txt = "List(" + size() + "): ";
        if (vacia()) {
            txt += "//";
            return txt;
        }
        NodoLL<T> nodo;
        nodo = cabeza;
        while (nodo != null) {
            txt += nodo.toString();
            nodo = nodo.getSiguiente();
        }
        return txt;
    }

    /**
     * Main por si se necesita probar algo directamente.
     * 
     * @param args
     */

    public static void main(String args[]) {

    }
}