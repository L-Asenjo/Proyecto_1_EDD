/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1;

import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author Ivan
 */
public class SeleccionarArchivoForm extends javax.swing.JFrame {

    private Proyecto1 mainClass;
    private MenuPrincipalForm menuPrincipalForm;
    
    /**
     * Creates new form SeleccionarArchivoForm
     */
    public SeleccionarArchivoForm() {
        initComponents();
    }
    public SeleccionarArchivoForm(Proyecto1 mainClass, MenuPrincipalForm menuPrincipalForm) {
        setLocationRelativeTo(menuPrincipalForm);
        this.mainClass = mainClass;
        this.menuPrincipalForm = menuPrincipalForm;
        initComponents();
        File directorio = new File("./data/");
        pnSeleccionarArchivo.setCurrentDirectory(directorio);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSeleccionarArchivo = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnSeleccionarArchivo.setCurrentDirectory(new java.io.File("C:\\Program Files\\NetBeans-22\\.\\data"));
        pnSeleccionarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnSeleccionarArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnSeleccionarArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnSeleccionarArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnSeleccionarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnSeleccionarArchivoActionPerformed

        File selectedFile = pnSeleccionarArchivo.getSelectedFile();
        if (selectedFile == null){
            dispose();
            return;
        }
        mainClass.nombreArchivo = selectedFile.getAbsolutePath();
        dispose();
        MostrarArchivo mostrarArchivo = new MostrarArchivo(mainClass, menuPrincipalForm);
        mostrarArchivo.setVisible(true);
        return;
    }//GEN-LAST:event_pnSeleccionarArchivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SeleccionarArchivoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionarArchivoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionarArchivoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionarArchivoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeleccionarArchivoForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser pnSeleccionarArchivo;
    // End of variables declaration//GEN-END:variables
}
