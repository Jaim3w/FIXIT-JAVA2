
package Vistas;

import Controlador.ctrlDetalleFactura;
import Modelo.Factura;
import Modelo.ProductoRep;
import Modelo.mdlAsignarOrden;
import Modelo.mdlDetalleFactura;
import Vistas.elementosTwo.TransparenteRoundedPanel;

public class frmDetalleFactura extends TransparenteRoundedPanel {

    public frmDetalleFactura() {
        initComponents();

        frmDetalleFactura vista = this;
                
        mdlDetalleFactura Modelo1 = new mdlDetalleFactura();         
        mdlAsignarOrden modelito = new mdlAsignarOrden();
        Factura modelito1 = new Factura();
        ProductoRep Modelo = new ProductoRep();
        ctrlDetalleFactura contro = new ctrlDetalleFactura(Modelo1, vista,modelito1,Modelo, modelito);
        vista.setVisible(true);
        
    }   


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetalleFactura = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbFactura = new javax.swing.JComboBox<>();
        cmbProductoRepuesto = new javax.swing.JComboBox<>();
        cmbAsignarOrden = new javax.swing.JComboBox<>();
        btnNuevaFactura = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new Vistas.elemetos.ButtonWI();
        btnEliminar = new Vistas.elemetos.ButtonWI();
        btnImprimirFac = new javax.swing.JButton();
        btnCargar = new Vistas.elemetos.ButtonWI();

        setBackground(new java.awt.Color(0, 0, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbDetalleFactura.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbDetalleFactura);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 409, 970, 230));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Detalle de Factura");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Factura a nombre de:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Producto o repuesto a pagar:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Servicio a pagar:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, -1, -1));

        cmbFactura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cmbFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 270, -1));

        cmbProductoRepuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cmbProductoRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 270, -1));

        cmbAsignarOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cmbAsignarOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 270, -1));

        btnNuevaFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icguardaraloadriel.png"))); // NOI18N
        add(btnNuevaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 30, 31));

        btnAgregar.setText("Guardar");
        btnAgregar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icguardaraloadriel.png"))); // NOI18N

        btnEliminar.setText("Eliminar");
        btnEliminar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icborraralojosue.png"))); // NOI18N

        btnImprimirFac.setBackground(new java.awt.Color(153, 153, 153));
        btnImprimirFac.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimirFac.setText("Imprimir factura");

        btnCargar.setText("Limpiar");
        btnCargar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iclimpiaralojaime.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btnImprimirFac)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimirFac)
                    .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(243, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1000, 290));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Vistas.elemetos.ButtonWI btnAgregar;
    public Vistas.elemetos.ButtonWI btnCargar;
    public Vistas.elemetos.ButtonWI btnEliminar;
    public javax.swing.JButton btnImprimirFac;
    public javax.swing.JButton btnNuevaFactura;
    public javax.swing.JComboBox<String> cmbAsignarOrden;
    public javax.swing.JComboBox<String> cmbFactura;
    public javax.swing.JComboBox<String> cmbProductoRepuesto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbDetalleFactura;
    // End of variables declaration//GEN-END:variables
}
