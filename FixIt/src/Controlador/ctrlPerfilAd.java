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
    // Crear el JFileChooser para seleccionar un archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Imagen");

        // Abrir el diálogo para seleccionar una imagen
        int result = fileChooser.showOpenDialog(Vista);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File imagenSeleccionada = fileChooser.getSelectedFile();

            // Establecer la imagen seleccionada en el modelo
            Modelo.setImg(imagenSeleccionada);

            // Mostrar la imagen en el JLabel lblImagenCarro
            mostrarImagenEnLabel(imagenSeleccionada);

            // Mostrar el nombre de la imagen seleccionada
            JOptionPane.showMessageDialog(Vista, "Imagen seleccionada: " + imagenSeleccionada.getName());
        } else {
            JOptionPane.showMessageDialog(Vista, "No se seleccionó ninguna imagen.");
        }
    }
        
        if (e.getSource() == Vista.btnActTelefono) {
        if (Vista.txtTelefono.getText().isEmpty())  {
            JOptionPane.showMessageDialog(Vista, "no puedes actualizar a un dato vacio", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String telefono = Vista.txtTelefono.getText();
            if (!telefono.matches("\\d{8}")) { // Verifica que solo contenga 8 dígitos
                JOptionPane.showMessageDialog(Vista, "El teléfono debe contener exactamente 8 números", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Modelo.setTelefono(telefono);

            Modelo.ActualizarTel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el empleado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        
        }
    }
        
        if (e.getSource() == Vista.btnActImagen) {
        if (Vista..getIcon().isEmpty())  {
            JOptionPane.showMessageDialog(Vista, "no puedes actualizar a un dato vacio", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String telefono = Vista.txtTelefono.getText();
            if (!telefono.matches("\\d{8}")) { // Verifica que solo contenga 8 dígitos
                JOptionPane.showMessageDialog(Vista, "El teléfono debe contener exactamente 8 números", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Modelo.setTelefono(telefono);

            Modelo.ActualizarTel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el empleado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        
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
