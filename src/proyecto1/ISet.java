/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Interfaz para implementar un conjunto de elementos de tipo genérico
 */

interface ISet<T> {
    /**
     * Retorna el tamaño del conjunto
     * 
     * @return el tamaño del conjunto
     */
    public int size();

    /**
     * Retorna true si el conjunto es vacío, false en caso contrario
     * 
     * @return true si el conjunto es vacío, false en caso contrario
     */

    public boolean vacio();

    /**
     * Retorna true si el conjunto contiene el valor, false en caso contrario
     * 
     * @param valor el valor a verificar
     * @return true si el conjunto contiene el valor, false en caso contrario
     */
    public boolean contiene(T valor);

    /**
     * Agrega un elemento al conjunto y retorna true si lo agrego.
     * Si ya existe, no lo agrega y retorna false
     * 
     * @param valor el valor a agregar
     * @return true si se pudo agregar, false en caso contrario
     */
    public boolean agregar(T valor);

    /**
     * Recibe un arreglo de elementos y los agrega uno por uno.
     * Devuelve un arreglo del mismo tamaño donde el valor true
     * significa que se agrego el elemento o false en caso contrario
     * 
     * @param valor el arreglo de elementos a agregar
     * @return el arreglo del mismo tamaño donde el valor true significa que se
     *         agrego el elemento o false en caso contrario
     */
    public boolean[] agregar(T[] valor);

    /**
     * Recibe una lista de elementos y los agrega uno por uno.
     * Devuelve un arreglo del mismo tamaño donde el valor true
     * significa que se agrego el elemento o false en caso contrario
     * 
     * @param valor la lista de elementos a agregar
     * @return el arreglo del mismo tamaño donde el valor true significa que se
     *         agrego el elemento o false en caso contrario
     */
    public boolean[] agregar(IList<T> valor);

    /**
     * Recibe un conjunto de elementos y los agrega uno por uno.
     * Devuelve un arreglo del mismo tamaño donde el valor true
     * significa que se agrego el elemento o false en caso contrario
     * 
     * @param set el conjunto a agregar
     * @return el matriz del mismo tamaño donde el valor true significa que se
     *         agrego el elemento o false en caso contrario
     */
    public boolean[] agregar(ISet<T> set);

    /**
     * Remueve un elemento del conjunto y retorna true si lo elimino o false
     * si el elemento no está en el conjunto
     * 
     * @param valor el valor a remover
     * @return true si se pudo remover, false en caso contrario
     */
    public boolean remover(T valor);

    /**
     * Retorna un valor del conjunto y lo elimina de este.
     * Si no hay elementos en el conjunto, retorna null
     * 
     * @return el valor eliminado del conjunto
     */
    public T pop();

    /**
     * Retorna un conjunto con todos los elementos que está en ambos conjuntos.
     * C = A U B
     * 
     * @param set el otro conjunto con el que realizar la operación
     * @return el nuevo conjunto
     */
    public ISet<T> union(ISet<T> set);

    /**
     * Retorna un conjunto con los elementos que están presentes en ambos conjuntos.
     * C = A ∩ B
     * 
     * @param set
     * @return
     */
    public ISet<T> interseccion(ISet<T> set);

    /**
     * Retorna un conjunto con los elementos que está en el conjunto pero no en el
     * conjunto pasado por parámetro. (C = A - B).
     * 
     * @param set el conjunto a restar
     * @return el nuevo conjunto
     */
    public ISet<T> diferencia(ISet<T> set);

    /**
     * Calcula la diferencia simétrica entre este conjunto y el conjunto
     * especificado.
     * La diferencia simétrica de dos conjuntos A y B es el conjunto de elementos
     * que está en A o en B, pero no en ambos.
     * Es decir, la diferencia simétrica A Δ B es igual a (A - B) ∪ (B - A).
     * 
     * @param set el otro conjunto con el que realizar la operación
     * @return el nuevo conjunto
     */
    public ISet<T> diferenciaSimetrica(ISet<T> set);

    /**
     * Retorna true si el conjunto es igual al conjunto pasado por parámetro
     * 
     * @param set el conjunto con el que realizar la operación
     * @return true si el conjunto es igual al conjunto pasado por parámetro
     */
    public boolean equals(ISet<T> set);

    /**
     * Devuelve una lista con todos los elementos del conjunto
     * 
     * @return la lista de los elementos del conjunto
     */
    public IList<T> toList();

    /**
     * Elimina todos los elementos del conjunto
     */
    public void vaciar();

    /**
     * Devuelve una representación del conjunto en String
     * 
     * @return la representación del conjunto
     */
    public String toString();
}
