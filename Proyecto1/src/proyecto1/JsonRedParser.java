/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * Clase que obtiene la información de texto de una red de transporte de un
 * string de texto
 * con formato JSON
 */
public class JsonRedParser implements IRedParser {

    /**
     * Nombre de la red
     */
    private String nombreRed;
    /**
     * Nombres de las lineas
     */
    private String[] nombresLineas;
    /**
     * Nombres de las paradas para todas las lineas
     */
    private String[][] nombresParadas;
    /**
     * String de texto con toda la información de la red
     */
    private String json;

    /**
     * Constructor por defecto
     */
    public JsonRedParser() {
        this.nombreRed = null;
        this.nombresLineas = null;
        this.nombresParadas = null;
        this.json = null;
    }

    /**
     * Constructor con el string en formato json
     * 
     * @param json el string de texto en formato json
     */
    public JsonRedParser(String json) {
        this.nombreRed = null;
        this.nombresLineas = null;
        this.nombresParadas = null;
        this.json = json;
    }

    /**
     * Getter del nombre de la red
     */
    public String getNombreRed() {
        return this.nombreRed;
    }

    /**
     * Getter de los nombres de las lineas
     */
    public String[] getNombresLineas() {
        return this.nombresLineas;
    }

    /**
     * Getter de los nombres de las paradas
     */
    public String[][] getNombresParadas() {
        return this.nombresParadas;
    }

    /**
     * Setter del string de texto en formato json con toda la información de la red
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * Quita todos los caracteres no deseados del string json.
     */
    void stripTotal() {
        this.json = this.json.strip().replace("\r", "").replace("\n", "").replace("\"", "").strip();
    }

    /**
     * Obtiene el nombre de la red.
     */
    void obtenerRed() {
        // Quitando la primera y ultima llaves.
        this.json = json.substring(1, this.json.length() - 1).strip();
        String[] aux = this.json.split(":", 2);
        this.nombreRed = aux[0].strip();
        this.json = aux[1].substring(1, aux[1].length() - 1).strip();
    }

    /**
     * Separa la información por linea.
     */
    void depurarLineas() {
        int numLlavesAbiertas = 0;
        String jsonAux = "";
        for (int i = 0; i < this.json.length(); i++) {
            char c = this.json.charAt(i);
            if (c == '{') {
                numLlavesAbiertas++;
                continue;
            }
            if (c == '[') {
                continue;
            }
            if (c == ']') {
                continue;
            }
            if (c == '}') {
                numLlavesAbiertas--;
                if (numLlavesAbiertas == 0) {
                    jsonAux += "\n";
                    continue;
                }
                if (numLlavesAbiertas == 1) {
                    continue;
                }
            }
            if (c == ',') {
                if (numLlavesAbiertas == 0) {
                    continue;
                }
            }
            jsonAux += String.valueOf(c);
        }
        this.json = jsonAux.strip();
    }

    /**
     * Obtiene los nombres de las lineas y las paradas
     */
    void obtenerLineas() {
        String[] lineas = this.json.strip().split("\n");
        this.nombresLineas = new String[lineas.length];
        this.nombresParadas = new String[lineas.length][];
        for (int i = 0; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] aux = linea.split(":", 2);
            this.nombresLineas[i] = aux[0].strip();
            String paradas = aux[1].strip();
            this.nombresParadas[i] = paradas.split(",");
            for (int j = 0; j < this.nombresParadas[i].length; j++) {
                this.nombresParadas[i][j] = this.nombresParadas[i][j].strip();
            }
        }
        this.json = null;
    }

    /**
     * Ejecuta todos los pasos en orden, sobre el string de texto con formato json,
     * para obtener toda la información de la red
     */
    public void parse() {
        this.stripTotal();
        this.obtenerRed();
        this.depurarLineas();
        this.obtenerLineas();
    }

    /**
     * Ejecuta todos los pasos en orden, sobre el string de texto con formato json
     * dado,
     * para obtener toda la información de la red
     */
    public void parse(String json) {
        this.json = json;
        this.parse();
    }

    /**
     * Devuelve un string con toda la información de la red obtenida
     */
    @Override
    public String toString() {
        if (this.nombreRed == null || this.nombresLineas == null || this.nombresParadas == null) {
            return null;
        }
        String txt = "Red: " + this.nombreRed + "\n";
        for (int i = 0; i < this.nombresLineas.length; i++) {
            txt += ("    " + this.nombresLineas[i] + "\n");
            for (int j = 0; j < this.nombresParadas[i].length; j++) {
                txt += ("        " + this.nombresParadas[i][j] + "\n");
            }
        }
        return txt;
    }

    /**
     * Solo para hacer unas pruebas.
     * 
     * @param args
     */
    public static void main(String[] args) {

        // String nombreArchivo = "./data/Caracas.json";
        String nombreArchivo = "./data/Bogota.json";
        Path path = Paths.get(nombreArchivo);
        String json = null;
        try {
            json = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (json != null) {
            JsonRedParser parser = new JsonRedParser();
            parser.parse(json);
            System.out.println(parser.toString());
        }
    }
}
