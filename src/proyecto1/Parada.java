/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Representa una parada de una red de transporte.
 */
public class Parada {
    /**
     * nombre de la parada
     */
    private String nombre;

    /**
     * alias de la parada
     */
    private String alias;

    /**
     * Constructor with 2 args.
     * 
     * @param nombre nombre de la parada
     * @param alias  alias de la parada
     */
    public Parada(String nombre, String alias) {
        setNombre(nombre);
        setAlias(alias);
    }

    /**
     * Constructor with 1 args.
     * 
     * @param nombre nombre de la parada
     */
    public Parada(String nombre) {
        setNombre(nombre);
        setAlias(null);
    }

    /**
     * Getter de nombre.
     * 
     * @return el nombre de la parada
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setter de nombre.
     * 
     * @param nombre el nuevo nombre de la parada
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.length() == 0) {
            throw new IllegalArgumentException("El nombre de la parada no puede estar vacío.");
        }
        this.nombre = nombre.strip();
    }

    /**
     * Getter de alias.
     * 
     * @return el alias de la parada
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * Setter de alias.
     * 
     * @param alias el nuevo alias de la parada
     */
    public void setAlias(String alias) {
        if (alias == null || alias.length() == 0) {
            this.alias = null;
            return;
        }
        this.alias = alias.strip();
    }

    /**
     * Retorna un arreglo de Strings con los nombre de las parada y si la parada
     * tiene un alias lo devuelve con el formato "nombre:alias"
     * 
     * @return un arreglo de Strings con los nombre de las parada
     */
    @Override
    public String toString() {
        String txt = "";
        txt += this.nombre;
        if (alias != null && this.alias.length() > 0) {
            txt += ":" + this.alias;
        }
        return txt;
    }

}
