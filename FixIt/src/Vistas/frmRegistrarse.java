/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Modelo.Usuarios;
import Controlador.controlador;
import Modelo.Roles;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class frmRegistrarse extends javax.swing.JFrame {

    public frmRegistrarse() {
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

    public static void initfrmRegistrarse(){
        Usuarios modelo = new Usuarios();
        frmRegistrarse vista = new frmRegistrarse();
        Roles mRoles = new Roles();
        controlador con = new controlador(modelo, vista, mRoles);
        vista.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        blackRoundedPanelF1 = new Vistas.elementosTwo.BlackRoundedPanelF();
        jLabel10 = new javax.swing.JLabel();
        cbComobox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnAgregarUser = new Vistas.elemetos.ButtonA();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtContra = new Vistas.elemetos.txtContrasenaredondeadaamarilla();
        txtCorreoUser = new Vistas.elemetos.txtRedondeadoamarillo();
        imgFixit = new Vistas.elemetos.ButtonC();
        fondo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(730, 659));

        blackRoundedPanelF1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("¿Ya tienes una cuenta?");
        blackRoundedPanelF1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 610, 140, -1));

        cbComobox.setBackground(new java.awt.Color(255, 255, 255));
        cbComobox.setForeground(new java.awt.Color(0, 0, 0));
        cbComobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbComobox.setOpaque(true);
        blackRoundedPanelF1.add(cbComobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 460, 410, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Correo");
        blackRoundedPanelF1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, 110, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Contraseña");
        blackRoundedPanelF1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 110, -1));

        btnAgregarUser.setText("Registrame");
        blackRoundedPanelF1.add(btnAgregarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 550, 180, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icexit.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        blackRoundedPanelF1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 10, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icminimize.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        blackRoundedPanelF1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 10, -1, -1));
        blackRoundedPanelF1.add(txtContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 350, 410, 40));
        blackRoundedPanelF1.add(txtCorreoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 260, 410, 40));

        imgFixit.setText("Iniciar sesión");
        blackRoundedPanelF1.add(imgFixit, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 640, 180, 50));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/imgregistrarsefondo.png"))); // NOI18N
        blackRoundedPanelF1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("¿Como quiero Registrame?");
        blackRoundedPanelF1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 430, 210, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(blackRoundedPanelF1, javax.swing.GroupLayout.PREFERRED_SIZE, 1350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(blackRoundedPanelF1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
    System.exit(0);  
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
    setState(JFrame.ICONIFIED); 
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(frmRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegistrarse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                initfrmRegistrarse();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Vistas.elementosTwo.BlackRoundedPanelF blackRoundedPanelF1;
    public Vistas.elemetos.ButtonA btnAgregarUser;
    public javax.swing.JComboBox<String> cbComobox;
    public javax.swing.JLabel fondo;
    public Vistas.elemetos.ButtonC imgFixit;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public Vistas.elemetos.txtContrasenaredondeadaamarilla txtContra;
    public Vistas.elemetos.txtRedondeadoamarillo txtCorreoUser;
    // End of variables declaration//GEN-END:variables
}
