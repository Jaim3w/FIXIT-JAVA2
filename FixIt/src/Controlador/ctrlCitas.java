package Controlador;

import Modelo.Clientes;
import Modelo.Empleados;
import Modelo.mdlCitas;
import Vistas.frmCitas;
import Vistas.citasCardsPanel; // Importar la vista de tarjetas
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JTable;

/**
 *
 * @author rdlfp
 */
public class ctrlCitas implements MouseListener, KeyListener {
    private mdlCitas modelito;
    private frmCitas vista;
    private Clientes modeloCliente;
    private Empleados modeloEmpleado;
    private citasCardsPanel citasCardsPanel; // Nueva referencia al panel de tarjetas

    public ctrlCitas(mdlCitas modelo, frmCitas vistas, Clientes Modelo, Empleados ModeloEm, citasCardsPanel citasCards) {
        this.modelito = modelo;
        this.vista = vistas;
        this.modeloCliente = Modelo;
        this.modeloEmpleado = ModeloEm;
        this.citasCardsPanel = citasCards; // Inicializar el panel de tarjetas

        // Añadir los listeners para botones y combos
        vistas.btnAddCita.addMouseListener(this);
        vistas.btnActualizar.addMouseListener(this);
        vistas.btnEliminar.addMouseListener(this);

        // Cargar combos de clientes y empleados
        Modelo.CargarCombo(vistas.cmbCliente);
        ModeloEm.Cargarcombo(vistas.cmbEmpleado);

        // Cargar citas al iniciar
        modelito.mostrarCitas(vista.tbCitas);  
        modelito.obtenerCitasCards();

        
         if (vista.cmbCliente.getItemCount() > 0) {
            vista.cmbCliente.setSelectedIndex(0);
        }
        // Acción del combobox de Clientes
        vistas.cmbCliente.addActionListener(e -> {
            if (e.getSource() == vistas.cmbCliente) {
                System.out.println("Combobox Cliente seleccionado");
                Clientes selectedItem = (Clientes) vistas.cmbCliente.getSelectedItem();
                if (selectedItem != null) {
                    String UUID = selectedItem.getDui_cliente();
                    modeloCliente.setDui_cliente(UUID);
                    System.out.println("Cliente seleccionado UUID: " + UUID);
                } else {
                    System.err.println("No se seleccionó ningún Cliente");
                }
            }
        });

        
         if (vista.cmbEmpleado.getItemCount() > 0) {
            vista.cmbEmpleado.setSelectedIndex(0);
        }
        // Acción del combobox de Empleados
        vistas.cmbEmpleado.addActionListener(e -> {
            if (e.getSource() == vistas.cmbEmpleado) {
                System.out.println("Combobox Empleado seleccionado");
                Empleados selectedItem = (Empleados) vistas.cmbEmpleado.getSelectedItem();
                if (selectedItem != null) {
                    String UUID = selectedItem.getDui_empleado();
                    modeloEmpleado.setDui_empleado(UUID);
                    System.out.println("Empleado seleccionado: " + UUID);
                } else {
                    System.out.println("No se seleccionó ningún Empleado");
                }
            }
        });

        // Añadir un MouseListener para capturar el clic en la tabla
        vista.tbCitas.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.tbCitas) {
            // Cuando se haga clic en una fila, se pasan los valores a los JTextFields
            JTable source = (JTable) e.getSource();
            int filaSeleccionada = source.getSelectedRow();
            
            if (filaSeleccionada != -1) {  // Asegurarse de que una fila esté seleccionada
                // Obtener los valores de la fila seleccionada
                String uuidCita = source.getModel().getValueAt(filaSeleccionada, 0).toString(); // columna 0 (UUID)
                String clienteNombre = source.getModel().getValueAt(filaSeleccionada, 1).toString(); // columna 1 (Cliente)
                String empleadoNombre = source.getModel().getValueAt(filaSeleccionada, 2).toString(); // columna 2 (Empleado)
                String fechaCita = source.getModel().getValueAt(filaSeleccionada, 3).toString(); // columna 3 (Fecha)
                String horaCita = source.getModel().getValueAt(filaSeleccionada, 4).toString();  // columna 4 (Hora)
                String descripcion = source.getModel().getValueAt(filaSeleccionada, 5).toString(); // columna 5 (Descripción)
                
                // Pasar los valores a los JTextFields correspondientes
                vista.txtFecha.setText(fechaCita);
                vista.txtHora.setText(horaCita);
                vista.txtDEsc.setText(descripcion);

                // Establecer los valores en los comboboxes (aquí necesitas el DUI)
                setComboBoxValue(vista.cmbCliente, clienteNombre); // Cambia a buscar por nombre si es necesario
                setComboBoxValue(vista.cmbEmpleado, empleadoNombre); // Cambia a buscar por nombre si es necesario
            }
        }

        if (e.getSource() == vista.btnAddCita) {
            try {
                // Validar fecha
                LocalDate fechaIngresada = LocalDate.parse(vista.txtFecha.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate fechaHoy = LocalDate.now();
                
                if (fechaIngresada.isBefore(fechaHoy)) {
                    JOptionPane.showMessageDialog(vista, "La fecha no puede ser anterior a hoy.", "Fecha inválida", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar hora
                LocalTime horaIngresada = LocalTime.parse(vista.txtHora.getText());
                LocalTime horaMinima = LocalTime.of(8, 0);  // 8:00 AM
                LocalTime horaMaxima = LocalTime.of(20, 0); // 8:00 PM

                if (horaIngresada.isBefore(horaMinima) || horaIngresada.isAfter(horaMaxima)) {
                    JOptionPane.showMessageDialog(vista, "La hora debe estar entre las 8:00 AM y las 8:00 PM.", "Hora inválida", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                System.out.println("Guardando cita...");
                modelito.setDui_empleado(modeloEmpleado.getDui_empleado());
                modelito.setDui_cliente(modeloCliente.getDui_cliente());
                modelito.setHora_cita(vista.txtHora.getText());
                modelito.setDescripcion(vista.txtDEsc.getText());
                modelito.setFecha_cita(vista.txtFecha.getText());

                modelito.insertarCitas();
                modelito.mostrarCitas(vista.tbCitas);
                citasCardsPanel.loadCards(); // Actualizar tarjetas después de añadir
                JOptionPane.showMessageDialog(null, "Cita registrada con éxito", "Cita registrada", JOptionPane.INFORMATION_MESSAGE);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(vista, "Formato de fecha u hora inválido. Use 'yyyy-MM-dd' para la fecha y 'HH:mm' para la hora.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al registrar cita: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            modelito.eliminarCita(vista.tbCitas);
            modelito.mostrarCitas(vista.tbCitas);  // Refrescar la tabla después de eliminar
             modelito.limpiarCampos(vista);

        citasCardsPanel.loadCards(); // Actualizar tarjetas después de eliminar
        }

        if (e.getSource() == vista.btnActualizar) {
    // Validaciones de entrada
    if (vista.txtFecha.getText().isEmpty() || vista.txtHora.getText().isEmpty() ||
            vista.txtDEsc.getText().isEmpty() || 
            (vista.cmbCliente.getSelectedItem() == null || vista.cmbEmpleado.getSelectedItem() == null)) {
        JOptionPane.showMessageDialog(vista, "No puedes actualizar a datos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Salir del método si hay campos vacíos
    }

    try {
        // Validar fecha
        LocalDate fechaIngresada = LocalDate.parse(vista.txtFecha.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fechaHoy = LocalDate.now();

        if (fechaIngresada.isBefore(fechaHoy)) {
            JOptionPane.showMessageDialog(vista, "La fecha no puede ser anterior a hoy.", "Fecha inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar hora
        LocalTime horaIngresada = LocalTime.parse(vista.txtHora.getText());
        LocalTime horaMinima = LocalTime.of(8, 0);  // 8:00 AM
        LocalTime horaMaxima = LocalTime.of(20, 0); // 8:00 PM

        if (horaIngresada.isBefore(horaMinima) || horaIngresada.isAfter(horaMaxima)) {
            JOptionPane.showMessageDialog(vista, "La hora debe estar entre las 8:00 AM y las 8:00 PM.", "Hora inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Establecer datos en el modelo
        modelito.setHora_cita(vista.txtHora.getText());
        modelito.setDescripcion(vista.txtDEsc.getText());
        modelito.setFecha_cita(vista.txtFecha.getText());

        // Obtener cliente y empleado seleccionados
        Clientes selectedCliente = (Clientes) vista.cmbCliente.getSelectedItem();
        Empleados selectedEmpleado = (Empleados) vista.cmbEmpleado.getSelectedItem();

        // Asegurarse de que no sean nulos
        if (selectedCliente != null) {
            modelito.setDui_cliente(selectedCliente.getDui_cliente());
        }

        if (selectedEmpleado != null) {
            modelito.setDui_empleado(selectedEmpleado.getDui_empleado());
        }

        // Llamar al método para actualizar la cita
        modelito.actualizarCita(vista.tbCitas);
        modelito.mostrarCitas(vista.tbCitas);
        citasCardsPanel.loadCards(); // Actualizar tarjetas después de actualizar
    } catch (DateTimeParseException ex) {
        JOptionPane.showMessageDialog(vista, "Formato de fecha u hora inválido. Use 'yyyy-MM-dd' para la fecha y 'HH:mm' para la hora.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "No se pudo actualizar la cita", "Error", JOptionPane.WARNING_MESSAGE);
    }
}
    }

    // Método para establecer el valor en un ComboBox basado en el DUI
    private void setComboBoxValue(javax.swing.JComboBox comboBox, String identifier) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i) instanceof Clientes && ((Clientes) comboBox.getItemAt(i)).getDui_cliente().equals(identifier)) {
                comboBox.setSelectedIndex(i);
                break;
            } else if (comboBox.getItemAt(i) instanceof Empleados && ((Empleados) comboBox.getItemAt(i)).getDui_empleado().equals(identifier)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}