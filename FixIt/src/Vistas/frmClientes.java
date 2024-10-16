/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vistas;

/**
 *
 * @author Jaimew
 */
import Controlador.ctrlClientes;
import Modelo.mdlClientes;
import Vistas.elementosTwo.TransparenteRoundedPanel;
import Vistas.elemetos.RoundedWhitePanel;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class frmClientes extends JFrame {

    /**
     * Creates new form frmClientes
     */
    public frmClientes() {
        initComponents();
        
         frmClientes vista = this;
        mdlClientes modelo = new mdlClientes();
        ctrlClientes controlador = new ctrlClientes(modelo, vista);

        vista.setVisible(true);
        setSize(1031, 717);
        setResizable(false);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        transparenteRoundedPanel1 = new Vistas.elementosTwo.TransparenteRoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtduicliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtApellidoCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCorreoCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefonoCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();
        btnAgregar = new Vistas.elemetos.ButtonWI();
        btnActualizar = new Vistas.elemetos.ButtonWI();
        btnEliminar = new Vistas.elemetos.ButtonWI();
        btnLimpiar = new Vistas.elemetos.ButtonWI();
        txtBuscar = new Vistas.elementosTwo.MyTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        transparenteRoundedPanel1.setBackground(new java.awt.Color(0, 0, 0));
        transparenteRoundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registra tus Clientes");
        transparenteRoundedPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dui del cliente:");
        transparenteRoundedPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 100, 20));
        transparenteRoundedPanel1.add(txtduicliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 310, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre:");
        transparenteRoundedPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));
        transparenteRoundedPanel1.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 310, -1));
        transparenteRoundedPanel1.add(txtApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 310, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido: ");
        transparenteRoundedPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, -1, -1));
        transparenteRoundedPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 320, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Correo electrónico: ");
        transparenteRoundedPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 130, -1));
        transparenteRoundedPanel1.add(txtCorreoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 320, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Teléfono: ");
        transparenteRoundedPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 90, -1));
        transparenteRoundedPanel1.add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 320, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Contraseña: ");
        transparenteRoundedPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 70, -1));

        tbClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbClientes);

        btnAgregar.setText("Guardar");
        btnAgregar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icguardaraloadriel.png"))); // NOI18N

        btnActualizar.setText("Editar");
        btnActualizar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iceditaralokevon.png"))); // NOI18N

        btnEliminar.setText("Eliminar");
        btnEliminar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icborraralojosue.png"))); // NOI18N

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iclimpiaralojaime.png"))); // NOI18N

        txtBuscar.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iconbuscar2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        transparenteRoundedPanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 990, 340));

        getContentPane().add(transparenteRoundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 720));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public Vistas.elemetos.ButtonWI btnActualizar;
    public Vistas.elemetos.ButtonWI btnAgregar;
    public Vistas.elemetos.ButtonWI btnEliminar;
    public Vistas.elemetos.ButtonWI btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbClientes;
    private Vistas.elementosTwo.TransparenteRoundedPanel transparenteRoundedPanel1;
    public javax.swing.JTextField txtApellidoCliente;
    public Vistas.elementosTwo.MyTextField txtBuscar;
    public javax.swing.JTextField txtContraseña;
    public javax.swing.JTextField txtCorreoCliente;
    public javax.swing.JTextField txtNombreCliente;
    public javax.swing.JTextField txtTelefonoCliente;
    public javax.swing.JTextField txtduicliente;
    // End of variables declaration//GEN-END:variables
}
