/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Implementa una lista de elementos de tipo genérico usando arreglos
 */

public class ArrayList<T> implements IList<T> {
    /**
     * Tamaño por defecto inicial de la lista
     */
    private final int DEFAULT_TAM_INICIAL = 1;
    /**
     * Máximo del tamaño inicial
     */
    private final int MAX_TAM_INICIAL = 50;
    /**
     * Tamaño por defecto del bloque en que crece el arreglo
     */
    private final int DEFAULT_TAM_BLOQUE = 5;
    /**
     * Máximo del tamaño del bloque
     */
    private final int MAX_TAM_BLOQUE = 50;
    /**
     * Arreglo de elementos de la lista
     */
    private T[] arreglo;
    /**
     * Tamaño de la lista
     */
    private int tamLista;
    /**
     * Tamaño inicial del arreglo de la lista
     */
    private int tamInicial;
    /**
     * Tamaño del bloque en que crece el arreglo de la lista
     */
    private int tamBloque;

    /**
     * Constructor por defecto, se usa el tamaño inicial
     * y el tamaño del bloque por defecto
     */

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.setTamInicial(DEFAULT_TAM_INICIAL);
        this.setTamBloque(DEFAULT_TAM_BLOQUE);
        arreglo = (T[]) new Object[tamInicial];
        tamLista = 0;
    }

    /**
     * Constructor con el tamaño del bloque a usar
     * El tamaño inicial es el por defecto
     * 
     * @param tamBloque el tamaño del bloque
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int tamBloque) {
        this.setTamBloque(tamBloque);
        this.setTamInicial(DEFAULT_TAM_INICIAL);
        arreglo = (T[]) new Object[tamInicial];
        tamLista = 0;
    }

    /**
     * Constructor con los valores de tamaño inicial y tamaño del bloque
     * 
     * @param tamInicial el tamaño inicial
     * @param tamBloque  el tamaño del bloque
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int tamInicial, int tamBloque) {
        this.setTamInicial(tamInicial);
        this.setTamBloque(tamBloque);
        arreglo = (T[]) new Object[tamInicial];
        tamLista = 0;
    }

    /**
     * El tamaño del arreglo de la lista, es un método package private
     * ya que es usado para hacer pruebas y verificar que funciona correctamente
     * 
     * @return el tamaño del arreglo
     */
    int getTamArreglo() {
        return arreglo.length;
    }

    /**
     * Setter de tamaño inicial
     * 
     * @param tamInicial el tamaño inicial
     */
    public void setTamInicial(int tamInicial) {
        if (tamInicial <= 0 || tamInicial > MAX_TAM_INICIAL) {
            this.tamInicial = DEFAULT_TAM_INICIAL;
        }
        this.tamInicial = tamInicial;
    }

    /**
     * Setter de tamaño del bloque
     * 
     * @param tamBloque el tamaño del bloque
     */
    public void setTamBloque(int tamBloque) {
        if (tamBloque <= 0 || tamBloque > MAX_TAM_BLOQUE) {
            this.tamBloque = DEFAULT_TAM_BLOQUE;
        }
        this.tamBloque = tamBloque;
    }

    /**
     * Retorna el tamaño de la lista
     * 
     * @return el tamaño de la lista
     */
    @Override
    public int size() {
        return tamLista;
    }

    /**
     * Retorna true si la lista es vacía, false en caso contrario
     * 
     * @return true si la lista es vacía, false en caso contrario
     */
    @Override
    public boolean vacia() {
        return tamLista == 0;
    }

    /**
     * Inserta un elemento en la lista en la posición indicada, retorna true si se
     * pudo insertar, false en caso contrario
     * 
     * @param index el indice en la lista donde insertar el elemento
     * @param valor el valor a insertar
     * @return true si se pudo insertar, false en caso contrario
     */

    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(int index, T valor) {
        if (index < 0 || index > tamLista) {
            return false;
        }
        if (tamLista == arreglo.length) {
            T[] temp = (T[]) new Object[arreglo.length + tamBloque];
            for (int i = 0; i < tamLista; i++) {
                temp[i] = arreglo[i];
            }
            arreglo = temp;
        }
        for (int i = tamLista; i > index; i--) {
            arreglo[i] = arreglo[i - 1];
        }
        arreglo[index] = valor;
        tamLista++;
        return true;
    }

    /**
     * Inserta un elemento al final de la lista. Retorna true si se pudo insertar,
     * false en caso contrario
     * 
     * @param valor el valor a insertar
     * @return true si se pudo insertar, false en caso contrario
     */

    @Override
    public boolean agregar(T valor) {
        return insertar(tamLista, valor);
    }

    /**
     * Retorna el elemento de la lista en la posición indicada, null en caso
     * contrario
     * 
     * @param index el indice del elemento
     * @return el elemento
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= tamLista) {
            return null;
        }
        return arreglo[index];
    }

    /**
     * Modifica el elemento de la lista en la posición indicada, retorna true si se
     * pudo modificar, false en caso contrario
     * 
     * @param index el indice del elemento
     * @param valor el nuevo valor
     * @return true si se pudo modificar, false en caso contrario
     */
    @Override
    public boolean set(int index, T valor) {
        if (index < 0 || index >= tamLista) {
            return false;
        }
        arreglo[index] = valor;
        return true;
    }

    /**
     * Compacta el arreglo de la lista, evita que el arreglo se llene de espacios
     * no usados. Solo compacta si la diferencia entre el tamaño de la lista y
     * el tamaño del arreglo es mayor que el tamaño del bloque.
     */
    @SuppressWarnings("unchecked")
    public void compactar() {
        T[] temp = (T[]) new Object[tamLista];
        for (int i = 0; i < tamLista; i++) {
            temp[i] = arreglo[i];
        }
        arreglo = temp;
    }

    /**
     * Elimina el elemento de la lista en la posición indicada, retorna el valor
     * que se elimino, null en caso contrario
     * 
     * @param index el indice del elemento
     * @return el valor que se elimino
     */
    @Override
    public T remover(int index) {
        if (index < 0 || index >= tamLista) {
            return null;
        }
        T temp = arreglo[index];
        for (int i = index; i < tamLista - 1; i++) {
            arreglo[i] = arreglo[i + 1];
        }
        tamLista--;
        if (arreglo.length - tamLista > tamBloque) {
            compactar();
        }
        return temp;
    }

    /**
     * Vacia la lista.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void vaciar() {
        arreglo = (T[]) new Object[tamInicial];
        tamLista = 0;
    }

    /**
     * Retorna un string con la representación de la lista
     */
    @Override
    public String toString() {
        if (arreglo == null) {
            String txt = "List(0/0): ";
            txt += "//";
            return txt;
        }
        String txt = "List(" + tamLista + "): ";

        for (int i = 0; i < tamLista; i++) {
            txt += arreglo[i] + " -> ";
        }
        txt += "//";
        return txt;
    }

}
