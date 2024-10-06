/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Controlador.controladorCorreo;
import Modelo.Credenciales;
import Modelo.Usuarios;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author rdlfp
 */
public class EnviarcorreoE extends javax.swing.JFrame {


    public EnviarcorreoE() {
        setUndecorated(true);
        
         // Configuramos FlatLaf como el LookAndFeel
        try {
            FlatLightLaf.setup(); // Usamos FlatLaf en su versión clara
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        initComponents();
        
           // Aplicamos el borde redondeado al JFrame
        setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50)); // Bordes redondeados
        
        // Configuramos el color de fondo y el layout
        setBackground(new Color(0, 0, 0, 0)); // Fondo transparente

    }
    
    public static void initEnviarCorreoE(){
    
        Credenciales modelo=new Credenciales();
        EnviarcorreoE vista=new EnviarcorreoE();
         Usuarios modelito = new Usuarios();
        controladorCorreo controlador=new controladorCorreo(modelo, vista,modelito);
        
        vista.setVisible(true);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonA1 = new Vistas.elemetos.ButtonA();
        whiteRoundedPanelF1 = new Vistas.elementosTwo.WhiteRoundedPanelF();
        imgMinimize = new javax.swing.JLabel();
        imgExit = new javax.swing.JLabel();
        txtCorreo = new Vistas.elemetos.txtRedondeadonegro();
        btnenviar = new Vistas.elemetos.ButtonC();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        buttonA1.setText("buttonA1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        whiteRoundedPanelF1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icminimizew.png"))); // NOI18N
        imgMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMinimizeMouseClicked(evt);
            }
        });
        whiteRoundedPanelF1.add(imgMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 10, -1, -1));

        imgExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icexitw.png"))); // NOI18N
        imgExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgExitMouseClicked(evt);
            }
        });
        whiteRoundedPanelF1.add(imgExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 10, -1, -1));

        txtCorreo.setName(""); // NOI18N
        whiteRoundedPanelF1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 430, 670, 50));

        btnenviar.setForeground(new java.awt.Color(0, 0, 0));
        btnenviar.setText("Enviar codigo de verificación");
        btnenviar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        whiteRoundedPanelF1.add(btnenviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 520, 260, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ingresa tu correo electronico");
        whiteRoundedPanelF1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/recucontra2.png"))); // NOI18N
        whiteRoundedPanelF1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(whiteRoundedPanelF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 840));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void imgMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMinimizeMouseClicked
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_imgMinimizeMouseClicked

    private void imgExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgExitMouseClicked
        System.exit(0);  // Cierra la aplicación por completo
    }//GEN-LAST:event_imgExitMouseClicked

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
            java.util.logging.Logger.getLogger(EnviarcorreoE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnviarcorreoE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnviarcorreoE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnviarcorreoE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               initEnviarCorreoE();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Vistas.elemetos.ButtonC btnenviar;
    private Vistas.elemetos.ButtonA buttonA1;
    public javax.swing.JLabel imgExit;
    public javax.swing.JLabel imgMinimize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public Vistas.elemetos.txtRedondeadonegro txtCorreo;
    private Vistas.elementosTwo.WhiteRoundedPanelF whiteRoundedPanelF1;
    // End of variables declaration//GEN-END:variables
}
