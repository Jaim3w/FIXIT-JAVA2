package Controlador;

import Modelo.Conexion;
import Modelo.mdlPerfilAd;
import Vistas.frmPerfilAd;
import java.awt.Image;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ctrlPerfilAd implements MouseListener, KeyListener {
    private mdlPerfilAd Modelo;
    private frmPerfilAd Vista;

    public ctrlPerfilAd(mdlPerfilAd modelo, frmPerfilAd vista) {
        this.Modelo = modelo;
        this.Vista = vista;
        cargarDatos(); // Llama a cargarDatos cuando se inicializa el controlador
    
        vista.btnActImagen.addMouseListener(this);
        vista.btnActTelefono.addMouseListener(this);
    }

    // Método para cargar los datos iniciales desde el modelo a la vista
    public void cargarDatos() {
    Connection conexion = Conexion.getConexion();  // Obtener la conexión

    if (conexion != null) {
        Modelo.cargarDatosPerfil();  // Llama al método que obtiene los datos

        // Asignar los datos obtenidos a los componentes de la vista
        Vista.getTxtNombre().setText(Modelo.getNombre());
        Vista.getTxtApellidos().setText(Modelo.getApellidos());
        Vista.getTxtCorreo().setText(Modelo.getCorreo());
        Vista.getTxtTelefono().setText(Modelo.getTelefono());
        Vista.getTxtNacimiento().setText(Modelo.getNacimiento());
        Vista.getTxtDui().setText(Modelo.getDui());
        Vista.getTxtImagenUrl().setText(Modelo.getImgUrl());

        try {
            conexion.close();  // Cerrar la conexión
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Error al conectar a la base de datos.");
    }
}


    // Método para manejar la selección y actualización de imágenes
    private void mostrarImagenEnLabel(File imagen) {
        try {
            BufferedImage bufferedImage = ImageIO.read(imagen);  // Leer la imagen
            ImageIcon icon = new ImageIcon(bufferedImage);

            // Redimensionar la imagen
            Image imageScaled = icon.getImage().getScaledInstance(Vista.txtImgUrl.getWidth(),
                Vista.txtImgUrl.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imageScaled);

            // Mostrar la imagen redimensionada en el JLabel
            Vista.txtImgUrl.setIcon(scaledIcon);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Vista, "Error al cargar la imagen.");
        }
    }

    // Implementación del evento mouseClicked para el botón de imagen y teléfono
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnActImagen) {
            if (Vista.btnActImagen.getText().equals("Actualizar imagen")) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar Imagen");

                int result = fileChooser.showOpenDialog(Vista);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File imagenSeleccionada = fileChooser.getSelectedFile();

                    Modelo.setImg(imagenSeleccionada);  // Guardar la imagen en el modelo
                    mostrarImagenEnLabel(imagenSeleccionada);  // Mostrarla en la vista

                    Vista.btnActImagen.setText("Aceptar");  // Cambiar el texto del botón
                    JOptionPane.showMessageDialog(Vista, "Imagen seleccionada: " + imagenSeleccionada.getName());
                } else {
                    JOptionPane.showMessageDialog(Vista, "No se seleccionó ninguna imagen.");
                }

            } else if (Vista.btnActImagen.getText().equals("Aceptar")) {
                if (Vista.txtImgUrl.getIcon() == null) {
                    JOptionPane.showMessageDialog(Vista, "Debes seleccionar una imagen", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Modelo.setImg(Modelo.getImg());  // Establecer imagen en el modelo
                        Modelo.actualizarImg();  // Actualizar la imagen en la base de datos

                        JOptionPane.showMessageDialog(Vista, "Imagen actualizada correctamente.");
                        Vista.btnActImagen.setText("Seleccionar Imagen");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Vista, "Error al actualizar la imagen", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }

        // Actualización del teléfono
        if (e.getSource() == Vista.btnActTelefono) {
        // Si el botón es "Actualizar teléfono", habilita el TextField
        if (Vista.btnActTelefono.getText().equals("Actualizar telefono")) {
            Vista.txtTelefono.setEnabled(true); // Habilita el campo para que se pueda editar
            Vista.txtTelefono.requestFocus(); // Pone el foco en el campo
            Vista.btnActTelefono.setText("Aceptar"); // Cambia el texto del botón a "Aceptar"

        // Si el botón es "Aceptar", verifica el nuevo número de teléfono
        } else if (Vista.btnActTelefono.getText().equals("Aceptar")) {
            // Verifica si el campo de texto está vacío
            if (Vista.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "No puedes actualizar a un dato vacío", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    String telefono = Vista.txtTelefono.getText(); // Obtiene el texto del campo

                    // Verifica si el número de teléfono tiene exactamente 8 dígitos
                    if (!telefono.matches("\\d{8}")) {
                        JOptionPane.showMessageDialog(Vista, "El teléfono debe contener exactamente 8 números", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si la validación falla
                    }

                    // Establece el nuevo teléfono en el modelo y lo actualiza en la base de datos
                    Modelo.setTelefono(telefono);  
                    Modelo.actualizarTel();  

                    // Muestra un mensaje de éxito
                    JOptionPane.showMessageDialog(Vista, "Teléfono actualizado correctamente.");
                    Vista.txtTelefono.setEnabled(false); // Deshabilita el campo de texto
                    Vista.btnActTelefono.setText("Actualizar telefono"); // Restablece el texto del botón

                } catch (Exception ex) {
                    // Maneja cualquier excepción que ocurra durante la actualización
                    JOptionPane.showMessageDialog(Vista, "Error al actualizar el teléfono", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}

    // Implementaciones vacías de los métodos no utilizados
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

