/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Clase que representa una red de transporte. Implementada con LinkedList.
 */

public class RedLL extends LinkedList<LineaLL> {

    /**
     * Nombre de la red.
     */
    private String nombre;
    /**
     * Nombre del archivo que contiene o va a contener la información de la red.
     */
    private String nombreArchivo;

    /**
     * Constructor por defecto.
     */
    public RedLL() {
        this.nombre = null;
        this.nombreArchivo = null;
    }

    /**
     * Constructor con parámetros.
     * 
     * @param nombreArchivo nombre del archivo que contiene o va a contener la
     *                      información de la red.
     */

    public RedLL(String nombreArchivo) {
        super();
        this.setNombreArchivo(nombreArchivo);
    }

    /**
     * Constructor con parámetros.
     * 
     * @param nombre        Nombre de la red.
     * 
     * @param nombreArchivo nombre del archivo que contiene o va a contener
     *                      laUsageId de la red.
     */
    public RedLL(String nombre, String nombreArchivo) {
        super();
        this.setNombre(nombre);
        setNombreArchivo(nombreArchivo);
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
     * Setter de nombre.
     * 
     * @param nombre el nuevo nombre de la red
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.length() == 0) {
            throw new IllegalArgumentException("El nombre de la red no puede estar vacío");
        }
        this.nombre = nombre;
    }

    /**
     * Getter de nombreArchivo.
     * 
     * @return el nombre del archivo
     */
    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    /**
     * Setter de nombreArchivo.
     * 
     * @param nombreArchivo el nuevo nombre del archivo
     */
    public void setNombreArchivo(String nombreArchivo) {
        if (nombreArchivo == null || nombreArchivo.length() == 0) {
            throw new IllegalArgumentException("El nombre del archivo no puede estar vacío");
        }
        this.nombreArchivo = nombreArchivo.strip();
    }

    /**
     * Devuelve un array con los nombres de las lineas de la red.
     * 
     * @return un array con los nombres de las lineas de la red
     */

    public String[] getNombreLineas() {
        if (vacia()) {
            return null;
        }
        String[] lineas = new String[this.size()];
        for (int i = 0; i < this.size(); i++) {
            lineas[i] = this.getValor(i).getNombre();
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
        if (vacia()) {
            return null;
        }
        if (index < 0 || index >= this.size()) {
            return null;
        }
        return this.getValor(index).getParadas();
    }

    /**
     * Agrega una nueva linea a la red.
     * 
     * @param linea linea a agregar
     */
    public void agregarLinea(LineaLL linea) {
        if (!vacia()) {
            for (int i = 0; i < this.size(); i++) {
                if (this.getValor(i).getNombre().equals(linea.getNombre())) {
                    throw new IllegalArgumentException("Ya existe una linea con el nombre " + linea.getNombre());
                }
            }
        }
        this.agregar(linea);
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
     * @return el indice de la linea o -1 si no existe
     */
    public int buscarLineaPorNombre(String nombreLinea) {
        if (vacia()) {
            return -1;
        }
        for (int i = 0; i < size(); i++) {
            if (this.getValor(i).getNombre().equals(nombreLinea)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Vacia la red.
     */

    public void vaciarRed() {
        if (vacia()) {
            return;
        }
        for (int i = 0; i < size(); i++) {
            this.getValor(i).vaciarLinea();
        }
        this.vaciar();
    }

    /**
     * Toma el archivo de la red .json y obtiene el String con formato
     * Json y lo devuelve.
     * 
     * @return el contenido del archivo en String con formato Json.
     */
    public String cargarArchivo() {
        if (this.getNombreArchivo() == null) {
            throw new IllegalArgumentException("No se ha definido el nombre del archivo");
        }
        String contenido = null;
        try {
            Path rutaArchivo = Paths.get(this.getNombreArchivo());
            contenido = new String(Files.readAllBytes(rutaArchivo));
        } catch (IOException e) {
            e.printStackTrace();

        }
        return contenido;
    }

    /**
     * Toma el String con formato json, de la red y lo carga en el objeto Red. En
     * otras
     * palabras carga la información de toda la red en la estructura de datos
     * dada por Red.
     * 
     * @param contenido
     */
    public void deTxtJson(String contenido) {
        if (contenido == null) {
            throw new IllegalArgumentException("Hubo un error al cargar el archivo " + this.getNombreArchivo());
        }
        JSONObject json = null;
        try {
            json = new JSONObject(contenido);
        } catch (Exception e) {
            throw new IllegalArgumentException("El archivo " + this.getNombreArchivo() + " no es un archivo JSON");
        }
        this.vaciarRed();
        String nombreRed = json.names().getString(0);
        setNombre(nombreRed);
        JSONArray jsonLineas = json.getJSONArray(nombreRed);
        for (int i = 0; i < jsonLineas.length(); i++) {
            LineaLL linea = new LineaLL(jsonLineas.getJSONObject(i).names().getString(0));
            JSONArray jsonParadas = jsonLineas.getJSONObject(i).getJSONArray(linea.getNombre());
            for (int j = 0; j < jsonParadas.length(); j++) {
                if (jsonParadas.get(j) instanceof String) {
                    Parada parada = new Parada(jsonParadas.getString(j));
                    linea.agregarParada(parada);
                    continue;
                }
                String nombre = jsonParadas.getJSONObject(j).names().getString(0);
                String alias = jsonParadas.getJSONObject(j).getString(nombre);
                Parada parada = new Parada(nombre, alias);
                linea.agregarParada(parada);
            }
            this.agregarLinea(linea);
        }
    }

    /**
     * Toma la información de la red que esta en el objeto de la clase Red y lo
     * devuelve como un String en formato JSON.
     * 
     * @return el String en formato JSON
     */
    public String aTxtJson() {
        if (this.getNombre() == null) {
            throw new IllegalArgumentException("No se ha definido el nombre de la red");
        }
        JSONObject json = new JSONObject();
        JSONArray jsonLineas = new JSONArray();
        for (int i = 0; i < size(); i++) {
            JSONArray jsonParadas = new JSONArray();
            for (int j = 0; j < getValor(i).size(); j++) {
                Parada parada = getValor(i).getValor(j);
                if (parada.getAlias() == null) {
                    jsonParadas.put(parada.getNombre());
                    continue;
                }
                JSONObject jsonParada = new JSONObject();
                jsonParada.put(parada.getNombre(), parada.getAlias());
                jsonParadas.put(jsonParada);
            }
            JSONObject lineaJson = new JSONObject();
            lineaJson.put(getValor(i).getNombre(), jsonParadas);
            jsonLineas.put(lineaJson);
        }
        json.put(getNombre(), jsonLineas);
        return json.toString();
    }

    /**
     * Guarda el String en formato Json en el archivo de la red.
     * 
     * @param contenido
     */
    public void guardarArchivo(String contenido) {
        if (this.getNombreArchivo() == null) {
            throw new IllegalArgumentException("No se ha definido el nombre del archivo");
        }
        try {
            Path rutaArchivo = Paths.get(this.getNombreArchivo());
            Files.write(rutaArchivo, contenido.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException("No se ha podido guardar el archivo " + this.getNombre());
        }
    }

    /**
     * Devuelve el String con una representación de la red.
     */
    @Override
    public String toString() {
        String txt = "Red: " + this.getNombre() + "\n";

        for (int i = 0; i < this.size(); i++) {
            txt += "    Linea: " + this.getValor(i).getNombre() + "\n";
            for (int j = 0; j < this.getValor(i).size(); j++) {
                txt += "        " + this.getValor(i).getValor(j).toString() + "\n";
            }
        }
        return txt;
    }

    /**
     * Solo un main para probar cosas.
     * @param args
     */
    public static void main(String[] args) {
        RedLL red = new RedLL(".\\data\\Caracas.json");
        String jsonTxt = red.cargarArchivo();
        red.deTxtJson(jsonTxt);
        System.out.println(red.toString());
        jsonTxt = red.aTxtJson();
        red.setNombreArchivo(".\\data\\prueba.json");
        red.guardarArchivo(jsonTxt);

    }

}

