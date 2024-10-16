
package Vistas;

import Vistas.elemetos.RoundedWhitePanel;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import Controlador.ctrlFactura;
import Modelo.Factura;
import Vistas.elementosTwo.TransparenteRoundedPanel;


public class frmFacturacion extends JFrame {


    public frmFacturacion() {
        initComponents();
        
        frmFacturacion vista = this;
        Factura modelo = new Factura();
        ctrlFactura controlador = new ctrlFactura(modelo, vista);

        vista.setVisible(true);
        
        txtFechaFactura.setEditable(false);
        txtFechaVencimiento.setEditable(false);
        
         for (Component component : CalendarioEmision.getComponents()) {
        if (component instanceof JButton) {
            component.setVisible(false);
        }
    }

    // Repite lo mismo para CalendarioVencimiento
    for (Component component : CalendarioVencimiento.getComponents()) {
        if (component instanceof JButton) {
            component.setVisible(false);
        }
    }
    }
    
    
    private void initCalendarios() {
     Calendar today = Calendar.getInstance();
    Date currentDate = today.getTime();

    // Establecer la fecha de emisión a hoy
    CalendarioEmision.setDate(currentDate);
    CalendarioEmision.setMinSelectableDate(currentDate); // Mínimo hoy

    // Establecer la fecha máxima seleccionable a mañana
    today.add(Calendar.DAY_OF_YEAR, 1); // Agregar un día
    Date tomorrowDate = today.getTime();
    CalendarioEmision.setMaxSelectableDate(tomorrowDate); // Máximo mañana

    // Inicializar el calendario de vencimiento
    today.add(Calendar.DAY_OF_YEAR, 6); // Ir a 7 días después de hoy
    Date fechaVencimiento = today.getTime();

    // Establecer el calendario de vencimiento con restricciones
    CalendarioVencimiento.setMinSelectableDate(fechaVencimiento); // Mínimo 7 días después
    CalendarioVencimiento.setMaxSelectableDate(fechaVencimiento); // Máximo 7 días después
    CalendarioVencimiento.setDate(fechaVencimiento); // Establecer fecha de vencimiento inicial

    // Aplicar restricciones
    restrictCalendar(CalendarioEmision);
    restrictCalendar(CalendarioVencimiento);
}
    
    private Date fechaSeleccionada = null; // Variable para almacenar la fecha seleccionada
    
    private void restrictCalendar(JCalendar calendar) {
    // Listener para el cambio de fecha
       disableMonthYearSelectors(calendar);

    // Listener para el cambio de fecha
    calendar.addPropertyChangeListener("calendar", evt -> {
        fechaSeleccionada = calendar.getDate(); // Guardar la fecha seleccionada
        updateTextFields(); // Actualizar los text fields

        if (calendar == CalendarioEmision) {
            // Restringir el calendario de vencimiento basado en la fecha de emisión
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaSeleccionada);
            cal.add(Calendar.DAY_OF_YEAR, 7);
            CalendarioVencimiento.setMinSelectableDate(cal.getTime());
            CalendarioVencimiento.setMaxSelectableDate(cal.getTime());
        }
    });
}
    
    
    private void disableMonthYearSelectors(JCalendar calendar) {
    if (calendar != null) {
        // Desactivar el selector de mes
        calendar.getMonthChooser().setEnabled(false);
        // Desactivar el selector de año
        calendar.getYearChooser().setEnabled(false);
        
        // Listener para permitir la selección de días
        calendar.getDayChooser().addPropertyChangeListener("day", evt -> {
            // Mantener la fecha actual al cambiar el día
            calendar.setDate(calendar.getDate());
        });
    }
}
    
    private void updateTextFields() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    if (fechaSeleccionada != null) {
        txtFechaFactura.setText(dateFormat.format(fechaSeleccionada));
        // Actualizar txtFechaVencimiento si es necesario
    }
}

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JFormattedTextField();
        txtFechaFactura = new javax.swing.JFormattedTextField();
        txtFechaVencimiento = new javax.swing.JFormattedTextField();
        CalendarioVencimiento = new com.toedter.calendar.JCalendar();
        CalendarioEmision = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFacturas = new javax.swing.JTable();
        btnLimpiar = new Vistas.elemetos.ButtonWI();
        btnGuardar = new Vistas.elemetos.ButtonWI();
        btnActualizar = new Vistas.elemetos.ButtonWI();
        btnEliminar = new Vistas.elemetos.ButtonWI();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1040, 670));
        setPreferredSize(new java.awt.Dimension(1040, 670));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha de vencimiento:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 300, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Factura para:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 250, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fecha Emisión:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 300, 20));

        txtcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 410, 30));
        getContentPane().add(txtFechaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 410, 30));
        getContentPane().add(txtFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 410, 30));

        CalendarioVencimiento.setForeground(new java.awt.Color(255, 255, 255));
        CalendarioVencimiento.setDecorationBackgroundColor(new java.awt.Color(255, 255, 255));
        CalendarioVencimiento.setSundayForeground(new java.awt.Color(0, 0, 0));
        CalendarioVencimiento.setWeekdayForeground(new java.awt.Color(153, 153, 153));
        CalendarioVencimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarioVencimientoPropertyChange(evt);
            }
        });
        getContentPane().add(CalendarioVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 480, 180));

        CalendarioEmision.setForeground(new java.awt.Color(255, 255, 255));
        CalendarioEmision.setDecorationBackgroundColor(new java.awt.Color(255, 255, 255));
        CalendarioEmision.setSundayForeground(new java.awt.Color(0, 0, 0));
        CalendarioEmision.setWeekdayForeground(new java.awt.Color(153, 153, 153));
        CalendarioEmision.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarioEmisionPropertyChange(evt);
            }
        });
        getContentPane().add(CalendarioEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 480, 160));

        tbFacturas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbFacturas);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iclimpiaralojaime.png"))); // NOI18N

        btnGuardar.setText("Guardar");
        btnGuardar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icguardaraloadriel.png"))); // NOI18N

        btnActualizar.setText("Editar");
        btnActualizar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/iceditaralokevon.png"))); // NOI18N

        btnEliminar.setText("Eliminar");
        btnEliminar.setSuffixIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/icborraralojosue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 1000, 300));
    }// </editor-fold>//GEN-END:initComponents

    private void txtclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclienteActionPerformed

    private void CalendarioEmisionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarioEmisionPropertyChange
if ("calendar".equals(evt.getPropertyName())) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Actualizar el campo de texto de la fecha de emisión
        txtFechaFactura.setText(dateFormat.format(CalendarioEmision.getDate()));

        // Calcular la fecha de vencimiento (7 días después)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(CalendarioEmision.getDate());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date fechaVencimiento = calendar.getTime();

        // Establecer la fecha de vencimiento y restringir las fechas
        CalendarioVencimiento.setMinSelectableDate(fechaVencimiento);
        CalendarioVencimiento.setMaxSelectableDate(fechaVencimiento);
        CalendarioVencimiento.setDate(fechaVencimiento); // Seleccionar automáticamente la fecha de vencimiento

        // Actualizar el campo de texto de la fecha de vencimiento
        txtFechaVencimiento.setText(dateFormat.format(fechaVencimiento));
    }
    }//GEN-LAST:event_CalendarioEmisionPropertyChange

     @Override
    public void addNotify() {
        super.addNotify();
        initCalendarios(); // Llama a initCalendarios para establecer las restricciones
    }
    
    private void CalendarioVencimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarioVencimientoPropertyChange
if ("calendar".equals(evt.getPropertyName())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            txtFechaVencimiento.setText(dateFormat.format(CalendarioVencimiento.getDate()));
        }    }//GEN-LAST:event_CalendarioVencimientoPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.toedter.calendar.JCalendar CalendarioEmision;
    public com.toedter.calendar.JCalendar CalendarioVencimiento;
    public Vistas.elemetos.ButtonWI btnActualizar;
    public Vistas.elemetos.ButtonWI btnEliminar;
    public Vistas.elemetos.ButtonWI btnGuardar;
    public Vistas.elemetos.ButtonWI btnLimpiar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbFacturas;
    public javax.swing.JFormattedTextField txtFechaFactura;
    public javax.swing.JFormattedTextField txtFechaVencimiento;
    public javax.swing.JFormattedTextField txtcliente;
    // End of variables declaration//GEN-END:variables
}
