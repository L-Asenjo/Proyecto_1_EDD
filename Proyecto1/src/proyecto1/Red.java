/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

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

    /**
     * Constructor por defecto.
     */
    public Red() {
        this.nombre = null;
        this.datosRedArchivo = new DatosRedArchivo();
        this.jsonRedParser = new JsonRedParser();
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
            if (i == -1) {
                return false;
            }
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
            if (i == -1) {
                return false;
            }
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
     * Solo un main para probar cosas.
     */
    public static void main(String[] args) {
        // Red red = new Red(".\\data\\Caracas.json");
        Red red = new Red(".\\data\\Bogota.json");
        red.cargarArchivo();
        // System.out.println(red.toString());

        for (int i = 0; i < red.size(); i++) {
            System.out.print(red.get(i).getNombre() + ": ");
            System.out.println(red.get(i).toString());
            System.out.println();
        }

    }

}
