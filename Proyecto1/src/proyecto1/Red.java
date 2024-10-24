/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafo;

import java.awt.Toolkit;

/**
 * Clase que representa una red de transporte. Implementada con ArrayList.
 */

public class Red extends ArrayList<Linea> implements IRed {

    /**
     * Nombre de la red.
     */
    private String nombre;
    /**
     * Objeto que se encarga de tomar los datos de un archivo..
     */
    private DatosRedArchivo datosRedArchivo;

    private JsonRedParser jsonRedParser;

    private Grafo grafo;

    /**
     * Constructor por defecto.
     */
    public Red() {
        this.nombre = null;
        this.datosRedArchivo = new DatosRedArchivo();
        this.jsonRedParser = new JsonRedParser();
        this.grafo = new Grafo();
    }

    /**
     * Constructor con parámetros.
     * 
     * @param nombreArchivo nombre del archivo que contiene o va a contener la
     *                      información de la red.
     */

    public Red(String nombreArchivo) {
        super(1);
        this.datosRedArchivo = new DatosRedArchivo(nombreArchivo);
        this.jsonRedParser = new JsonRedParser();
        this.grafo = new Grafo();
    }

    /**
     * Getter de nombre.
     * 
     * @return el nombre de la red
     */

    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setter del nombre de la red.
     */

    public void setNombre(String nombre) {
        if (nombre == null || nombre.length() == 0) {
            throw new IllegalArgumentException("El nombre de la red no puede estar vacío");
        }
        this.nombre = nombre.strip();
    }

    /**
     * Obtiene el nombre del archivo que contiene los datos de la red.
     * 
     * @return el nombre del archivo
     */
    public String getNombreArchivo() {
        return this.datosRedArchivo.getNombreArchivo();
    }

    /**
     * Cambia el nombre del archivo que contiene los datos de la red.
     * 
     * @param nombreArchivo el nuevo nombre del archivo
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.datosRedArchivo.setNombreArchivo(nombreArchivo);
    }

    /**
     * Getter del grafo.
     */
    public Grafo getGrafo() {
        return this.grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    /**
     * Devuelve un array con los nombres de las lineas de la red.
     * 
     * @return un array con los nombres de las lineas de la red
     */

    public String[] getNombresLineas() {
        if (this.vacia()) {
            return null;
        }
        String[] lineas = new String[this.size()];
        for (int i = 0; i < this.size(); i++) {
            lineas[i] = this.get(i).getNombre();
        }
        return lineas;
    }

    /**
     * Devuelve un array con los nombres de las paradas de la linea indicada.
     * 
     * @param index
     * @return un array con los nombres de las paradas
     */

    public String[] getParadasLinea(int index) {
        if (this.vacia()) {
            return null;
        }
        if (index < 0 || index >= this.size()) {
            return null;
        }
        return this.get(index).getParadas();
    }

    /**
     * Agrega una nueva linea a la red.
     * 
     * @param linea linea a agregar
     */
    public boolean agregarLinea(Linea linea) {
        if (!this.vacia()) {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getNombre().equals(linea.getNombre())) {
                    return false;
                }
            }
        }
        return this.agregar(linea);
    }

    /**
     * Agrega una nueva linea a la red.
     * 
     * @param nombreLinea nombre de la linea
     * @return true si se agrego la linea
     */

    public boolean agregarLinea(String nombreLinea) {
        Linea linea = new Linea(nombreLinea);
        return this.agregarLinea(linea);
    }

    /**
     * Agrega una parada a una linea. El string de la parada puede ser de
     * alguno de estos formatos "{"nombreParada"":"alias"}", "{nombreParada:alias}",
     * "nombreParada:alias", "{"nombreParada"}", "{nombreParada}", "nombreParada".
     * 
     * @param nombreLinea  nombre de la linea
     * @param nombreParada nombre de la parada
     * @return true si se agrego la parada o false en caso contrario
     */

    public boolean agregarParada(String nombreLinea, String nombreParada) {
        boolean lineaCreada = false;
        int i = this.buscarLineaPorNombre(nombreLinea);
        if (i == -1) {
            if (!this.agregarLinea(nombreLinea)) {
                return false;
            }
            i = this.buscarLineaPorNombre(nombreLinea);
            lineaCreada = true;
        }
        Linea linea = this.get(i);
        if (linea == null) {
            return false;
        }
        // Si me llega un nombre parada de la siguiente forma:
        // "{"nombreParada"":"alias"}"
        // tengo que dejarlo como nombreParada:alias
        nombreParada.strip();
        nombreParada = nombreParada.replace("{", "");
        nombreParada = nombreParada.replace("}", "");
        nombreParada = nombreParada.replace("\"", "");
        if (nombreParada.contains(":")) {
            String[] parada = nombreParada.split(":");
            if (parada.length != 2) {
                if (lineaCreada) {
                    this.removerLinea(i);
                }
                return false;
            }
            for (int j = 0; j < parada.length; j++) {
                parada[j] = parada[j].strip();
                if (parada[j].length() == 0) {
                    if (lineaCreada) {
                        this.removerLinea(i);
                    }
                    return false;
                }
            }
            if (!linea.agregarParada(new Parada(parada[0], parada[1]))) {
                if (lineaCreada) {
                    this.removerLinea(i);
                }
                return false;
            }
            return true;
        }
        nombreParada = nombreParada.strip();
        if (nombreParada.length() == 0) {
            if (lineaCreada) {
                this.removerLinea(i);
            }
            return false;
        }
        if (!linea.agregarParada(new Parada(nombreParada))) {
            if (lineaCreada) {
                this.removerLinea(i);
            }
            return false;
        }
        return true;
    }

    /**
     * Agrega paradas a una linea. Cada elemento del array de paradas es un string
     * de alguno de estos formatos "{"nombreParada"":"alias"}",
     * "{nombreParada:alias}", "nombreParada:alias", "{"nombreParada"}",
     * "{nombreParada}", "nombreParada".
     * 
     * @param nombreLinea el nombre de la linea
     * @param paradas     el array de strings de paradas
     * @return true si se agrego la linea
     */
    public boolean agregarParadas(String nombreLinea, String[] paradas) {
        boolean lineaCreada = false;
        int i = this.buscarLineaPorNombre(nombreLinea);
        if (i == -1) {
            if (!this.agregarLinea(nombreLinea)) {
                return false;
            }
            i = this.buscarLineaPorNombre(nombreLinea);
            lineaCreada = true;
        }
        for (int j = 0; j < paradas.length; j++) {
            if (!this.agregarParada(nombreLinea, paradas[j])) {
                if (lineaCreada) {
                    this.removerLinea(i);
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Remueve una linea de la red.
     * 
     * @param index el indice de la linea
     */
    public void removerLinea(int index) {
        this.remover(index);
    }

    /**
     * Busca una linea por su nombre.
     * 
     * @param nombreLinea el nombre de la linea
     * @return el indice de la linea o -1 si no se encuentra
     */
    public int buscarLineaPorNombre(String nombreLinea) {
        if (this.vacia()) {
            return -1;
        }
        for (int i = 0; i < size(); i++) {
            if (this.get(i).getNombre().toLowerCase().equals(nombreLinea.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Vacía la red. Sirve para cargar una nueva red.
     */

    public void vaciarRed() {
        if (this.vacia()) {
            return;
        }
        for (int i = 0; i < size(); i++) {
            this.get(i).vaciarLinea();
        }
        this.vaciar();
    }

    /**
     * Toma el archivo de la red .json y carga todos los datos.
     * 
     * @return true si se cargo el archivo o false en caso contrario
     * 
     */
    public boolean cargarArchivo() {
        if (this.datosRedArchivo.getNombreArchivo() == null) {
            return false;
        }
        try {
            String json = this.datosRedArchivo.ObtenerDatos();
            this.jsonRedParser.parse(json);
        } catch (Exception e) {
            return false;
        }
        this.vaciarRed();
        this.setNombre(jsonRedParser.getNombreRed());
        String[] nombresLineas = jsonRedParser.getNombresLineas();
        String[][] nombresParadas = jsonRedParser.getNombresParadas();

        for (int i = 0; i < nombresLineas.length; i++) {
            Linea linea = new Linea(nombresLineas[i]);
            for (int j = 0; j < nombresParadas[i].length; j++) {
                if (nombresParadas[i][j].contains(":")) {
                    String nombreParada = nombresParadas[i][j].strip();
                    String[] aux = nombreParada.split(":");
                    Parada parada = new Parada(aux[0].strip(), aux[1].strip());
                    linea.agregar(parada);
                    continue;
                }
                Parada parada = new Parada(nombresParadas[i][j].strip());
                linea.agregar(parada);
            }
            this.agregarLinea(linea);
        }
        return true;
    }

    /**
     * Toma el archivo de la red .json y carga todos los datos.
     * 
     * @param nombreArchivo el nombre del archivo .json.
     * @return true si se cargo el archivo o false en caso contrario
     */
    public boolean cargarArchivo(String nombreArchivo) {
        this.datosRedArchivo.setNombreArchivo(nombreArchivo);
        return this.cargarArchivo();
    }

    public void cargarGrafo() {
        this.grafo.vaciar();
        for (int i = 0; i < this.size(); i++) {
            String[] nombreParadas = this.get(i).getParadas();
            for (int j = 0; j < nombreParadas.length; j++) {
                String[] paradas;
                String adyacenteAnterior = null;
                String adyacenteSiguiente = null;
                if (j > 0) {
                    if (nombreParadas[j - 1].contains(":")) {

                        adyacenteAnterior = nombreParadas[j - 1].split(":")[0].strip();
                    } else {
                        adyacenteAnterior = nombreParadas[j - 1].strip();
                    }
                }
                if (j < nombreParadas.length - 1) {
                    if (nombreParadas[j + 1].contains(":")) {
                        adyacenteSiguiente = nombreParadas[j + 1].split(":")[0].strip();
                    } else {
                        adyacenteSiguiente = nombreParadas[j + 1].strip();
                    }
                }
                if (nombreParadas[j].contains(":")) {
                    paradas = nombreParadas[j].split(":");
                    this.grafo.agregarVertice(paradas[0].strip());
                    this.grafo.agregarVertice(paradas[1].strip());
                    this.grafo.agregarAdyacente(paradas[0].strip(), paradas[1].strip(), 0);
                } else {
                    paradas = new String[1];
                    paradas[0] = nombreParadas[j].strip();
                    this.grafo.agregarVertice(nombreParadas[j].strip());
                }
                if (adyacenteAnterior != null) {
                    this.grafo.agregarVertice(adyacenteAnterior);
                    this.grafo.agregarAdyacente(paradas[0].strip(), adyacenteAnterior.strip(), 1);
                }
                if (adyacenteSiguiente != null) {
                    this.grafo.agregarVertice(adyacenteSiguiente);
                    this.grafo.agregarAdyacente(paradas[0].strip(), adyacenteSiguiente.strip(), 1);
                }
            }
        }
    }

    public String grafoToString() {
        return this.grafo.toString();
    }

    /**
     * Devuelve el String con una representación de la red.
     */
    public String toString() {
        String txt = "Red: " + this.getNombre() + "\n";

        for (int i = 0; i < this.size(); i++) {
            txt += "    Linea: " + this.get(i).getNombre() + "\n";
            for (int j = 0; j < this.get(i).size(); j++) {
                txt += "        " + this.get(i).get(j).toString() + "\n";
            }
        }
        return txt;
    }

    /**
     * Carga la información de la red en el grafo.
     * Vaciando todo su contenido anterior.
     */
    public void cargarGrafo() {
        this.grafo.vaciar();
        for (int i = 0; i < this.size(); i++) {
            String[] nombreParadas = this.get(i).getParadas();
            for (int j = 0; j < nombreParadas.length; j++) {
                String[] paradas;
                String adyacenteAnterior = null;
                String adyacenteSiguiente = null;
                if (j > 0) {
                    if (nombreParadas[j - 1].contains(":")) {

                        adyacenteAnterior = nombreParadas[j - 1].split(":")[0].strip();
                    } else {
                        adyacenteAnterior = nombreParadas[j - 1].strip();
                    }
                }
                if (j < nombreParadas.length - 1) {
                    if (nombreParadas[j + 1].contains(":")) {
                        adyacenteSiguiente = nombreParadas[j + 1].split(":")[0].strip();
                    } else {
                        adyacenteSiguiente = nombreParadas[j + 1].strip();
                    }
                }
                if (nombreParadas[j].contains(":")) {
                    paradas = nombreParadas[j].split(":");
                    this.grafo.agregarVertice(paradas[0].strip());
                    this.grafo.agregarVertice(paradas[1].strip());
                    this.grafo.agregarAdyacente(paradas[0].strip(), paradas[1].strip(), 0);
                } else {
                    paradas = new String[1];
                    paradas[0] = nombreParadas[j].strip();
                    this.grafo.agregarVertice(nombreParadas[j].strip());
                }
                if (adyacenteAnterior != null) {
                    this.grafo.agregarVertice(adyacenteAnterior);
                    this.grafo.agregarAdyacente(paradas[0].strip(), adyacenteAnterior.strip(), 1);
                }
                if (adyacenteSiguiente != null) {
                    this.grafo.agregarVertice(adyacenteSiguiente);
                    this.grafo.agregarAdyacente(paradas[0].strip(), adyacenteSiguiente.strip(), 1);
                }
            }
        }
    }

    /**
     * Devuelve el valor de T del grafo.
     * 
     * @return T
     */
    public int getT() {
        return this.grafo.getT();
    }

    /**
     * Cambia el valor de T del grafo.
     * 
     * @param t nuevo valor de T
     */
    public void setT(int t) {
        this.grafo.setT(t);
    }

    /**
     * Agrega una sucursal al grafo.
     * 
     * @param nombre el nombre de la sucursal
     * @return true si se agrego o false si ya existe
     */
    public boolean agregarSucursal(String nombre) {
        return this.grafo.agregarSucursal(nombre);
    }

    /**
     * Elimina una sucursal del grafo.
     * 
     * @param nombre el nombre de la sucursal
     * @return true si se elimino o false si no existe
     */
    public boolean removerSucursal(String nombre) {
        return this.grafo.removerSucursal(nombre);
    }

    /**
     * Cambia el arreglo de sucursales del grafo.
     * 
     * @param sucursales el nuevo arreglo de sucursales
     * @return true si se pudo cambiar o false en caso contrario
     */
    public boolean setSucursales(String[] sucursales) {
        return this.grafo.setSucursales(sucursales);
    }

    /**
     * Vaciado de la lista de sucursales.
     */
    public void vaciarSucursales() {
        this.grafo.vaciarSucursales();
    }

    /**
     * Devuelve el arreglo de sucursales del grafo.
     * 
     * @return el arreglo de sucursales
     */
    public String[] getSucursales() {
        return this.grafo.getSucursales();
    }

    /**
     * Devuelve el String con una representación del grafo.
     * 
     * @return el String
     */
    public String grafoToString() {
        return this.grafo.toString();
    }

    /**
     * Solo un main para probar cosas.
     */
    public static void main(String[] args) {
        Red red = new Red(".\\data\\Caracas.json");
        // Red red = new Red(".\\data\\Bogota.json");
        red.cargarArchivo();
        // System.out.println(red.toString());

        // for (int i = 0; i < red.size(); i++) {
        // System.out.print(red.get(i).getNombre() + ": ");
        // System.out.println(red.get(i).toString());
        // System.out.println();
        // }
        red.cargarGrafo();
        System.out.println(red.grafoToString());
        // System.out.println("El grafo es conexo? " + red.grafo.esConexo());
        red.grafo.setT(3);
        red.grafo.agregarSucursal("Propatria");
        // red.grafo.agregarSucursal("Terminal");

        // System.out.println("El numero de vertices es: " +
        // red.grafo.getVertices().length);

        // System.out.print("Las sucursales recomendadas son: ");
        String[] sucursales = red.grafo.recomendarSucursales();

        for (int i = 0; i < sucursales.length; i++) {
            System.out.print(" " + sucursales[i] + " ");
        }
        System.out.println();

    }

}