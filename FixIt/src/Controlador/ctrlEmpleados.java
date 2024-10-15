package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import Modelo.mdlEmpleados;
import Modelo.Usuarios;
import Vistas.Loginjava;
import Vistas.frmClientes;
import Vistas.frmUsuarios; 
import Vistas.frmNuevoUsuario;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

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
        Vista.btnNewUser.addMouseListener(this);
        Vista.btnClientes.addMouseListener(this);
        Vista.btnActualizar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        
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
        Modelo.limpiar(Vista);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnNewUser) {
            frmNuevoUsuario nuevoUsuarioFrame = new frmNuevoUsuario();
            nuevoUsuarioFrame.setVisible(true);
            nuevoUsuarioFrame.setLocationRelativeTo(null);

            nuevoUsuarioFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    mUsuarios.CargarComboEmpleado(Vista.cmbCorreoEmpleado);
                }
            });
        }
        
        if (e.getSource() == Vista.btnClientes) {
            frmClientes nuevoClientesFrame = new frmClientes();
            nuevoClientesFrame.setVisible(true);
            nuevoClientesFrame.setLocationRelativeTo(null);
        }

        
        if (e.getSource() == Vista.dtgempleado) {
            Modelo.cargarDatosTabla(Vista);
        }
        
        if (e.getSource() == Vista.btnSubirImagen) {
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

                    // Validar que la fecha sea posterior al año 1930 y que no sea posterior al año 2007
                    if (yearNacimiento < 1930 || yearNacimiento > 2007) {
                        JOptionPane.showMessageDialog(Vista, "Debe ser mayor de edad y el límite de año es 1930", "Error", JOptionPane.ERROR_MESSAGE);
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
            Modelo.limpiar(Vista);
            Modelo.Mostrar(Vista.dtgempleado);

            System.out.println("empleado guardado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Vista, "Error al guardar el empleado: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("Error al guardar el empleado: " + ex.getMessage());
        }
    }
        
        if (e.getSource() == Vista.btnActualizar) {
        if (Vista.txtdui.getText().isEmpty() || 
            Vista.cmbCorreoEmpleado.getSelectedItem() == null || 
            Vista.txtnombre.getText().isEmpty()|| 
            Vista.txtapellido.getText().isEmpty() || 
            Vista.txtFecha.getDate() == null ||
            (Vista.txtImagenUrl.getIcon() == null && Vista.txtImagenUrl.getText().isEmpty()) ||
            Vista.txtTelefono.getText().isEmpty())  {
            JOptionPane.showMessageDialog(Vista, "no puedes actualizar a datos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
            System.out.println("Guardando empleado...");
            
            String dui = Vista.txtdui.getText();
            if (!dui.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(Vista, "El D.U.I. debe contener exactamente 9 dígitos numéricos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Modelo.setDuiEmpleado(dui);
            
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
                return;
            }
            Modelo.setApellido(apellido);
                Modelo.setImagenEmpleado(Modelo.getImagenEmpleado());
            
            Date fechaNacimiento = Vista.txtFecha.getDate();
                if (fechaNacimiento != null) {
                    Calendar calNacimiento = Calendar.getInstance();
                    calNacimiento.setTime(fechaNacimiento);

                    int yearNacimiento = calNacimiento.get(Calendar.YEAR);

                    if (yearNacimiento < 1930 || yearNacimiento > 2007) {
                        JOptionPane.showMessageDialog(Vista, "Debe ser mayor de edad y el límite de año es 1930", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String fechaNacimientoStr = dateFormat.format(fechaNacimiento);
                    Modelo.setFechaNacimiento(fechaNacimientoStr);
                } else {
                    JOptionPane.showMessageDialog(Vista, "Por favor selecciona una fecha de nacimiento", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            
            String telefono = Vista.txtTelefono.getText();
            if (!telefono.matches("\\d{8}")) { // Verifica que solo contenga 8 dígitos
                JOptionPane.showMessageDialog(Vista, "El teléfono debe contener exactamente 8 números", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }
            Modelo.setTelefono(telefono);

                Modelo.Actualizar(Vista.dtgempleado);
                Modelo.Mostrar(Vista.dtgempleado);
                Modelo.limpiar(Vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el carro", "Error", JOptionPane.WARNING_MESSAGE);
            }
        
        }
    }

        //se asegura de que no haya datos vacios porque significaria que no esta seleccionando nada
        if (e.getSource() == Vista.btnEliminar) {
                Modelo.Eliminar(Vista.dtgempleado);
                Modelo.Mostrar(Vista.dtgempleado);
                Modelo.limpiar(Vista);
            
       }
        
        //ejecuta la limpieza de campos
        if (e.getSource() == Vista.btnLimpiar) {
            Modelo.limpiar(Vista);
        }
        
        
    }
    
    private void mostrarImagenEnLabel(File imagen) {
        try {
            // Leer la imagen desde el archivo
            BufferedImage bufferedImage = ImageIO.read(imagen);

            // Crear el ImageIcon a partir de la imagen
            ImageIcon icon = new ImageIcon(bufferedImage);

            // Redimensionar la imagen para que se ajuste al JLabel
            Image imageScaled = icon.getImage().getScaledInstance(Vista.txtImagenUrl.getWidth(),
            Vista.txtImagenUrl.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imageScaled);

            // Establecer la imagen en el JLabel
            Vista.txtImagenUrl.setIcon(scaledIcon);

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