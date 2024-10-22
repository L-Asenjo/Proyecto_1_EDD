/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;

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

    /**
     * Objeto que se encarga de parsear los datos de un archivo json con
     * los datos de una red.
     */

    private JsonRedParser jsonRedParser;

    /**
     * Objeto que se encarga de la representación de la red.
     */
    private Grafo grafo;

    /**
     * Implementación de Grafos en GraphStream. Usada para representar la
     * Interfaz gráfica del grafo.
     */
    private Graph gsGraph;

    /**
     * Constructor por defecto.
     */
    public Red() {
        this.nombre = null;
        this.datosRedArchivo = new DatosRedArchivo();
        this.jsonRedParser = new JsonRedParser();
        this.grafo = new Grafo();
        this.gsGraph = new SingleGraph("GrafoRedDeTransporte");

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
        this.gsGraph = new SingleGraph("GrafoRedDeTransporte");
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
        this.gsGraph.setAttribute("ui.title", this.getNombre());
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
     * Getter del grafo de GraphStream.
     */
    public Graph getGsGraph() {
        return this.gsGraph;
    }

    /**
     * Setter del grafo de GraphStream.
     */

    public void setGsGraph(Graph gsGraph) {
        this.gsGraph = gsGraph;
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
     * Carga el grafo GsGraph de la red usando los vertices y adyacentes de
     * this.grafo.
     */
    public void cargarGsGraph() {
        String[] vertices = this.grafo.getVertices();
        this.gsGraph.clear();
        for (int i = 0; i < vertices.length; i++) {
            if (this.gsGraph.getNode(vertices[i]) == null) {
                this.gsGraph.addNode(vertices[i]);
                Node node = this.gsGraph.getNode(vertices[i]);
                node.setAttribute("ui.label", vertices[i]);
//                node.setAttribute("ui.style", "size: 8px; fill-color: red;");
                node.setAttribute("ui.style", "size: 8px; stroke-mode: plain; stroke-color: green; stroke-width: 3px; fill-color: rgba(0,0,0,0);");
//                node.setAttribute("ui.style", "size: 8px; stroke-mode: plain; stroke-color: blue; stroke-width: 3px; fill-color: rgba(0,0,0,0);");


            }
            Vertice vertice = this.grafo.buscarVertice(vertices[i]);
            Adyacente[] adyacentes = vertice.getAdyacentes();
            for (int j = 0; j < adyacentes.length; j++) {
                if (this.gsGraph.getNode(adyacentes[j].nombre) == null) {
                    this.gsGraph.addNode(adyacentes[j].nombre);
                    Node node = this.gsGraph.getNode(adyacentes[j].nombre);
                    node.setAttribute("ui.label", adyacentes[j].nombre);
//                    node.setAttribute("ui.style", "size: 8px; fill-color: red;");
                    node.setAttribute("ui.style", "size: 8px; stroke-mode: plain; stroke-color: green; stroke-width: 3px; fill-color: rgba(0,0,0,0);");
//                    node.setAttribute("ui.style", "size: 8px; stroke-mode: plain; stroke-color: blue; stroke-width: 3px; fill-color: rgba(0,0,0,0);");


                }
                String nombreOrigenDestino = vertice.getNombre() + "-" + adyacentes[j].nombre;
                String nombreDestinoOrigen = adyacentes[j].nombre + "-" + vertice.getNombre();
                if (this.gsGraph.getEdge(nombreOrigenDestino) == null
                        && this.gsGraph.getEdge(nombreDestinoOrigen) == null) {
                    this.gsGraph.addEdge(nombreOrigenDestino, vertice.getNombre(), adyacentes[j].nombre, false);
                    Edge edge = this.gsGraph.getEdge(nombreOrigenDestino);
                    edge.setAttribute("peso", adyacentes[j].peso);
                    edge.setAttribute("ui.label", adyacentes[j].peso);
                }
            }
        }
    }

   
    public String gsGraphToString() {
        String txt = "Grafo: " + this.gsGraph.toString() + "\n";
        for (int i = 0; i < this.gsGraph.getNodeCount(); i++) {
            txt += "Vertice: " + this.gsGraph.getNode(i).toString() + "\n";
        }
        for (int i = 0; i < this.gsGraph.getEdgeCount(); i++) {
            txt += "Arista: " + this.gsGraph.getEdge(i).getId().toString() + " Peso: "
                    + this.gsGraph.getEdge(i).getAttribute("peso") + "\n";
        }
        return txt;
    }

    /**
     * Comprueba si el grafo GsGraph es vacío.
     * 
     * @return true si el grafo GsGraph es vacío, false en caso contrario
     */
    public boolean gsGraphVacio() {
        return this.gsGraph.getNodeCount() == 0;
    }

    /**
     * Comprueba si el grafo es vacío.
     * 
     * @return true si el grafo es vacío, false en caso contrario
     */
    public boolean grafoVacio() {
        return this.grafo.getVerticesSize() == 0;
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
        // System.out.println(red.grafoToString());
        // System.out.println("El grafo es conexo? " + red.grafo.esConexo());
        // red.grafo.setT(3);
        // red.grafo.agregarSucursal("Propatria");
        // red.grafo.agregarSucursal("Terminal");

        // System.out.println("El numero de vertices es: " +
        // red.grafo.getVertices().length);

        // System.out.print("Las sucursales recomendadas son: ");
        // String[] sucursales = red.grafo.recomendarSucursales();
        // for (int i = 0; i < sucursales.length; i++) {
        // System.out.print(" " + sucursales[i] + " ");
        // }
        // System.out.println();
        red.cargarGsGraph();
        System.out.println(red.gsGraphToString());

    }

}
