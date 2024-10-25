/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Clase que obtiene el string del archivo de texto dado.
 */
public class DatosRedArchivo {
    /**
     * El nombre del archivo.
     */
    String nombreArchivo;

    /**
     * Constructor vacío.
     */

    public DatosRedArchivo() {
        this.nombreArchivo = null;
    }

    /**
     * Constructor con argumentos.
     * 
     * @param nombreArchivo el nombre del archivo
     */
    public DatosRedArchivo(String nombreArchivo) {
        this.setNombreArchivo(nombreArchivo);
    }

    /**
     * Getter del nombre del archivo.
     * 
     * @return el nombre del archivo
     */
    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    /**
     * Setter del nombre del archivo.
     * 
     * @param nombreArchivo el nuevo nombre del archivo
     */
    public void setNombreArchivo(String nombreArchivo) {
        if (nombreArchivo == null || nombreArchivo.length() == 0) {
            throw new IllegalArgumentException("El nombre del archivo no puede estar vacío");
        }
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Obtiene el string del archivo de texto dado. Cambia el valor de el atributo
     * nombreArchivo.
     * antes de ser llamado para obtener el string del archivo.
     * 
     * @param nombreArchivo el nombre del archivo
     * @return el string del archivo
     */
    public String ObtenerDatos(String nombreArchivo) {
        this.setNombreArchivo(nombreArchivo);
        return this.ObtenerDatos();
    }

    /**
     * Obtiene el string del archivo cuyo nombre se encuentra en el atributo
     * nombreArchivo.
     * 
     * @return el string del archivo
     */
    public String ObtenerDatos() {
        String text = null;
        try {
            Path path = Paths.get(this.nombreArchivo);
            if (!Files.exists(path)) {
                throw new IllegalArgumentException("El archivo no existe");
            }
            text = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * Solo para hacer unas pruebas.
     * 
     * @param args
     */
    public static void main(String[] args) {
        String nombreArchivo = "./data/Caracas.json";
        String json = new DatosRedArchivo().ObtenerDatos(nombreArchivo);
        System.out.println(json);
    }
}
