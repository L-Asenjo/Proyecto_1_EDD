/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;

/**
 * Interfaz de Listas. Todo implementación debe cumplir con esta interfaz.
 */

interface List<T> {

    /**
     * Devuelve el tamaño de la lista
     * 
     * @return tamaño de la lista
     */
    int size();

    /**
     * Devuelve true si la lista esta vacia
     * 
     * @return true si la lista esta vacia
     */
    boolean vacia();

    /**
     * Inserta un elemento con el valor indicado, en el indice de la lista indicado.
     * Devuelve true si el elemento fue insertado correctamente, false en caso
     * contrario.
     * El indice puede ser negativo, en este caso, el indice -1 es igual a size() -
     * 1 y el indice -2 es igual a size() - 2 y y -size() es igual a 0.
     * 
     * @param valor el valor a insertar
     * @param index el indice de inserción, va de 0 a size() o -1 a -size().
     * @return true si el elemento fue insertado correctamente, false en caso
     *         contrario.
     */

    boolean insertar(T valor, int index);

    /**
     * Inserta un elemento al final de la lista. Devuelve true si el elemento fue
     * insertado correctamente, false en caso contrario. Es un insertar con el
     * indice
     * igual a size().
     * 
     * @param valor el valor a insertar
     * @return true si el elemento fue insertado correctamente, false en caso
     *         contrario.
     */
    boolean agregar(T valor);

    /**
     * Inserta un elemento al inicio de la lista. Es decir es un insertar con el
     * indice 0.
     * 
     * @param valor el valor a insertar
     * @return true si el elemento fue insertado correctamente, false en caso
     *         contrario.
     */

    boolean insertarCabeza(T valor);

    /**
     * Devuelve el valor de la lista en la ubicación indicada por el parámetro o
     * null si hay un error. Los indices pueden ser negativos, donde el indice -1
     * es igual a size() - 1 y el indice -2 es igual a size() - 2, y -size() es
     * igual a 0.
     * 
     * @param index la ubicación del valor
     * @return el valor en la ubicación indicada por el parámetro index o null si
     *         hay un error.
     */

    T getValor(int index);

    /**
     * Establece el valor de la lista en la ubicación indicada por el parámetro.
     * Los indices pueden ser negativos, donde el indice -1 es igual a size() -
     * 1 y el indice -2 es igual a size() - 2, y -size() es igual a 0.
     * 
     * @param valor el valor a establecer
     * @param index la ubicación del valor
     * @return true si el valor fue establecido correctamente, false en caso
     *         contrario
     */

    boolean setValor(T valor, int index);

    /**
     * Remueve el elemento de la lista en la ubicación indicada por el parámetro.
     * Los indices pueden ser negativos, donde el indice -1 es igual a size() -1
     * y el indice -2 es igual a size() - 2, y -size() es igual a 0.
     * 
     * @param index la ubicación del valor
     * @return el valor removido
     */

    T remover(int index);

    /**
     * Remueve el primer elemento de la lista.
     * 
     * @return el primer elemento
     */

    T removerCabeza();

    /**
     * Remueve el último elemento de la lista.
     * 
     * @return el último elemento
     */

    T removerCola();

    /**
     * Remueve todos los elementos de la lista.
     */

    void vaciar();

    /**
     * Devuelve una copia de la lista. Es una copia liviana de los
     * valores de la lista, si los valores son objetos, se usan referencias a estos,
     * así que debes tener cuidado si los modificas.
     * 
     * @return una copia de la lista
     */
    List<T> copiar();

    /**
     * Invierte los elementos de la lista "In Place".
     */
    void invertir();

    /**
     * Devuelve una copia de la lista invertida.
     * 
     * @return una copia de la lista invertida
     */
    List<T> invertirCopia();

    /**
     * Determina si dos listas son iguales.
     * 
     * @param obj la lista a comparar con la lista actual
     * @return true si las listas son iguales, false en caso contrario
     */

    boolean equals(Object obj);

    /**
     * Devuelve una representación en cadena de la lista.
     */

    @Override
    String toString();

}
