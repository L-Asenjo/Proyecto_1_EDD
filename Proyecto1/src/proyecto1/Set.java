/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto1;

import java.lang.reflect.Array;

/**
 * Implementa un conjunto de elementos de tipo genérico usando un ArrayList
 */
public class Set<T> implements ISet<T> {
    /**
     * El conjunto de elementos;
     */
    ArrayList<T> conjunto;

    /**
     * El constructor
     */
    public Set() {
        this.conjunto = new ArrayList<>();
    }

    /**
     * El constructor
     * 
     * @param valor la lista de elementos
     */
    public Set(IList<T> valor) {
        this.conjunto = new ArrayList<>();
        for (int i = 0; i < valor.size(); i++) {
            this.agregar(valor.get(i));
        }
    }

    /**
     * El constructor
     * 
     * @param valor la arreglo de elementos
     */
    public Set(T[] valor) {
        this.conjunto = new ArrayList<>();
        for (int i = 0; i < valor.length; i++) {
            this.agregar(valor[i]);
        }
    }

    /**
     * El constructor
     * 
     * @param set el conjunto de elementos
     */
    public Set(ISet<T> set) {
        this.conjunto = new ArrayList<>();
        ArrayList<T> listSet = (ArrayList<T>) set.toList();
        for (int i = 0; i < listSet.size(); i++) {
            this.agregar(listSet.get(i));
        }
    }

    /**
     * Retorna el tamaño del conjunto
     * 
     * @return el tamaño del conjunto
     */
    @Override
    public int size() {
        return this.conjunto.size();
    }

    /**
     * Retorna true si el conjunto es vacío, false en caso contrario
     * 
     * @return true si el conjunto es vacío, false en caso contrario
     */
    @Override
    public boolean vacio() {
        return this.conjunto.vacia();
    }

    /**
     * Retorna true si el conjunto contiene el valor, false en caso contrario
     * 
     * @param valor el valor a verificar
     * @return true si el conjunto contiene el valor, false en caso contrario
     */
    @Override
    public boolean contiene(T valor) {
        for (int i = 0; i < this.conjunto.size(); i++) {
            if (this.conjunto.get(i).equals(valor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega un elemento al conjunto y retorna true si lo agrego.
     * Si ya existe, no lo agrega y retorna false
     * 
     * @param valor el valor a agregar
     * @return true si se pudo agregar, false en caso contrario
     */
    @Override
    public boolean agregar(T valor) {
        if (this.contiene(valor)) {
            return false;
        }
        this.conjunto.agregar(valor);
        return true;
    }

    /**
     * Recibe un arreglo de elementos y los agrega uno por uno.
     * Devuelve un matriz del mismo tamaño donde el valor true
     * significa que se agrego el elemento o false en caso contrario
     * 
     * @param valor el matriz de elementos a agregar
     * @return el matriz del mismo tamaño donde el valor true significa que se
     *         agrego el elemento o false en caso
     */
    @Override
    public boolean[] agregar(T[] valor) {
        boolean[] aux = new boolean[valor.length];
        for (int i = 0; i < valor.length; i++) {
            aux[i] = this.agregar(valor[i]);
        }
        return aux;
    }

    /**
     * Recibe una lista de elementos y los agrega uno por uno.
     * Devuelve un arreglo del mismo tamaño donde el valor true significa que se
     * agrego el elemento o false en caso contrario
     * 
     * @param valor la lista de elementos a agregar
     * @return el matriz del mismo tamaño donde el valor true significa que se
     *         agrego el elemento o false en caso
     */
    public boolean[] agregar(IList<T> valor) {
        boolean[] aux = new boolean[valor.size()];
        for (int i = 0; i < valor.size(); i++) {
            aux[i] = this.agregar(valor.get(i));
        }
        return aux;
    }

    /**
     * Recibe un conjunto de elementos y los agrega uno por uno.
     * Devuelve un arreglo del mismo tamaño donde el valor true significa que se
     * agrego el elemento o false en caso contrario
     * 
     * @param set el conjunto de elementos a agregar
     * @return el matriz del mismo tamaño donde el valor true significa que se
     *         agrego el elemento o false en caso
     */

    public boolean[] agregar(ISet<T> set) {
        boolean[] aux = new boolean[set.size()];
        ArrayList<T> listSet = (ArrayList<T>) set.toList();
        for (int i = 0; i < listSet.size(); i++) {
            aux[i] = this.agregar(listSet.get(i));
        }
        return aux;
    }

    /**
     * Retira un elemento del conjunto y retorna true si lo removió.
     * Si no existe, no lo remueve y retorna false
     * 
     * @param valor el valor a retirar
     * @return true si se pudo retirar, false en caso contrario
     */

    @Override
    public boolean remover(T valor) {
        for (int i = 0; i < this.conjunto.size(); i++) {
            if (this.conjunto.get(i).equals(valor)) {
                this.conjunto.remover(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna un valor del conjunto y lo elimina de este.
     * Si no hay elementos en el conjunto, retorna null
     * 
     * @return el valor eliminado del conjunto
     */
    @Override
    public T pop() {
        return this.conjunto.remover(0);
    }

    /**
     * Retorna un conjunto con los elementos de ambos conjuntos
     * 
     * @param set el conjunto a unir
     * @return el nuevo conjunto
     */
    @Override
    public Set<T> union(ISet<T> set) {
        Set<T> aux = new Set<>();
        ArrayList<T> listSet = (ArrayList<T>) set.toList();
        for (int i = 0; i < this.conjunto.size(); i++) {
            aux.agregar(this.conjunto.get(i));
        }
        for (int i = 0; i < listSet.size(); i++) {
            aux.agregar(listSet.get(i));
        }
        return aux;
    }

    /**
     * Retorna un conjunto con los elementos comunes a ambos conjuntos
     * 
     * @param set el conjunto a interseccionar
     * @return el nuevo conjunto
     */

    @Override
    public Set<T> interseccion(ISet<T> set) {
        Set<T> aux = new Set<>();
        ArrayList<T> listSet = (ArrayList<T>) set.toList();
        for (int i = 0; i < listSet.size(); i++) {
            if (this.contiene(listSet.get(i))) {
                aux.agregar(listSet.get(i));
            }
        }
        return aux;
    }

    /**
     * Retorna un conjunto con los elementos que están en el conjunto pero no en el
     * conjunto pasado por parámetro. (C = A - B).
     * 
     * @param set el conjunto a restar
     * @return el nuevo conjunto
     */
    @Override
    public Set<T> diferencia(ISet<T> set) {
        Set<T> aux = new Set<>();
        for (int i = 0; i < this.conjunto.size(); i++) {
            if (!set.contiene(this.conjunto.get(i))) {
                aux.agregar(this.conjunto.get(i));
            }
        }
        return aux;
    }

    /**
     * Calcula la diferencia simétrica entre este conjunto y el conjunto
     * especificado.
     * La diferencia simétrica de dos conjuntos A y B es el conjunto de elementos
     * que están en A o en B, pero no en ambos.
     * Es decir, la diferencia simétrica A Δ B es igual a (A - B) ∪ (B - A).
     * 
     * @param set el otro conjunto con quien realizar la operación
     * @return el nuevo conjunto
     */
    @Override
    public Set<T> diferenciaSimetrica(ISet<T> set) {
        Set<T> aux = new Set<>();
        ArrayList<T> listSet = (ArrayList<T>) set.toList();
        for (int i = 0; i < this.size(); i++) {
            if (!set.contiene(this.conjunto.get(i))) {
                aux.agregar(this.conjunto.get(i));
            }
        }
        for (int i = 0; i < listSet.size(); i++) {
            if (!this.contiene(listSet.get(i))) {
                aux.agregar(listSet.get(i));
            }
        }
        return aux;
    }

    /**
     * Compara dos conjuntos, retorna true si son iguales, false en caso contrario
     * 
     * @param set el conjunto con el que comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(ISet<T> set) {
        if (this.size() != set.size()) {
            return false;
        }
        Set<T> aux = this.diferencia(set);
        if (!aux.vacio()) {
            return false;
        }
        aux = (Set<T>) set;
        aux = ((Set<T>) set).diferencia(this);
        if (!aux.vacio()) {
            return false;
        }
        return true;
    }

    /**
     * Retorna una lista con los elementos del conjunto
     * 
     * @return la lista de los elementos del conjunto
     */
    public ArrayList<T> toList() {
        ArrayList<T> aux = new ArrayList<>();
        for (int i = 0; i < this.conjunto.size(); i++) {
            aux.agregar(this.conjunto.get(i));
        }
        return aux;
    }

    /**
     * Limpia el conjunto
     */
    @Override
    public void vaciar() {
        conjunto.vaciar();
    }

    /**
     * Retorna una representación en cadena del conjunto
     * 
     * @return la representación en cadena
     */
    @Override
    public String toString() {
        String txt = "Set(" + conjunto.size() + "): {";
        for (int i = 0; i < conjunto.size(); i++) {
            txt += conjunto.get(i);
            if (i < conjunto.size() - 1) {
                txt += ", ";
            }
        }
        txt += "}";
        return txt;
    }

}
