/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Controlador.ctrlModelo;
import Modelo.ModeloCarro;
import Modelo.mdlModelo;
import Vistas.elementosTwo.TransparenteRoundedPanel;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

public class frmModelo extends JFrame{

    public frmModelo() {
        initComponents();
        
        mdlModelo modelo = new mdlModelo();
        frmModelo vista = this;
        ModeloCarro mMarca = new ModeloCarro(); 
        ctrlModelo controlador = new ctrlModelo(modelo, vista, mMarca);
        
        vista.setVisible(true);
        setSize (830, 540);
        setResizable(false);
    }
    
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
            java.util.logging.Logger.getLogger(frmNuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmModelo().setVisible(true);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbModelos = new javax.swing.JTable();
        txtNombre = new javax.swing.JTextField();
        cmbMarca = new javax.swing.JComboBox<>();
        btnNewMarca = new javax.swing.JButton();
        txtBuscar = new Vistas.elementosTwo.MyTextField();
        btnAgregar = new Vistas.elemetos.ButtonWI();
        btnActualizar = new Vistas.elemetos.ButtonWI();
        btnLimpiar = new Vistas.elemetos.ButtonWI();
        btnEliminar = new Vistas.elemetos.ButtonWI();

        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar un nuevo modelo:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Modelo:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 113, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Marca:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        tbModelos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbModelos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 760, 190));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 113, 320, 30));

        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cmbMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 320, 30));

        btnNewMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/mas.png"))); // NOI18N
        getContentPane().add(btnNewMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 40, -1));

        txtBuscar.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iconbuscar2.png"))); // NOI18N
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 320, -1));

        btnAgregar.setText("Guardar");
        btnAgregar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icguardaraloadriel.png"))); // NOI18N
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 250, -1));

        btnActualizar.setText("Editar");
        btnActualizar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iceditaralokevon.png"))); // NOI18N
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 250, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iclimpiaralojaime.png"))); // NOI18N
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 250, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icborraralojosue.png"))); // NOI18N
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 250, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Vistas.elemetos.ButtonWI btnActualizar;
    public Vistas.elemetos.ButtonWI btnAgregar;
    public Vistas.elemetos.ButtonWI btnEliminar;
    public Vistas.elemetos.ButtonWI btnLimpiar;
    public javax.swing.JButton btnNewMarca;
    public javax.swing.JComboBox<String> cmbMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbModelos;
    public Vistas.elementosTwo.MyTextField txtBuscar;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
