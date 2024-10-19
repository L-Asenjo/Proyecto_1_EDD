/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Representa una linea de una red de transporte. Implementada con ArrayList.
 */

public class Linea extends ArrayList<Parada> {
    /**
     * Nombre de la linea.
     */
    private String nombre;

    /**
     * Constructor de la clase.
     * 
     * @param nombre El nombre de la linea.
     */
    public Linea(String nombre) {
        super(1);
        setNombre(nombre);
    }

    /**
     * Getter de nombre.
     * 
     * @return El nombre de la linea.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setter de nombre.
     * 
     * @param nombre El nuevo nombre de la linea.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.length() == 0) {
            throw new IllegalArgumentException("El nombre de la linea no puede estar vacío");
        }
        this.nombre = nombre.strip();
    }

    /**
     * Retorna un arreglo de Strings con los nombre de las parada y si la parada
     * tiene un alias lo
     * devuelve con el formato "nombre:alias"
     * 
     * @return un arreglo de Strings con los nombre de las parada
     */
    public String[] getParadas() {
        if (this.vacia()) {
            return null;
        }
        String[] paradas = new String[this.size()];
        Parada parada;
        for (int i = 0; i < this.size(); i++) {
            parada = this.get(i);
            if (parada.getAlias() != null) {
                paradas[i] = parada.getNombre() + ":" + parada.getAlias();
                continue;
            }
            paradas[i] = parada.getNombre();
        }
        return paradas;
    }

    /**
     * Agrega una parada a la linea.
     * 
     * @param parada La parada a agregar.
     * @return true si la parada fue agregada, false de lo contrario.
     */

    public boolean agregarParada(Parada parada) {
        if (parada == null) {
            return false;
        }
        if (buscarParadaPorNombre(parada.getNombre()) >= 0) {
            return false;
        }
        this.agregar(parada);
        return true;
    }

    /**
     * Elimina una parada de la linea.
     * 
     * @param i el indice de la parada
     */

    public void removerParada(int i) {
        this.remover(i);
        this.compactar();
    }

    /**
     * Busca una parada por su nombre.
     * 
     * @param nombreParada el nombre de la parada
     * @return el indice de la parada
     */
    public int buscarParadaPorNombre(String nombreParada) {
        if (vacia()) {
            return -1;
        }
        for (int i = 0; i < size(); i++) {
            if (this.get(i).getNombre().equals(nombreParada)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca una parada por su alias.
     * 
     * @param aliasParada el alias de la parada
     * @return el indice de la parada
     */
    public int buscarParadaPorAlias(String aliasParada) {
        if (vacia()) {
            return -1;
        }
        for (int i = 0; i < size(); i++) {
            if (get(i).getAlias() == null) {
                continue;
            }
            if (get(i).getAlias().equals(aliasParada)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Elimina todas las paradas de la linea.
     */

    public void vaciarLinea() {
        this.vaciar();
        this.compactar();
    }

}
