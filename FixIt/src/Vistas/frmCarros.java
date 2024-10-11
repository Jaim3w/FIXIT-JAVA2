/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Controlador.ctrlCarros;
import Modelo.ClientesCarro;
import Modelo.ModeloCarro;
import Modelo.mdlCarros;
import Vistas.elemetos.RoundedWhitePanel;

public class frmCarros extends RoundedWhitePanel {


    public frmCarros() {
        super(20, 20);
        initComponents();
        
        frmCarros vista = this;
        mdlCarros modelo = new mdlCarros();
        ClientesCarro mClientes = new ClientesCarro();
        ModeloCarro mModelo = new ModeloCarro();
        ctrlCarros controlador = new ctrlCarros(modelo, vista, mClientes, mModelo);

        vista.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDescripcionCarro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPlacaCarro = new javax.swing.JFormattedTextField();
        cmbClienteCarro = new javax.swing.JComboBox<>();
        cmbModeloCarro = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnSubirImagen = new javax.swing.JButton();
        lblImagenCarro = new javax.swing.JLabel();
        btnActualizarCarro = new Vistas.elemetos.ButtonA();
        jLabel2 = new javax.swing.JLabel();
        btnGuardarCarro = new Vistas.elemetos.ButtonA();
        btnEliminarCarro = new Vistas.elemetos.ButtonA();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListaCarros = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtColorCarro = new javax.swing.JFormattedTextField();
        BtnLimpiarcamposCarro = new javax.swing.JButton();
        txtAnoCarro = new javax.swing.JTextField();
        txtBuscarCarro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtIngresoCarro = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(922, 703));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Carros");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 170, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel3.setText("Color del vehiculo:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel7.setText("Fecha de ingreso");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel5.setText("Año del vehiculo");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, -1, -1));
        add(txtDescripcionCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 200, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel10.setText("Cliente");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 40, 20));
        add(txtPlacaCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 210, 30));

        cmbClienteCarro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cmbClienteCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 210, 30));

        cmbModeloCarro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbModeloCarro.setToolTipText("");
        add(cmbModeloCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 220, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel9.setText("Descripcion");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, -1, -1));

        btnSubirImagen.setText("Subir Imagen");
        add(btnSubirImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, -1, -1));
        add(lblImagenCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 200, 110));

        btnActualizarCarro.setText("Actualizar");
        btnActualizarCarro.setToolTipText("");
        add(btnActualizarCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 340, 120, -1));
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, -1, -1));

        btnGuardarCarro.setText("Guardar");
        btnGuardarCarro.setToolTipText("");
        add(btnGuardarCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 120, -1));

        btnEliminarCarro.setText("Eliminar");
        btnEliminarCarro.setToolTipText("");
        add(btnEliminarCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 120, -1));

        tbListaCarros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbListaCarros);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 710, 240));
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 510, 30, 30));
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 370, 30, 10));
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 540, 70, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel11.setText("Modelo:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));
        add(txtColorCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 220, 30));

        BtnLimpiarcamposCarro.setText("limpiar");
        add(BtnLimpiarcamposCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 120, 30));
        add(txtAnoCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 190, 30));
        add(txtBuscarCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 410, 30));

        jLabel12.setText("Buscar");
        jLabel12.setToolTipText("");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 70, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel13.setText("Placa del Vehiculo");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        txtIngresoCarro.setText("jLabel14");
        add(txtIngresoCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 90, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BtnLimpiarcamposCarro;
    public Vistas.elemetos.ButtonA btnActualizarCarro;
    public Vistas.elemetos.ButtonA btnEliminarCarro;
    public Vistas.elemetos.ButtonA btnGuardarCarro;
    public javax.swing.JButton btnSubirImagen;
    public javax.swing.JComboBox<String> cmbClienteCarro;
    public javax.swing.JComboBox<String> cmbModeloCarro;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblImagenCarro;
    public javax.swing.JTable tbListaCarros;
    public javax.swing.JTextField txtAnoCarro;
    public javax.swing.JTextField txtBuscarCarro;
    public javax.swing.JFormattedTextField txtColorCarro;
    public javax.swing.JTextField txtDescripcionCarro;
    public javax.swing.JLabel txtIngresoCarro;
    public javax.swing.JFormattedTextField txtPlacaCarro;
    // End of variables declaration//GEN-END:variables
}
