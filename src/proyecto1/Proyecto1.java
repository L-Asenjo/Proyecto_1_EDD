/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;

import javax.swing.*;

/**
 * Clase principal del proyecto
 */
public class Proyecto1 extends JFrame  {
    /**
     * Red de transporte y es el objeto principal que permite interactuar con el
     * proyecto, contiene el grafo de la red de transporte y su representación
     * gráfica via GraphStream. @see Red
     */
    Red red = new Red();
    
    public Proyecto1() {
        /**
         * Ventana del menú principal del proyecto. Se crea una nueva instancia.
         * Y se pasa este Objeto para que se pueda acceder a la red.
         */
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(this);
        /**
         * Se hace visible.
         */
        ventanaPrincipal.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Proyecto1();
    }
    
}
