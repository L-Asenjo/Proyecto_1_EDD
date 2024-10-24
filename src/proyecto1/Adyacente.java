/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Clase Adyacente que contiene el nombre del vertice adyacente y el peso
 * (es parte de un grafo no dirigido)
 */
public class Adyacente {
    /**
     * Nombre del vertice adyacente
     */
    public String nombre;
    /**
     * Peso (es la distancia en que se encuentran dos vertices (paradas) adyacentes,
     * por lo general es 1 a menos que sea una estación o parada de transferencia,
     * en cuyo caso es 0).
     */
    public int peso;

    /**
     * Constructor de la Clase Adyacente
     * 
     * @param nombre nombre de vertice (parada) adyacente.
     * @param peso   peso o distancia al vertice o parada adyacente.
     */
    public Adyacente(String nombre, int peso) {
        if (nombre == null || nombre.length() == 0) {
            throw new IllegalArgumentException("El nombre del vertice adyacente no puede ser nulo o vacío");
        }
        this.nombre = nombre;
        this.peso = peso;
    }

    /**
     * Representación en cadena de texto de un objeto de esta clase.
     * 
     * @return una representación en cadena de texto de un objeto de esta clase.
     */
    @Override
    public String toString() {
        return this.nombre + " (" + this.peso + ")";
    }
}
