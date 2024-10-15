package Vistas;

import Controlador.ctrlCarros;
import Modelo.ClientesCarro;
import Modelo.ModeloCarro;
import Modelo.mdlCarros;
import Vistas.elementosTwo.TransparenteRoundedPanel;

public class frmCarros extends TransparenteRoundedPanel {


    public frmCarros() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbListaCarros = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtBuscarCarro = new Vistas.elementosTwo.MyTextField();
        BtnLimpiarcamposCarro = new Vistas.elemetos.ButtonWI();
        btnGuardarCarro = new Vistas.elemetos.ButtonWI();
        btnEliminarCarro = new Vistas.elemetos.ButtonWI();
        btnActualizarCarro = new Vistas.elemetos.ButtonWI();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDescripcionCarro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPlacaCarro = new javax.swing.JFormattedTextField();
        cmbClienteCarro = new javax.swing.JComboBox<>();
        cmbModeloCarro = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        lblImagenCarro = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtColorCarro = new javax.swing.JFormattedTextField();
        txtAnoCarro = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtIngresoCarro = new javax.swing.JLabel();
        btnSubirImagen = new Vistas.elemetos.ButtonWI();
        btnNewCliente = new javax.swing.JButton();
        btnNewModelo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(922, 703));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 970, 240));

        txtBuscarCarro.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iconbuscar2.png"))); // NOI18N

        BtnLimpiarcamposCarro.setText("Limpiar");
        BtnLimpiarcamposCarro.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iclimpiaralojaime.png"))); // NOI18N

        btnGuardarCarro.setText("Guardar");
        btnGuardarCarro.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icguardaraloadriel.png"))); // NOI18N

        btnEliminarCarro.setText("Eliminar");
        btnEliminarCarro.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icborraralojosue.png"))); // NOI18N

        btnActualizarCarro.setText("Editar");
        btnActualizarCarro.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iceditaralokevon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtBuscarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(BtnLimpiarcamposCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnLimpiarcamposCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(264, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 990, 320));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Color del vehiculo:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Fecha de ingreso");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Año del vehiculo");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));
        add(txtDescripcionCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 220, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cliente");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 40, 20));
        add(txtPlacaCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 220, 30));

        cmbClienteCarro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cmbClienteCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 220, 30));

        cmbModeloCarro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbModeloCarro.setToolTipText("");
        add(cmbModeloCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 220, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Descripcion");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        lblImagenCarro.setBackground(new java.awt.Color(255, 255, 255));
        lblImagenCarro.setForeground(new java.awt.Color(255, 255, 255));
        add(lblImagenCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 290, 230));
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, -1, -1));
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 510, 30, 30));
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 370, 30, 10));
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 540, 70, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Modelo:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));
        add(txtColorCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 220, 30));
        add(txtAnoCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 220, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Placa del Vehiculo");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        txtIngresoCarro.setForeground(new java.awt.Color(255, 255, 255));
        txtIngresoCarro.setText("jLabel14");
        add(txtIngresoCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 90, -1));

        btnSubirImagen.setText("Cargar imagen");
        btnSubirImagen.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iccargarimagenalofito.png"))); // NOI18N
        add(btnSubirImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 170, -1));

        btnNewCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/mas.png"))); // NOI18N
        add(btnNewCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 30, 30));

        btnNewModelo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/mas.png"))); // NOI18N
        add(btnNewModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 30, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCarroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCarroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Vistas.elemetos.ButtonWI BtnLimpiarcamposCarro;
    public Vistas.elemetos.ButtonWI btnActualizarCarro;
    public Vistas.elemetos.ButtonWI btnEliminarCarro;
    public Vistas.elemetos.ButtonWI btnGuardarCarro;
    public javax.swing.JButton btnNewCliente;
    public javax.swing.JButton btnNewModelo;
    public Vistas.elemetos.ButtonWI btnSubirImagen;
    public javax.swing.JComboBox<String> cmbClienteCarro;
    public javax.swing.JComboBox<String> cmbModeloCarro;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblImagenCarro;
    public javax.swing.JTable tbListaCarros;
    public javax.swing.JTextField txtAnoCarro;
    public Vistas.elementosTwo.MyTextField txtBuscarCarro;
    public javax.swing.JFormattedTextField txtColorCarro;
    public javax.swing.JTextField txtDescripcionCarro;
    public javax.swing.JLabel txtIngresoCarro;
    public javax.swing.JFormattedTextField txtPlacaCarro;
    // End of variables declaration//GEN-END:variables
}
