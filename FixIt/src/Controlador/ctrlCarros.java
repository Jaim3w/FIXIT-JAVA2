/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Jaimew
 */
import Modelo.ClientesCarro;
import Modelo.ModeloCarro;
import Modelo.mdlCarros;
import Vistas.frmCarros;
import Vistas.frmModelo;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.html.ImageView;

public class ctrlCarros implements MouseListener, KeyListener {
    private mdlCarros Modelo;
    private frmCarros Vista;
    private ClientesCarro mClientes;
    private ModeloCarro mModelo;
    
    public ctrlCarros(mdlCarros Modelo, frmCarros Vista, ClientesCarro mClientes, ModeloCarro mModelo) {
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.mClientes = mClientes;
        this.mModelo = mModelo;
        
        Vista.BtnLimpiarcamposCarro.addMouseListener(this);
        Vista.btnActualizarCarro.addMouseListener(this);
        Vista.btnEliminarCarro.addMouseListener(this);
        Vista.btnGuardarCarro.addMouseListener(this);
        Vista.btnSubirImagen.addMouseListener(this);
        Vista.txtBuscarCarro.addKeyListener(this);
        Vista.tbListaCarros.addMouseListener(this);
        Vista.btnNewModelo.addMouseListener(this);
        
        //carga el contenido de los combo box
        this.mClientes.CargarComboClientes(Vista.cmbClienteCarro);
        this.mModelo.CargarComboModelos(Vista.cmbModeloCarro);
        
        Vista.cmbClienteCarro.addActionListener(e -> {
            if (e.getSource() == Vista.cmbClienteCarro) {
                System.out.println("ComboBox seleccionado");
                ClientesCarro selectedItem = (ClientesCarro) Vista.cmbClienteCarro.getSelectedItem();
                if (selectedItem != null) {
                    String dui = selectedItem.getDui_cliente();
                    mClientes.setDui_cliente(dui);
                    System.out.println("dui de cliente seleccionado: " + dui);
                } else {
                    System.out.println("No se seleccionó ningún cliente");
                }
            }
        });
        
        Vista.cmbModeloCarro.addActionListener(e -> {
            if (e.getSource() == Vista.cmbModeloCarro) {
                System.out.println("ComboBox seleccionado");
                ModeloCarro selectedItem = (ModeloCarro) Vista.cmbModeloCarro.getSelectedItem();
                if (selectedItem != null) {
                    String uuidModelo = selectedItem.getUUID_modelo();
                    mModelo.setUUID_modelo(uuidModelo);
                    System.out.println("uuid de modelo seleccionado: " + uuidModelo);
                } else {
                    System.out.println("No se seleccionó ningún modelo");
                }
            }
        });
        
        Modelo.Mostrar(Vista.tbListaCarros);
        cargarFecha();
        Modelo.limpiar(Vista);
    }
    
    //carga la fecha actual
    private void cargarFecha() {
        String fechaActual = Modelo.obtenerFechaActual(); // Llamada al modelo
        Vista.txtIngresoCarro.setText(fechaActual); // Actualiza la vista
    }
    
    private void guardarCarro() {
        System.out.println("Guardar carro");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnNewModelo) {
            frmModelo nuevoModeloFrame = new frmModelo();
            nuevoModeloFrame.setLocationRelativeTo(null);
            nuevoModeloFrame.setVisible(true);

            nuevoModeloFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Refrescar la lista de modelos si es necesario
                    mModelo.CargarComboModelos(Vista.cmbModeloCarro);
                }
            });
        }

        
    // ejecución al dar clic a boton guardar
    if (e.getSource() == Vista.btnGuardarCarro) {
        // Validar que los campos no estén vacíos
        if (Vista.txtPlacaCarro.getText().isEmpty() || Vista.cmbClienteCarro.getSelectedItem() == null || Vista.cmbModeloCarro.getSelectedItem() == null
                || Vista.txtColorCarro.getText().isEmpty() || Vista.txtAnoCarro.getText().isEmpty()
                || Vista.txtDescripcionCarro.getText().isEmpty() || Vista.lblImagenCarro.getIcon() == null) {
            JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Detener la ejecución si hay campos vacíos
        }

        // Validar que la placa no exceda 8 caracteres
        String placa = Vista.txtPlacaCarro.getText();
        if (placa.length() > 8) {
            JOptionPane.showMessageDialog(Vista, "La placa no debe exceder 8 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Detener la ejecución si la validación falla
        }

        try {
            int añoCarro = Integer.parseInt(Vista.txtAnoCarro.getText());
            if (añoCarro < 1900 || añoCarro >= 2026 || Vista.txtAnoCarro.getText().length() != 4) {
                JOptionPane.showMessageDialog(Vista, "El año debe ser un número de 4 dígitos menor a 2026 y mayor 1900", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución si la validación falla
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(Vista, "El año debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Detener la ejecución si hay un error de formato
        }

        // Se ejecuta si no hay campos vacíos
        try {
            System.out.println("Guardando vehículo...");

            Modelo.setPlaca(placa); // Asegurarse de usar la placa validada

            // Obtiene los clientes y modelos del combo box
            ClientesCarro clienteSeleccionado = (ClientesCarro) Vista.cmbClienteCarro.getSelectedItem();
            ModeloCarro modeloSeleccionado = (ModeloCarro) Vista.cmbModeloCarro.getSelectedItem();

            Modelo.setCliente(clienteSeleccionado.getDui_cliente());
            Modelo.setModelo(modeloSeleccionado.getUUID_modelo());
            Modelo.setColor(Vista.txtColorCarro.getText());
            Modelo.setAnoCarro(Vista.txtAnoCarro.getText());
            Modelo.setDescripcion(Vista.txtDescripcionCarro.getText());
            Modelo.setImagenSeleccionada(Modelo.getImagenSeleccionada());

            Modelo.Guardar();
            Modelo.Mostrar(Vista.tbListaCarros);
            Modelo.limpiar(Vista);

            System.out.println("Carro guardado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Vista, "Error al guardar el vehículo: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("Error al guardar el vehículo: " + ex.getMessage());
        }
    }

        
        
        if (e.getSource() == Vista.btnSubirImagen) {
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
        
        if (e.getSource() == Vista.btnActualizarCarro) {
        if (Vista.txtPlacaCarro.getText().isEmpty() || Vista.cmbClienteCarro.getSelectedItem() == null || Vista.cmbModeloCarro.getSelectedItem() == null
                || Vista.txtColorCarro.getText().isEmpty() || Vista.txtAnoCarro.getText().isEmpty()
                || Vista.txtDescripcionCarro.getText().isEmpty() ||
                    (Vista.lblImagenCarro.getIcon() == null && Vista.lblImagenCarro.getText().isEmpty()))  {
            JOptionPane.showMessageDialog(Vista, "no puedes actualizar a datos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                // Establecer datos en el modelo
                Modelo.setPlaca(Vista.txtPlacaCarro.getText());

                ClientesCarro clienteSeleccionado = (ClientesCarro) Vista.cmbClienteCarro.getSelectedItem();
                ModeloCarro modeloSeleccionado = (ModeloCarro) Vista.cmbModeloCarro.getSelectedItem();

                Modelo.setCliente(clienteSeleccionado.getDui_cliente());
                Modelo.setModelo(modeloSeleccionado.getUUID_modelo());

                if (Modelo.getImagenSeleccionada() != null) {
                    Modelo.setImagenSeleccionada(Modelo.getImagenSeleccionada());
                }

                Modelo.setColor(Vista.txtColorCarro.getText());
                Modelo.setAnoCarro(Vista.txtAnoCarro.getText());
                Modelo.setDescripcion(Vista.txtDescripcionCarro.getText());

                Modelo.Actualizar(Vista.tbListaCarros);
                Modelo.Mostrar(Vista.tbListaCarros);
                Modelo.limpiar(Vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el carro", "Error", JOptionPane.WARNING_MESSAGE);
            }
        
        }
    }

        //se asegura de que no haya datos vacios porque significaria que no esta seleccionando nada
        if (e.getSource() == Vista.btnEliminarCarro) {
            Modelo.Eliminar(Vista.tbListaCarros);
                Modelo.Mostrar(Vista.tbListaCarros);
                Modelo.limpiar(Vista);
       }
        
        //ejecuta la limpieza de campos
        if (e.getSource() == Vista.BtnLimpiarcamposCarro) {
            Modelo.limpiar(Vista);
        }
        
        //carga los datos en la tabla
        if (e.getSource() == Vista.tbListaCarros) {
            Modelo.cargarDatosTabla(Vista);
        }
    }
    
    //hace que la imagen que se seleccione, aparezca en un label
    private void mostrarImagenEnLabel(File imagen) {
        try {
            // Leer la imagen desde el archivo
            BufferedImage bufferedImage = ImageIO.read(imagen);

            // Crear el ImageIcon a partir de la imagen
            ImageIcon icon = new ImageIcon(bufferedImage);

            // Redimensionar la imagen para que se ajuste al JLabel
            Image imageScaled = icon.getImage().getScaledInstance(Vista.lblImagenCarro.getWidth(),
            Vista.lblImagenCarro.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imageScaled);

            // Establecer la imagen en el JLabel
            Vista.lblImagenCarro.setIcon(scaledIcon);

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
        if (e.getSource() == Vista.txtAnoCarro) {
        char c = e.getKeyChar();
        
        if (!Character.isDigit(c)) {
            e.consume(); // Ignora el evento si no es un dígito
        }
        
        // Validar la longitud y el valor máximo
        String text = Vista.txtAnoCarro.getText() + c; // Texto actual + nuevo carácter
        if (text.length() > 4) {
            e.consume(); // Ignora si excede 4 caracteres
        } else if (text.length() == 4) {
            try {
                int year = Integer.parseInt(text);
                if (year >= 2026) {
                    e.consume(); // Ignora si el año es mayor o igual a 2026
                }
            } catch (NumberFormatException ex) {
                e.consume(); // Ignora si no se puede convertir a número
            }
        }
    }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
    
    
    //ejecuta el buscador al empezar a escribir en la barra de busqueda
    @Override
    public void keyReleased(KeyEvent e) {
            if (e.getSource() == Vista.txtBuscarCarro) {
                Modelo.Buscar(Vista.tbListaCarros, Vista.txtBuscarCarro);
        }    }
}