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
    }

    public void cargarDatos() {
    // Obtener la conexión usando el método estático
    Connection conexion = Conexion.getConexion();

    if (conexion != null) {
        // Llamar a cargarDatosPerfil sin pasar el id, ya que ahora usa el correo global
        Modelo.cargarDatosPerfil(); // Ya no es necesario pasar parámetros
        
        // Asignar los datos obtenidos a los componentes de la vista
        Vista.getTxtNombre().setText(Modelo.getNombre());
        Vista.getTxtApellidos().setText(Modelo.getApellidos());
        Vista.getTxtCorreo().setText(Modelo.getCorreo());
        Vista.getTxtTelefono().setText(Modelo.getTelefono());
        Vista.getTxtNacimiento().setText(Modelo.getNacimiento());
        Vista.getTxtDui().setText(Modelo.getDui());

        // Cerrar la conexión
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Error al conectar a la base de datos.");
    }
}


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnActImagen) {
    if (Vista.btnActImagen.getText().equals("Actualizar imagen")) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Imagen");

        int result = fileChooser.showOpenDialog(Vista);

        if (result == JFileChooser.APPROVE_OPTION) {
            File imagenSeleccionada = fileChooser.getSelectedFile();

            Modelo.setImg(imagenSeleccionada);

            mostrarImagenEnLabel(imagenSeleccionada);

            // Cambiar el texto del botón a "Actualizar Imagen"
            Vista.btnActImagen.setText("Aceptar");

            JOptionPane.showMessageDialog(Vista, "Imagen seleccionada: " + imagenSeleccionada.getName());
        } else {
            JOptionPane.showMessageDialog(Vista, "No se seleccionó ninguna imagen.");
        }

    } else if (Vista.btnActImagen.getText().equals("Aceptar")) {
        if (Vista.txtImgUrl.getIcon() == null)  {
            JOptionPane.showMessageDialog(Vista, "Debes seleccionar una imagen", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Modelo.setImg(Modelo.getImg());

                Modelo.ActualizarImg();

                JOptionPane.showMessageDialog(Vista, "Imagen actualizada correctamente.");

                Vista.btnActImagen.setText("Seleccionar Imagen");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar la imagen", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

        
        if (e.getSource() == Vista.btnActTelefono) {
    // Si el botón dice "Actualizar Telefono"
    if (Vista.btnActTelefono.getText().equals("Actualizar Telefono")) {
        Vista.txtTelefono.setEnabled(true);
        Vista.txtTelefono.requestFocus();

        Vista.btnActTelefono.setText("Aceptar");

    } else if (Vista.btnActTelefono.getText().equals("Aceptar")) {
        if (Vista.txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Vista, "No puedes actualizar a un dato vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String telefono = Vista.txtTelefono.getText();

                if (!telefono.matches("\\d{8}")) {
                    JOptionPane.showMessageDialog(Vista, "El teléfono debe contener exactamente 8 números", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Modelo.setTelefono(telefono);
                
                Modelo.ActualizarTel();

                JOptionPane.showMessageDialog(Vista, "Teléfono actualizado correctamente.");
                Vista.txtTelefono.setEnabled(false);
                Vista.btnActTelefono.setText("Actualizar Telefono");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el teléfono", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

        
        /*if (e.getSource() == Vista.btnActImagen) {
        if (Vista.txtImgUrl.getIcon() == null)  {
            JOptionPane.showMessageDialog(Vista, "Debes seleccionar una imagen", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
            Modelo.setImg(Modelo.getImg());

            Modelo.ActualizarTel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el empleado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        
        }
    }*/
    }
    
    private void mostrarImagenEnLabel(File imagen) {
        try {
            // Leer la imagen desde el archivo
            BufferedImage bufferedImage = ImageIO.read(imagen);

            // Crear el ImageIcon a partir de la imagen
            ImageIcon icon = new ImageIcon(bufferedImage);

            // Redimensionar la imagen para que se ajuste al JLabel
            Image imageScaled = icon.getImage().getScaledInstance(Vista.txtImgUrl.getWidth(),
            Vista.txtImgUrl.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imageScaled);

            // Establecer la imagen en el JLabel
            Vista.txtImgUrl.setIcon(scaledIcon);

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
