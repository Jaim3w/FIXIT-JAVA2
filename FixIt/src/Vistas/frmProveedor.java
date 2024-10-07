/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

import Controlador.ctrlProveedor;
import Modelo.mdlProveedor;
import Vistas.elementosTwo.TransparenteRoundedPanel;
import Vistas.elemetos.RoundedWhitePanel;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author jjove
 */
public class frmProveedor extends TransparenteRoundedPanel{

    /**
     * Creates new form frmProveedor1
     */
    public frmProveedor() {
        initComponents();
        
         frmProveedor vista = this;
        mdlProveedor modelo = new mdlProveedor();
        ctrlProveedor controlador = new ctrlProveedor(modelo, vista);

        vista.setVisible(true);
        
        try {
            MaskFormatter mf = new MaskFormatter("####-####");
            mf.setPlaceholderCharacter('_');
            txtTelefono.setFormatterFactory(new DefaultFormatterFactory(mf));
        } catch (ParseException ex) {
               JOptionPane.showMessageDialog(this, "Error al aplicar formato al teléfono", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    
    
    //Warnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRepresentante = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnGuardarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        btnActualizarProveedor = new javax.swing.JButton();
        btnLimpiarCamposProveedor = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtBuscarProveedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProveedores = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(153, 153, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Agregar un nuevo proveedor");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 44, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Codigo de marca");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(116, 120, -1, -1));

        txtCodigo.setInheritsPopupMenu(true);
        add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 112, 200, 32));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Telefono");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 120, 50, -1));

        txtTelefono.setInheritsPopupMenu(true);
        add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 112, 200, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Representante:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 189, -1, -1));

        txtRepresentante.setInheritsPopupMenu(true);
        add(txtRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 179, 201, 36));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Correo ");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 189, -1, -1));

        txtCorreo.setInheritsPopupMenu(true);
        add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 200, 36));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Marca");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 243, -1, -1));

        txtMarca.setInheritsPopupMenu(true);
        add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 233, 201, 36));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Direccion");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 243, 50, -1));

        txtDireccion.setInheritsPopupMenu(true);
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 233, 200, 36));

        btnGuardarProveedor.setText("Guardar");
        btnGuardarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarProveedorMouseClicked(evt);
            }
        });
        add(btnGuardarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 308, -1, -1));

        btnEliminarProveedor.setText("Eliminar");
        add(btnEliminarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(357, 308, -1, -1));

        btnActualizarProveedor.setText("Actualizar");
        add(btnActualizarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 308, -1, -1));

        btnLimpiarCamposProveedor.setText("Limpiar");
        add(btnLimpiarCamposProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(588, 308, -1, -1));

        jLabel9.setText("Buscar:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 352, 50, -1));
        add(txtBuscarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 349, 687, -1));

        tbProveedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProveedoresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbProveedores);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 377, 773, 277));
    }// </editor-fold>//GEN-END:initComponents

    private void tbProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProveedoresMouseClicked

    }//GEN-LAST:event_tbProveedoresMouseClicked

    private void btnGuardarProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarProveedorMouseClicked

    }//GEN-LAST:event_btnGuardarProveedorMouseClicked


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizarProveedor;
    public javax.swing.JButton btnEliminarProveedor;
    public javax.swing.JButton btnGuardarProveedor;
    public javax.swing.JButton btnLimpiarCamposProveedor;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tbProveedores;
    public javax.swing.JTextField txtBuscarProveedor;
    public javax.swing.JFormattedTextField txtCodigo;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtMarca;
    public javax.swing.JTextField txtRepresentante;
    public javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
