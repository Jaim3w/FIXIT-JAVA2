package Vistas;

import Controlador.ctrlRegistro2;
import Modelo.Usuarios;
import Modelo.mdlRegistro2;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import javax.swing.JFrame;

public class frmRegistroParte2 extends javax.swing.JFrame {

    public frmRegistroParte2() {
        
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
    
    public static void initfrmRegistroParte2(){
        frmRegistroParte2 vista = new frmRegistroParte2();
        mdlRegistro2 modelo = new mdlRegistro2();
        Usuarios mUsuarios = new Usuarios();
        ctrlRegistro2 controlador = new ctrlRegistro2(modelo, vista, mUsuarios);

        vista.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        blackRoundedPanelF1 = new Vistas.elementosTwo.BlackRoundedPanelF();
        jLabel3 = new javax.swing.JLabel();
        cmbCorreoR = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblImagenEmpleado = new javax.swing.JLabel();
        btnSubirImagenR = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        btnGuardarR = new Vistas.elemetos.ButtonC();
        txtDuiR = new Vistas.elemetos.txtRedondeadoamarillo();
        txtNombresR = new Vistas.elemetos.txtRedondeadoamarillo();
        txtApellidosR = new Vistas.elemetos.txtRedondeadoamarillo();
        txtTelefonoR = new Vistas.elemetos.txtRedondeadoamarillo();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        blackRoundedPanelF1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("D.U.I");
        blackRoundedPanelF1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 400, -1, 10));

        cmbCorreoR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        blackRoundedPanelF1.add(cmbCorreoR, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 450, 330, 30));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Correo electrónico");
        blackRoundedPanelF1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, 100, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombres");
        blackRoundedPanelF1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 510, 50, 20));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellidos");
        blackRoundedPanelF1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 570, 50, -1));

        lblImagenEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        lblImagenEmpleado.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblImagenEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        blackRoundedPanelF1.add(lblImagenEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 140, 120, 90));

        btnSubirImagenR.setBackground(new java.awt.Color(255, 255, 255));
        btnSubirImagenR.setForeground(new java.awt.Color(0, 0, 0));
        btnSubirImagenR.setText("Subir Imagen");
        btnSubirImagenR.setToolTipText("");
        blackRoundedPanelF1.add(btnSubirImagenR, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, -1, 30));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha de nacimiento");
        blackRoundedPanelF1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, -1, 20));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Número de teléfono");
        blackRoundedPanelF1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 620, -1, -1));

        blackRoundedPanelF1.add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 330, 340, 30));

        btnGuardarR.setText("Registrarse");
        blackRoundedPanelF1.add(btnGuardarR, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 680, 160, -1));
        blackRoundedPanelF1.add(txtDuiR, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 390, 330, -1));
        blackRoundedPanelF1.add(txtNombresR, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 510, 330, -1));
        blackRoundedPanelF1.add(txtApellidosR, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 560, 330, -1));
        blackRoundedPanelF1.add(txtTelefonoR, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 610, 330, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icminimize.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        blackRoundedPanelF1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 10, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icexit.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        blackRoundedPanelF1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/imgregistrarsefondodos.png"))); // NOI18N
        blackRoundedPanelF1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(blackRoundedPanelF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 840));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel9MouseClicked

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
            java.util.logging.Logger.getLogger(frmRegistroParte2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegistroParte2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegistroParte2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegistroParte2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRegistroParte2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vistas.elementosTwo.BlackRoundedPanelF blackRoundedPanelF1;
    public Vistas.elemetos.ButtonC btnGuardarR;
    public javax.swing.JButton btnSubirImagenR;
    public javax.swing.JComboBox<String> cmbCorreoR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JLabel lblImagenEmpleado;
    public Vistas.elemetos.txtRedondeadoamarillo txtApellidosR;
    public Vistas.elemetos.txtRedondeadoamarillo txtDuiR;
    public com.toedter.calendar.JDateChooser txtFechaNacimiento;
    public Vistas.elemetos.txtRedondeadoamarillo txtNombresR;
    public Vistas.elemetos.txtRedondeadoamarillo txtTelefonoR;
    // End of variables declaration//GEN-END:variables
}
