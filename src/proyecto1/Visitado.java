/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Clase Visitado, para representar un vertice visitado en un grafo. Sirve tanto
 * para recorrer el grafo, como
 * para determinar cuales son los vertices (paradas) que están dentro de un area
 * de cobertura de una sucursal.
 */

public class Visitado {
    /**
     * Nombre del vertice visitado.
     */
    public String nombre;
    /**
     * Distancia al vertice visitado. Es la suma de los pesos para llegar a este
     * vertice.
     */
    public int distancia;

    /**
     * Constructor de la clase Visitado.
     * 
     * @param nombre    el nombre del vertice visitado.
     * @param distancia la distancia al vertice visitado.
     */
    public Visitado(String nombre, int distancia) {
        if (nombre == null || nombre.length() == 0) {
            throw new IllegalArgumentException("El nombre del vertice visitado no puede ser nulo o vacío");
        }
        this.nombre = nombre;
        this.distancia = distancia;
    }

    /**
     * Representación en cadena de texto de un objeto de esta clase.
     * 
     * @return una representación en cadena de texto de un objeto de esta clase.
     */
    @Override
    public String toString() {
        return this.nombre + "(" + this.distancia + ")";
    }
}
