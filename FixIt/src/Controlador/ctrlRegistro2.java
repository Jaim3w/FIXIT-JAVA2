package Controlador;

import Modelo.Usuarios;
import Modelo.mdlRegistro2;
import Vistas.Loginjava;
import Vistas.frmRegistroParte2;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ctrlRegistro2 implements MouseListener {
    private mdlRegistro2 Modelo;
    private frmRegistroParte2 Vista;
    private Usuarios mUsuarios;
    
    public ctrlRegistro2 (mdlRegistro2 Modelo, frmRegistroParte2 Vista, Usuarios mUsuarios){
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.mUsuarios = mUsuarios;
        
        this.Vista.btnGuardarR.addMouseListener(this);
        this.Vista.btnSubirImagenR.addMouseListener(this);
        
        this.mUsuarios.CargarComboUser(Vista.cmbCorreoR);
        
        Vista.cmbCorreoR.addActionListener(e -> {
            if (e.getSource() == Vista.cmbCorreoR) {
                System.out.println("ComboBox seleccionado");
                Usuarios selectedItem = (Usuarios) Vista.cmbCorreoR.getSelectedItem();
                if (selectedItem != null) {
                    String uuid = selectedItem.getUUID_usuario();
                    mUsuarios.setUUID_usuario(uuid);
                    System.out.println("uuid de usuario seleccionado: " + uuid);
                } else {
                    System.out.println("No se seleccionó ningún usuario");
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnGuardarR) {
        // Validar que los campos no estén vacíos
        if (Vista.txtDuiR.getText().isEmpty() || 
            Vista.cmbCorreoR.getSelectedItem() == null || 
            Vista.txtNombresR.getText().isEmpty()|| 
            Vista.txtApellidosR.getText().isEmpty() || 
            Vista.txtFechaNacimiento.getDate() == null ||
            Vista.lblImagenEmpleado.getIcon() == null ||
            Vista.txtTelefonoR.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Detener la ejecución si hay campos vacíos
        }
        // Se ejecuta si no hay campos vacíos
        try {
            System.out.println("Guardando admin...");
            
            // Validar que el dui no exceda 9 caracteres
            String dui = Vista.txtDuiR.getText();

            // Validar que el D.U.I. tenga exactamente 9 dígitos y solo números
            if (!dui.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(Vista, "El D.U.I. debe contener exactamente 9 dígitos numéricos", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }

            Modelo.setDuiEmpleado(dui);
            
            // Obtiene los usuarios del combo box
            Usuarios clienteSeleccionado = (Usuarios) Vista.cmbCorreoR.getSelectedItem();

            Modelo.setCorreoUsuario(clienteSeleccionado.getUUID_usuario());
            
            // Validar nombres (solo letras y longitud mayor a 3)
            String nombre = Vista.txtNombresR.getText();
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{4,}")) { // Verifica que solo contenga letras y sea mayor a 3 caracteres
                JOptionPane.showMessageDialog(Vista, "El nombre debe contener solo letras y tener más de 3 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }
            Modelo.setNombreEmpledo(nombre); // Guardar el nombre en el modelo

            String apellido = Vista.txtApellidosR.getText();
            if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{4,}")) { // Verifica que solo contenga letras y sea mayor a 3 caracteres
                JOptionPane.showMessageDialog(Vista, "El apellido debe contener solo letras y tener más de 3 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }
            Modelo.setApellidoEmplead(apellido); // Guardar el apellido en el modelo
                Modelo.setImagenSeleccionada(Modelo.getImagenSeleccionada());
            
            // Convertir la fecha de JDateChooser a String
            Date fechaNacimiento = Vista.txtFechaNacimiento.getDate(); // Obtener la fecha seleccionada
            if (fechaNacimiento != null) {
                Calendar calNacimiento = Calendar.getInstance();
                calNacimiento.setTime(fechaNacimiento);

                int yearNacimiento = calNacimiento.get(Calendar.YEAR);

                // Validar que la fecha sea posterior al año 1930 y que no sea posterior al año 2006
                if (yearNacimiento < 1930 || yearNacimiento > 2007) {
                    JOptionPane.showMessageDialog(Vista, "debes ser mayor de edad y el limite de año es 1930", "Error", JOptionPane.ERROR_MESSAGE);
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
            String telefono = Vista.txtTelefonoR.getText();
            if (!telefono.matches("\\d{8}")) { // Verifica que solo contenga 8 dígitos
                JOptionPane.showMessageDialog(Vista, "El teléfono debe contener exactamente 8 números", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }
            Modelo.setTelefonoEmpleado(telefono); // Guardar el teléfono en el modelo

            Modelo.Guardar();

            System.out.println("admin guardado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Vista, "Error al guardar el empleado: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("Error al guardar el empleado: " + ex.getMessage());
        }
        
        try {
                Loginjava.initLogin();
                Vista.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al abrir la ventana de inicio de sesión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
        if (e.getSource() == Vista.btnSubirImagenR) {
    // Crear el JFileChooser para seleccionar un archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Imagen");

        // Abrir el diálogo para seleccionar una imagen
        int result = fileChooser.showOpenDialog(Vista);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File imagenSeleccionada = fileChooser.getSelectedFile();

            // Establecer la imagen seleccionada en el modelo
            Modelo.setImagenSeleccionada(imagenSeleccionada);

            // Mostrar la imagen en el JLabel lblImagenCarro
            mostrarImagenEnLabel(imagenSeleccionada);

            // Mostrar el nombre de la imagen seleccionada
            JOptionPane.showMessageDialog(Vista, "Imagen seleccionada: " + imagenSeleccionada.getName());
        } else {
            JOptionPane.showMessageDialog(Vista, "No se seleccionó ninguna imagen.");
        }
    }
    }
    
    private void mostrarImagenEnLabel(File imagen) {
        try {
            // Leer la imagen desde el archivo
            BufferedImage bufferedImage = ImageIO.read(imagen);

            // Crear el ImageIcon a partir de la imagen
            ImageIcon icon = new ImageIcon(bufferedImage);

            // Redimensionar la imagen para que se ajuste al JLabel
            Image imageScaled = icon.getImage().getScaledInstance(Vista.lblImagenEmpleado.getWidth(),
            Vista.lblImagenEmpleado.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imageScaled);

            // Establecer la imagen en el JLabel
            Vista.lblImagenEmpleado.setIcon(scaledIcon);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Vista, "Error al cargar la imagen.");
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
}
