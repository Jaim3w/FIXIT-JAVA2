
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


public class frmFacturacion extends TransparenteRoundedPanel {


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

        Facturas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JFormattedTextField();
        btnActualizar = new Vistas.elemetos.ButtonA();
        btnGuardar = new Vistas.elemetos.ButtonA();
        btnEliminar = new Vistas.elemetos.ButtonA();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFacturas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFechaFactura = new javax.swing.JFormattedTextField();
        txtFechaVencimiento = new javax.swing.JFormattedTextField();
        CalendarioVencimiento = new com.toedter.calendar.JCalendar();
        btnLimpiar = new Vistas.elemetos.ButtonA();
        CalendarioEmision = new com.toedter.calendar.JCalendar();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(922, 703));
        setPreferredSize(new java.awt.Dimension(922, 703));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Facturas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Facturas.setForeground(new java.awt.Color(0, 0, 0));
        Facturas.setText("Facturas");
        add(Facturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 170, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Fecha de vencimiento");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Factura Para");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Fecha Emision");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 110, 20));

        txtcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclienteActionPerformed(evt);
            }
        });
        add(txtcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 220, 30));

        btnActualizar.setText("Actualizar");
        btnActualizar.setToolTipText("");
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 280, 120, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 120, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("");
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 120, -1));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 940, 220));
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 510, 30, 30));
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, 30, 10));
        add(txtFechaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 220, 30));
        add(txtFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 220, 30));

        CalendarioVencimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarioVencimientoPropertyChange(evt);
            }
        });
        add(CalendarioVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 340, 180));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setToolTipText("");
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 120, -1));

        CalendarioEmision.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarioEmisionPropertyChange(evt);
            }
        });
        add(CalendarioEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 360, 180));
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
    private javax.swing.JLabel Facturas;
    public Vistas.elemetos.ButtonA btnActualizar;
    public Vistas.elemetos.ButtonA btnEliminar;
    public Vistas.elemetos.ButtonA btnGuardar;
    public Vistas.elemetos.ButtonA btnLimpiar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbFacturas;
    public javax.swing.JFormattedTextField txtFechaFactura;
    public javax.swing.JFormattedTextField txtFechaVencimiento;
    public javax.swing.JFormattedTextField txtcliente;
    // End of variables declaration//GEN-END:variables
}
