/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;

import javax.swing.*;

public class Proyecto1 extends JFrame  {

    public String nombreArchivo;
    public Red red;
    
    public Proyecto1() {
        red = new Red();
        MenuPrincipalForm menuPrincipalForm = new MenuPrincipalForm(this);
        menuPrincipalForm.setVisible(true);
         new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Nombre Archivo: " + nombreArchivo);
            }
        }).start();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Proyecto1();
    }
    
}
