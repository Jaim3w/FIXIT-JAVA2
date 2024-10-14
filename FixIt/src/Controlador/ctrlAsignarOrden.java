
package Controlador;

import Modelo.mdlAsignarOrden;
import Vistas.frmAsignarOrden;

import Modelo.mdlCitas;
import Modelo.Servicios;
import Modelo.EstadoAsignarOrden;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class ctrlAsignarOrden implements MouseListener, KeyListener{
    private mdlAsignarOrden Modelo;
    private frmAsignarOrden Vista;
    private mdlCitas ModeloCita;
    private Servicios ModeloServicio;
    private EstadoAsignarOrden ModeloEstado;

    
    
    public ctrlAsignarOrden(mdlAsignarOrden Modelo, frmAsignarOrden Vista, mdlCitas Modelo1, Servicios Modelo2, EstadoAsignarOrden Modelo3){
        
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.ModeloCita = Modelo1;
        this.ModeloServicio = Modelo2;
        this.ModeloEstado = Modelo3;
        
         Vista.tbAsignaciones.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            Modelo.cargarDatosTabla(Vista);
        }
    });
         
          System.out.println("Controlador creado");
        
        
        Vista.btnAgregar.addMouseListener(this);
        Vista.btnActualizar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        Vista.btnLimpiar.addMouseListener(this);
        Vista.tbAsignaciones.addMouseListener(this);
        
        Modelo1.CargarComboCitas(Vista.cmbCita);
        Modelo2.CargarComboServicio(Vista.cmbServicio);
        Modelo3.CargarComboEstadoAsignarOrden(Vista.cmbEstado);

        
        Modelo.Mostrar(Vista.tbAsignaciones);
        
        
        Vista.cmbCita.addActionListener(e -> {
    // Verificamos que la fuente del evento sea el ComboBox de Citas
    if (e.getSource() == Vista.cmbCita) {
        System.out.println("ComboBox Cita seleccionado");
        
        // Obtener el item seleccionado
        mdlCitas selectedItem = (mdlCitas) Vista.cmbCita.getSelectedItem();
        
        if (selectedItem != null) {
            // Obtener el UUID de la cita seleccionada
            String uuidCita = selectedItem.getUUID_cita();
            // Establecer el UUID en el modelo correspondiente
            ModeloCita.setUUID_cita(uuidCita);
            
            // Imprimir el UUID para depuración
            System.out.println("Cita seleccionada UUID: " + uuidCita);
        } else {
            System.err.println("No se seleccionó ninguna Cita");
        }
    }
});

        
        
        
        Vista.cmbServicio.addActionListener(e -> {
            if (e.getSource() == Vista.cmbServicio) {
                System.out.println("Combobox Servicio seleccionado");
                Servicios selectedItem = (Servicios) Vista.cmbServicio.getSelectedItem();
                if (selectedItem != null) {
                    String UUID = selectedItem.getUUID_servicio();
                    ModeloServicio.setUUID_servicio(UUID);
                    System.out.println("Servicio seleccionado UUID: " + UUID);
                } else {
                    System.err.println("No se seleccionó ningún Servicio");
                }
            }
        });
        
        Vista.cmbEstado.addActionListener(e -> {
            if (e.getSource() == Vista.cmbEstado) {
                System.out.println("Combobox Estado seleccionado");
                EstadoAsignarOrden selectedItem = (EstadoAsignarOrden) Vista.cmbEstado.getSelectedItem();
                if (selectedItem != null) {
                    String UUID = selectedItem.getUUID_estado();
                    ModeloEstado.setUUID_estado(UUID);
                    System.out.println("Estado seleccionado UUID: " + UUID);
                } else {
                    System.err.println("No se seleccionó ningún estado");
                }
            }
        });
        
         // Añadir un MouseListener para capturar el clic en la tabla
        Vista.tbAsignaciones.addMouseListener(this);
        
}

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == Vista.btnAgregar) {
        // Verificar si los campos de texto están vacíos
        if (Vista.txtFechaAsignacion.getText().isEmpty() || Vista.txtFechaFinalizacion.getText().isEmpty() 
                || Vista.txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                System.out.println("Guardando asignación...");

                // Verificar si hay elementos en el JComboBox de Citas y seleccionar el primero si no hay selección
                if (Vista.cmbCita.getItemCount() > 0) {
                    Vista.cmbCita.setSelectedIndex(0);
                }

                // Verificar si hay elementos en el JComboBox de Servicios y seleccionar el primero si no hay selección
                if (Vista.cmbServicio.getItemCount() > 0) {
                    Vista.cmbServicio.setSelectedIndex(0);
                }

                // Verificar si hay elementos en el JComboBox de Estado y seleccionar el primero si no hay selección
                if (Vista.cmbEstado.getItemCount() > 0) {
                    Vista.cmbEstado.setSelectedIndex(0);
                }

                // Asignar los valores de la vista al modelo
                Modelo.setUUID_Cita(ModeloCita.getUUID_cita());
                Modelo.setUUID_Servicio(ModeloServicio.getUUID_servicio());
                Modelo.setUUID_Estado(ModeloEstado.getUUID_estado());
                Modelo.setCarro_Empleado(Vista.txtCliente.getText());
                Modelo.setFechaAsignacion(Vista.txtFechaAsignacion.getText());
                Modelo.setFechaFinalizacion(Vista.txtFechaFinalizacion.getText());
                Modelo.setDescripcion(Vista.txtDescripcion.getText());

                // Guardar la asignación
                Modelo.AgregarAsignacion();
                Modelo.Mostrar(Vista.tbAsignaciones);

                // Limpiar los campos de la vista
                Modelo.limpiar(Vista);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Dato ingresado erróneo", "Error", JOptionPane.WARNING_MESSAGE);
                System.out.println("Error al guardar la asignación: " + ex.getMessage());
            }
        }
    }

        
        
        if (e.getSource() == Vista.btnEliminar) {
            if (Vista.txtFechaAsignacion.getText().isEmpty() || Vista.txtFechaFinalizacion.getText().isEmpty() 
                    || Vista.txtDescripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Modelo.EliminarAsignacion(Vista.tbAsignaciones);
                Modelo.Mostrar(Vista.tbAsignaciones);
                Modelo.limpiar(Vista);
            }
        }
        
        
        if (e.getSource() == Vista.btnActualizar) {
            if (Vista.txtFechaAsignacion.getText().isEmpty() || Vista.txtFechaFinalizacion.getText().isEmpty() 
                    || Vista.txtDescripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    
                      EstadoAsignarOrden selectedEstado = (EstadoAsignarOrden) Vista.cmbEstado.getSelectedItem();
            if (selectedEstado != null) {
                // Asignar el UUID del estado al modelo
                Modelo.setUUID_Estado(selectedEstado.getUUID_estado()); // Asegúrate de que `mdlEstado` tiene este método
            } else {
                JOptionPane.showMessageDialog(Vista, "No se ha seleccionado ningún estado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    Modelo.setFechaAsignacion(Vista.txtFechaAsignacion.getText());
                    Modelo.setFechaFinalizacion(Vista.txtFechaFinalizacion.getText());
                    Modelo.setDescripcion(Vista.txtDescripcion.getText());

                    //Ejecutar el método    
                    Modelo.ActualizarAsignacion(Vista.tbAsignaciones);
                    Modelo.Mostrar(Vista.tbAsignaciones);
                    Modelo.limpiar(Vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Vista, "El valor seleccionado debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        if (e.getSource() == Vista.btnLimpiar) {
            Modelo.limpiar(Vista);
        }

        if (e.getSource() == Vista.tbAsignaciones) {
            Modelo.cargarDatosTabla(Vista);
        }
        
        
    }
        
    
//     Método para establecer el valor en un ComboBox basado en el DUI
//    private void setComboBoxValue(javax.swing.JComboBox comboBox, String identifier) {
//        for (int i = 0; i < comboBox.getItemCount(); i++) {
//            if (comboBox.getItemAt(i) instanceof mdlCitas && ((mdlCitas) comboBox.getItemAt(i)).getUUID_cita().equals(identifier)) {
//                comboBox.setSelectedIndex(i);
//                break;
//            } else if(comboBox.getItemAt(i) instanceof Servicios && ((Servicios) comboBox.getItemAt(i)).getUUID_servicio().equals(identifier)) {
//                comboBox.setSelectedIndex(i);
//                break;
//            }
//            else if (comboBox.getItemAt(i) instanceof EstadoAsignarOrden && ((EstadoAsignarOrden) comboBox.getItemAt(i)).getUUID_estado().equals(identifier)) {
//                comboBox.setSelectedIndex(i);
//                break;
//        }
//   }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
//         if (e.getSource() == Vista.txtBuscarAsignacion) {
//            Modelo.Buscar(Vista.tbAsignaciones, Vista.txtBuscarAsignacion);
//        }
    }

    
}
