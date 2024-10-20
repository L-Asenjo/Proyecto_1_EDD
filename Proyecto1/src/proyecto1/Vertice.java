/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Clase Vertice, para representar un vertice en un grafo. En el caso del
 * proyecto
 * los vertices son paradas o estaciones de una linea de transporte.
 */
public class Vertice {
    /**
     * Nombre del vertice (parada o estación).
     */
    private String nombre;

    /**
     * Lista de adyacentes del vertice. @see Adyacente
     */
    private ArrayList<Adyacente> adyacentes;
    /**
     * Lista de los vertices (paradas o estaciones) que conforman la zona de
     * cobertura, Si el vertice es una sucursal.
     * Si no es sucursal, una lista vacía. @see Visitado.
     */
    private ArrayList<Visitado> cobertura;

    /**
     * Constructor de la clase.
     * 
     * @param nombre El nombre del vertice.
     */
    public Vertice(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
        if (nombre.length() == 0) {
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        }
        this.nombre = nombre;
        this.cobertura = new ArrayList<>();
        this.adyacentes = new ArrayList<>();
    }

    /**
     * Getter del nombre.
     * 
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el tamaño de la cobertura.
     * 
     * @return el tamaño de la cobertura.
     */
    public int coberturaSize() {
        return cobertura.size();
    }

    /**
     * Devuelve true si la cobertura esta vacía.
     * 
     * @return true si la cobertura esta vacía
     */
    public boolean coberturaVacia() {
        return cobertura.vacia();
    }

    /**
     * Devuelve un arreglo con la cobertura.
     * 
     * @return la cobertura
     */
    public Visitado[] getCobertura() {
        Visitado[] aux = new Visitado[cobertura.size()];
        for (int i = 0; i < this.cobertura.size(); i++) {
            aux[i] = this.cobertura.get(i);
        }
        return aux;
    }

    /**
     * Devuelve un arreglo con los nombres de los vertices en la cobertura.
     * 
     * @return Los nombres de los vertices en la cobertura
     */
    public String[] getNombresCobertura() {
        String[] aux = new String[cobertura.size()];
        for (int i = 0; i < this.cobertura.size(); i++) {
            aux[i] = this.cobertura.get(i).nombre;
        }
        return aux;
    }

    /**
     * Buscar un vertice visitado en la cobertura.
     * 
     * @param nombre el nombre del vertice visitado.
     * @return el vertice visitado o null si no existe.
     */
    public Visitado buscarVisitado(String nombre) {
        if (nombre == null || nombre.length() == 0) {
            return null;
        }
        for (int i = 0; i < this.cobertura.size(); i++) {
            Visitado visitado = this.cobertura.get(i);
            if (visitado.nombre.toLowerCase().equals(nombre.toLowerCase())) {
                return visitado;
            }
        }
        return null;
    }

    /**
     * Busca un vertice visitado en la cobertura.
     * 
     * @param Visitado vertice visitado
     * @return el vertice visitado o null si no existe.
     */
    public Visitado buscarVisitado(Visitado visitado) {
        if (visitado == null || visitado.nombre.length() == 0) {
            return null;
        }
        return buscarVisitado(visitado.nombre);
    }

    /**
     * Agrega un visitado a la cobertura.
     * 
     * @param visitado el vertice visitado.
     * @return true si se agrego, false en caso contrario.
     */
    public boolean agregarVisitado(Visitado visitado) {
        if (visitado == null || visitado.nombre.length() == 0) {
            return false;
        }
        if (buscarVisitado(visitado) != null) {
            return false;
        }
        return cobertura.agregar(visitado);
    }

    /**
     * Toma un nombre y una distancia, lo convierte en un objeto de la clase
     * visitado y lo agrega a la cobertura.
     * 
     * @param nombre    el nombre del vertice visitado.
     * @param distancia la distancia del vertice visitado.
     * @return true si se agrego, false en caso contrario.
     */

    public boolean agregarVisitado(String nombre, int distancia) {
        Visitado visitado = new Visitado(nombre, distancia);
        return agregarVisitado(visitado);
    }

    /**
     * Devuelve un visitado de la cobertura.
     * 
     * @param i el indice del visitado.
     * @return el visitado
     */
    public Visitado getVisitado(int i) {
        return cobertura.get(i);
    }

    /**
     * Devuelve el tamaño de la lista de adyacentes del vertice.
     * 
     * @return el tamaño de la lista de adyacentes del vertice.
     */
    public int adyacentesSize() {
        return adyacentes.size();
    }

    /**
     * Verifica si la lista de adyacentes del vertice esta vacía.
     * 
     * @return true si la lista de adyacentes del vertice esta vacía, false en caso
     *         contrario.
     */
    public boolean adyacentesVacia() {
        return adyacentes.vacia();
    }

    /**
     * Devuelve un arreglo con los adyacentes del vertice.
     * 
     * @return los adyacentes.
     */
    public Adyacente[] getAdyacentes() {
        if (adyacentes.vacia()) {
            return null;
        }
        Adyacente[] aux = new Adyacente[adyacentes.size()];
        for (int i = 0; i < this.adyacentes.size(); i++) {
            aux[i] = this.adyacentes.get(i);
        }
        return aux;
    }

    /**
     * Buscar un vertice adyacente en la lista de adyacentes.
     * 
     * @param nombre el nombre del vertice adyacente.
     * @return el vertice adyacente o null si no existe.
     */
    public Adyacente buscarAdyacente(String nombre) {
        if (nombre == null || nombre.length() == 0) {
            return null;
        }
        for (int i = 0; i < this.adyacentes.size(); i++) {
            Adyacente adyacente = this.adyacentes.get(i);
            if (adyacente.nombre.toLowerCase().equals(nombre.toLowerCase())) {
                return adyacente;
            }
        }
        return null;
    }

    /**
     * Busca un vertice adyacente en la lista de adyacentes.
     * 
     * @param adyacente vertice adyacente
     * @return el vertice adyacente o null si no existe.
     */
    public Adyacente buscarAdyacente(Adyacente adyacente) {
        if (adyacente == null || adyacente.nombre.length() == 0) {
            return null;
        }
        return buscarAdyacente(adyacente.nombre);
    }

    /**
     * Agrega un vertice adyacente a la lista de adyacentes del vertice.
     * 
     * @param adyacente el vertice adyacente
     * @return true si se agrego, false en caso contrario.
     */

    public boolean agregarAdyacente(Adyacente adyacente) {
        if (adyacente == null) {
            return false;
        }
        if (buscarAdyacente(adyacente) != null) {
            return false;
        }
        return adyacentes.agregar(adyacente);
    }

    /**
     * Toma un nombre y un peso, lo convierte en un objeto de clase adyacente y lo
     * agrega a la lista de adyacentes del vertice.
     * 
     * @param nombre el nombre del vertice adyacente.
     * @param peso   el peso o distancia del vertice adyacente.
     * @return true si se agrego, false en caso contrario.
     */
    public boolean agregarAdyacente(String nombre, int peso) {
        if (nombre == null || nombre.length() == 0) {
            return false;
        }
        if (buscarAdyacente(nombre) != null) {
            return false;
        }
        return adyacentes.agregar(new Adyacente(nombre, peso));
    }

    /**
     * Devuelve el adyacente en la posición i de la lista de adyacentes.
     * 
     * @param i el indice
     * @return el adyacente
     */
    public Adyacente getAdyacente(int i) {
        return adyacentes.get(i);
    }

    /**
     * Vacia la cobertura.
     */
    public void vaciarCobertura() {
        this.cobertura.vaciar();
    }

    /**
     * Vacia la lista de adyacentes
     */
    public void vaciarAdyacentes() {
        this.adyacentes.vaciar();
    }

    public void vaciar() {
        this.vaciarCobertura();
        this.vaciarAdyacentes();
    }

    /**
     * Devuelve una representación en cadena de texto de un objeto de esta clase.
     */
    public String toString() {
        String txt = this.getNombre() + ":";
        if (!this.cobertura.vacia()) {
            txt += " ";
            for (int i = 0; i < this.cobertura.size(); i++) {
                txt += this.cobertura.get(i).toString();
                if (i < this.cobertura.size() - 1) {
                    txt += ", ";
                }
            }
        }
        return txt;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
}
