/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Interfaz que define los métodos de un parser de red de transporte.
 */
interface IRedParser {
    /**
     * Obtiene el nombre de la red
     * 
     * @return el nombre de la red
     */
    public String getNombreRed();

    /**
     * Obtiene los nombres de las líneas
     * 
     * @return los nombres de las líneas
     */
    public String[] getNombresLineas();

    /**
     * Obtiene los nombres de las paradas para todas las líneas
     * 
     * @return los nombres de las paradas
     */

    public String[][] getNombresParadas();

    /**
     * Setter del atributo json
     * 
     * @param json el nuevo string json
     */
    public void setJson(String json);

    /**
     * Método que obtiene toda la información de la red, desde el string de texto.
     */
    public void parse();

    /**
     * Método que obtiene toda la información de la red, desde el string de texto.
     * 
     * @param json
     */
    public void parse(String json);

    /**
     * Método que devuelve un string con toda la información de la red obtenida
     * desde el string de texto.
     * 
     * @return
     */
    public String toString();
}
