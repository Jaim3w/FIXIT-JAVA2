package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import Modelo.mdlEmpleados;
import Modelo.Usuarios;
import Vistas.Loginjava;
import Vistas.frmUsuarios; 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ctrlEmpleados implements MouseListener, KeyListener {
    private mdlEmpleados Modelo;
    private frmUsuarios Vista;
    private Usuarios mUsuarios;
    
    public ctrlEmpleados(mdlEmpleados Modelo, frmUsuarios Vista, Usuarios mUsuarios){
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.mUsuarios = mUsuarios;
        
        Vista.btnGuardar.addMouseListener(this);
        Vista.btnSubirImagen.addMouseListener(this);
        Vista.dtgempleado.addMouseListener(this);
        
        this.mUsuarios.CargarComboEmpleado(Vista.cmbCorreoEmpleado);
        
        Vista.cmbCorreoEmpleado.addActionListener(e -> {
            if (e.getSource() == Vista.cmbCorreoEmpleado) {
                System.out.println("ComboBox seleccionado");
                Usuarios selectedItem = (Usuarios) Vista.cmbCorreoEmpleado.getSelectedItem();
                if (selectedItem != null) {
                    String uuid = selectedItem.getUUID_usuario();
                    mUsuarios.setUUID_usuario(uuid);
                    System.out.println("uuid de usuario seleccionado: " + uuid);
                } else {
                    System.out.println("No se seleccionó ningún usuario");
                }
            }
        });
        
        Modelo.Mostrar(Vista.dtgempleado);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*if (e.getSource() == Vista.btnNewUser) {
            try {
                frmNuevoUser.initLogin();
                Vista.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al abrir la ventana de inicio de sesión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }*/
        
        if (e.getSource() == Vista.dtgempleado) {
            Modelo.cargarDatosTabla(Vista);
        }
        
        if (e.getSource() == Vista.btnGuardar) {
        // Validar que los campos no estén vacíos
        if (Vista.txtdui.getText().isEmpty() || 
            Vista.cmbCorreoEmpleado.getSelectedItem() == null || 
            Vista.txtnombre.getText().isEmpty()|| 
            Vista.txtapellido.getText().isEmpty() || 
            Vista.txtFecha.getDate() == null ||
            Vista.txtImagenUrl.getIcon() == null ||
            Vista.txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Detener la ejecución si hay campos vacíos
        }
        // Se ejecuta si no hay campos vacíos
        try {
            System.out.println("Guardando empleado...");
            
            // Validar que el dui no exceda 9 caracteres
            String dui = Vista.txtdui.getText();

            // Validar que el D.U.I. tenga exactamente 9 dígitos y solo números
            if (!dui.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(Vista, "El D.U.I. debe contener exactamente 9 dígitos numéricos", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }

            Modelo.setDuiEmpleado(dui);
            
            // Obtiene los usuarios del combo box
            Usuarios userSeleccionado = (Usuarios) Vista.cmbCorreoEmpleado.getSelectedItem();

            Modelo.setUuidUsuario(userSeleccionado.getUUID_usuario());
            
            // Validar nombres (solo letras y longitud mayor a 3)
            String nombre = Vista.txtnombre.getText();
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{4,}")) { // Verifica que solo contenga letras y sea mayor a 3 caracteres
                JOptionPane.showMessageDialog(Vista, "El nombre debe contener solo letras y tener más de 3 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }
            Modelo.setNombre(nombre); // Guardar el nombre en el modelo

            String apellido = Vista.txtapellido.getText();
            if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{4,}")) { // Verifica que solo contenga letras y sea mayor a 3 caracteres
                JOptionPane.showMessageDialog(Vista, "El apellido debe contener solo letras y tener más de 3 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }
            Modelo.setApellido(apellido); // Guardar el apellido en el modelo
                Modelo.setImagenEmpleado(Modelo.getImagenEmpleado());
            
            // Convertir la fecha de JDateChooser a String
            Date fechaNacimiento = Vista.txtFecha.getDate(); // Obtener la fecha seleccionada
            if (fechaNacimiento != null) {
                Calendar calNacimiento = Calendar.getInstance();
                calNacimiento.setTime(fechaNacimiento);

                int yearNacimiento = calNacimiento.get(Calendar.YEAR);

                // Validar que la fecha sea posterior al año 1930 y que no sea posterior al año 2006
                if (yearNacimiento < 1930 || yearNacimiento > 2007) {
                    JOptionPane.showMessageDialog(Vista, "debe ser mayor de edad y el limite de año es 1930", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener si la fecha está fuera del rango
                }

                // Si pasa todas las validaciones, convertir la fecha a String
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String fechaNacimientoStr = dateFormat.format(fechaNacimiento); // Convertir la fecha a String
                Modelo.setFechaNacimiento(fechaNacimientoStr); // Guardar la fecha como String en el modelo
            } else {
                JOptionPane.showMessageDialog(Vista, "Por favor selecciona una fecha de nacimiento", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener si no hay fecha seleccionada
            }
            
            
            // Validar el teléfono (solo números y longitud de 8)
            String telefono = Vista.txtTelefono.getText();
            if (!telefono.matches("\\d{8}")) { // Verifica que solo contenga 8 dígitos
                JOptionPane.showMessageDialog(Vista, "El teléfono debe contener exactamente 8 números", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }
            Modelo.setTelefono(telefono); // Guardar el teléfono en el modelo

            Modelo.Guardar();

            System.out.println("empleado guardado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Vista, "Error al guardar el empleado: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("Error al guardar el empleado: " + ex.getMessage());
        }
    }
    }

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
    }
}