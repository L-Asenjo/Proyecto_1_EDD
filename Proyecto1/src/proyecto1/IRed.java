/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 * Interfaz que define los métodos de una red de transporte.
 */
interface IRed {
    /**
     * Obtiene el nombre de la red
     * 
     * @return el nombre de la red
     */
    public String getNombre();

    /**
     * Cambia el nombre de la red
     * 
     * @param nombre el nuevo nombre de la red
     */
    public void setNombre(String nombre);

    /**
     * Obtiene el nombre del archivo que contiene los datos de la red.
     * 
     * @return el nombre del archivo
     */
    public String getNombreArchivo();

    /**
     * Cambia el nombre del archivo que contiene los datos de la red.
     * 
     * @param nombreArchivo el nuevo nombre del archivo
     */
    public void setNombreArchivo(String nombreArchivo);

    /**
     * Obtiene un arreglo con los nombres de las lineas de la red
     * 
     * @return un arreglo con los nombres de las lineas
     */
    public String[] getNombresLineas();

    /**
     * Obtiene un arreglo con las paradas de una linea
     * 
     * @param index el indice de la linea
     * @return un arreglo con las paradas
     */
    public String[] getParadasLinea(int index);

    /**
     * Agrega una nueva linea a la red.
     * 
     * @param linea linea a agregar
     * @return true si se agrego la linea o false en caso contrario
     */
    public boolean agregarLinea(Linea linea);

    /**
     * Agrega una nueva linea a la red.
     * 
     * @param nombreLineaString nombre de la linea a agregar
     * @return true si se agrego la linea o false en caso contrario
     */
    public boolean agregarLinea(String nombreLineaString);

    /**
     * Agrega una nueva parada a una linea. Cada elemento del array de paradas es un
     * string de alguno de estos formatos "{"nombreParada"":"alias"}",
     * "{nombreParada:alias}", "nombreParada:alias", "{"nombreParada"}",
     * "{nombreParada}", "nombreParada".
     * Si hay algún problema la línea y su parada no se agregan.
     * 
     * @param nombreLinea  nombre de la linea
     * @param nombreParada nombre de la parada
     * @return true si se agrego la parada o false en caso contrario
     */
    public boolean agregarParada(String nombreLinea, String nombreParada);

    /**
     * Agrega paradas a una linea. Cada elemento del array de paradas es un
     * string de alguno de estos formatos "{"nombreParada"":"alias"}",
     * "{nombreParada:alias}", "nombreParada:alias", "{"nombreParada"}",
     * "{nombreParada}", "nombreParada".
     * Si hay algún problema la línea y ninguna de sus paradas se agregan.
     * 
     * @param nombreLinea el nombre de la linea
     * @param paradas     un arreglo con nombres de paradas
     * @return true si se agrego la linea o false en caso contrario
     */

    public boolean agregarParadas(String nombreLinea, String[] paradas);

    /**
     * Remueve una linea, con el indice dado, de la red
     * 
     * @param index el indice de la linea
     */

    public void removerLinea(int index);

    /**
     * Busca una linea por su nombre y devuelve su indice o -1 si no se encuentra
     * 
     * @param nombreLinea el nombre de la linea
     * @return
     */
    public int buscarLineaPorNombre(String nombreLinea);

    /**
     * Vacía la red, sirve para cargar una nueva red.
     */
    public void vaciarRed();

    /**
     * Carga la información de una nueva red desde un archivo.
     * 
     * @return true si se cargo la red o false en caso contrario
     */
    public boolean cargarArchivo();

    /**
     * Carga la información de una nueva red desde un archivo.
     * 
     * @param nombreArchivo el nombre del archivo
     * @return true si se cargo la red o false en caso contrario
     */
    public boolean cargarArchivo(String nombreArchivo);

    /**
     * Devuelve una representación en cadena de la red
     * 
     * @return una representación en cadena de la red
     */

    public String toString();

}