package Vistas;

import Controlador.ctrlRegistro2;
import Modelo.Usuarios;
import Modelo.mdlRegistro2;

public class frmRegistroParte2 extends javax.swing.JFrame {

    public frmRegistroParte2() {
        initComponents();
        this.setLocationRelativeTo(null);
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

        radialGradientPanel1 = new Vistas.elemetos.RadialGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDuiR = new Vistas.elemetos.TxtBoxRedondeado();
        jLabel3 = new javax.swing.JLabel();
        cmbCorreoR = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNombresR = new Vistas.elemetos.TxtBoxRedondeado();
        jLabel5 = new javax.swing.JLabel();
        txtApellidosR = new Vistas.elemetos.TxtBoxRedondeado();
        jLabel6 = new javax.swing.JLabel();
        lblImagenEmpleado = new javax.swing.JLabel();
        btnSubirImagenR = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTelefonoR = new Vistas.elemetos.TxtBoxRedondeado();
        btnGuardarR = new Vistas.elemetos.ButtonA();
        jLabel2 = new javax.swing.JLabel();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        radialGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registrarse");
        radialGradientPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 130, 40));
        radialGradientPanel1.add(txtDuiR, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 78, 240, 30));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("D.U.I");
        radialGradientPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 40, 10));

        cmbCorreoR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        radialGradientPanel1.add(cmbCorreoR, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 250, 30));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Correo electrónico");
        radialGradientPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, -1));
        radialGradientPanel1.add(txtNombresR, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 250, 30));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombres");
        radialGradientPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 60, 20));
        radialGradientPanel1.add(txtApellidosR, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 250, 30));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellidos");
        radialGradientPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 60, -1));

        lblImagenEmpleado.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        radialGradientPanel1.add(lblImagenEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 120, 90));

        btnSubirImagenR.setText("Subir Imagen");
        btnSubirImagenR.setToolTipText("");
        radialGradientPanel1.add(btnSubirImagenR, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, 30));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha de nacimiento");
        radialGradientPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 140, 20));
        radialGradientPanel1.add(txtTelefonoR, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 250, 30));

        btnGuardarR.setText("Registrarme");
        radialGradientPanel1.add(btnGuardarR, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 160, 50));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Número de teléfono");
        radialGradientPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));
        radialGradientPanel1.add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 240, 30));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/Diseño sin título.png"))); // NOI18N
        radialGradientPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 540));

        getContentPane().add(radialGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    public Vistas.elemetos.ButtonA btnGuardarR;
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
    public javax.swing.JLabel lblImagenEmpleado;
    private Vistas.elemetos.RadialGradientPanel radialGradientPanel1;
    public transient Vistas.elemetos.TxtBoxRedondeado txtApellidosR;
    public Vistas.elemetos.TxtBoxRedondeado txtDuiR;
    public com.toedter.calendar.JDateChooser txtFechaNacimiento;
    public Vistas.elemetos.TxtBoxRedondeado txtNombresR;
    public Vistas.elemetos.TxtBoxRedondeado txtTelefonoR;
    // End of variables declaration//GEN-END:variables
}
