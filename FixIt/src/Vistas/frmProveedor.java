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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProveedores = new javax.swing.JTable();
        txtBuscarProveedor = new Vistas.elementosTwo.MyTextField();
        btnGuardarProveedor = new Vistas.elemetos.ButtonWI();
        btnActualizarProveedor = new Vistas.elemetos.ButtonWI();
        btnEliminarProveedor = new Vistas.elemetos.ButtonWI();
        btnLimpiarCamposProveedor = new Vistas.elemetos.ButtonWI();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(153, 153, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Codigo de marca");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        txtCodigo.setInheritsPopupMenu(true);
        add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 290, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Telefono");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 50, -1));

        txtTelefono.setInheritsPopupMenu(true);
        add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 290, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Representante:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        txtRepresentante.setInheritsPopupMenu(true);
        add(txtRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 290, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Correo ");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, -1));

        txtCorreo.setInheritsPopupMenu(true);
        add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 290, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Marca");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        txtMarca.setInheritsPopupMenu(true);
        add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 290, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Direccion");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 50, -1));

        txtDireccion.setInheritsPopupMenu(true);
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, 290, -1));

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

        txtBuscarProveedor.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iconbuscar2.png"))); // NOI18N

        btnGuardarProveedor.setText("Guardar");
        btnGuardarProveedor.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icguardaraloadriel.png"))); // NOI18N

        btnActualizarProveedor.setText("Editar");
        btnActualizarProveedor.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iceditaralokevon.png"))); // NOI18N

        btnEliminarProveedor.setText("Eliminar");
        btnEliminarProveedor.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icborraralojosue.png"))); // NOI18N

        btnLimpiarCamposProveedor.setText("Limpiar");
        btnLimpiarCamposProveedor.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iclimpiaralojaime.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiarCamposProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarCamposProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 980, 320));
    }// </editor-fold>//GEN-END:initComponents

    private void tbProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProveedoresMouseClicked

    }//GEN-LAST:event_tbProveedoresMouseClicked


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Vistas.elemetos.ButtonWI btnActualizarProveedor;
    public Vistas.elemetos.ButtonWI btnEliminarProveedor;
    public Vistas.elemetos.ButtonWI btnGuardarProveedor;
    public Vistas.elemetos.ButtonWI btnLimpiarCamposProveedor;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tbProveedores;
    public Vistas.elementosTwo.MyTextField txtBuscarProveedor;
    public javax.swing.JFormattedTextField txtCodigo;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtMarca;
    public javax.swing.JTextField txtRepresentante;
    public javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
