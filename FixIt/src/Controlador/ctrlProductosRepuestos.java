/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Categorias;
import Modelo.mdlProductosRepuestos;
import Vistas.frmProductosRepuestos;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

/**
 *
 * @author Kevin
 */
public class ctrlProductosRepuestos implements MouseListener, KeyListener {
    
    private mdlProductosRepuestos Modelo;
    private frmProductosRepuestos Vista;
    private Categorias mCategorias;
    
    public ctrlProductosRepuestos (mdlProductosRepuestos Modelo, frmProductosRepuestos Vista, Categorias mCategorias) {
    this.Modelo = Modelo;
    this.Vista = Vista;
    this.mCategorias = mCategorias;
        
    Vista.btnAgregarR.addMouseListener(this);
    Vista.btnEliminarR.addMouseListener(this);
    Vista.btnActualizarR.addMouseListener(this);
    Vista.btnBuscarR.addMouseListener(this);
    Vista.btnLimpiarR.addMouseListener(this);
    Vista.tbListaProductosRepuestos.addMouseListener(this);
    
    //carga el contenido de los combo box
        this.mCategorias.CargarComboCategorias(Vista.cmbCategoria);
        
        Vista.cmbCategoria.addActionListener(e -> {
            if (e.getSource() == Vista.cmbCategoria) {
                System.out.println("ComboBox seleccionado");
                Categorias selectedItem = (Categorias) Vista.cmbCategoria.getSelectedItem();
                if (selectedItem != null) {
                    String uuid = selectedItem.getUUID_item();
                    mCategorias.setUUID_item(uuid);
                    System.out.println("Categoria seleccionada: " + uuid);
                } else {
                    System.out.println("No se seleccionó ningúna Categoria");
                }
            }
        });
        
        Modelo.Mostrar(Vista.tbListaProductosRepuestos);
        Modelo.limpiar(Vista);
    }
    
    private void guardarProdcutoRepuesto() {
        System.out.println("Guardar Producto o Repuesto");
    }
    
    @Override
   public void mouseClicked(MouseEvent e) {
    // ejecución al dar clic a boton guardar
    if (e.getSource() == Vista.btnAgregarR) {
        // Validar que los campos no estén vacíos
        if (Vista.txtNombreR.getText().isEmpty() || Vista.cmbCategoria.getSelectedItem() == null
                || Vista.txtPrecioR.getText().isEmpty() || Vista.imgR.getIcon() == null) {
            JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Detener la ejecución si hay campos vacíos
        }
    }
    
    try {
        System.out.println("Guardando Repuesto o Producto...");
    
        Categorias categoriaSeleccionada = (Categorias) Vista.cmbCategoria.getSelectedItem();

            Modelo.setItem(categoriaSeleccionada.getUUID_item());
            Modelo.setNombre(Vista.txtNombreR.getText());
            Modelo.setImagenSeleccionada(Modelo.getImagenSeleccionada());
            Modelo.setPrecio(Integer.parseInt(Vista.txtPrecioR.getText()));

            Modelo.Guardar();
            Modelo.Mostrar(Vista.tbListaProductosRepuestos);
            Modelo.limpiar(Vista);
            
            System.out.println("Repuesto o Prodcuto guardado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Vista, "Error al guardar el Repuesto o Prodcuto: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("Error al guardar el Repuesto o Prodcuto: " + ex.getMessage());
        }
    
     if (e.getSource() == Vista.btnAgregarImgR) {
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
     
     if (e.getSource() == Vista.btnActualizarR) {
        if (Vista.txtNombreR.getText().isEmpty() || Vista.cmbCategoria.getSelectedItem() == null
                || Vista.txtPrecioR.getText().isEmpty() || Vista.imgR.getIcon() == null) {
            JOptionPane.showMessageDialog(Vista, "No se puede actulizar campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            try {

                Categorias categoriaSeleccionada = (Categorias) Vista.cmbCategoria.getSelectedItem();

                Modelo.setItem(categoriaSeleccionada.getUUID_item());               

                if (Modelo.getImagenSeleccionada() != null) {
                    Modelo.setImagenSeleccionada(Modelo.getImagenSeleccionada());
                }

            Modelo.setNombre(Vista.txtNombreR.getText());
            Modelo.setPrecio(Integer.parseInt(Vista.txtPrecioR.getText()));

                Modelo.Actualizar(Vista.tbListaProductosRepuestos);
                Modelo.Mostrar(Vista.tbListaProductosRepuestos);
                Modelo.limpiar(Vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el Producto o Repuesto", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if (e.getSource() == Vista.btnEliminarR) {
            if (Vista.txtNombreR.getText().isEmpty() || Vista.cmbCategoria.getSelectedItem() == null
                || Vista.txtPrecioR.getText().isEmpty() || Vista.imgR.getIcon() == null) {
            JOptionPane.showMessageDialog(Vista, "No se puede eliminar campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Modelo.Eliminar(Vista.tbListaProductosRepuestos);
                Modelo.Mostrar(Vista.tbListaProductosRepuestos);
                Modelo.limpiar(Vista);
            }
       }
        
        //ejecuta la limpieza de campos
        if (e.getSource() == Vista.btnLimpiarR) {
            Modelo.limpiar(Vista);
        }
        
        //carga los datos en la tabla
        if (e.getSource() == Vista.tbListaProductosRepuestos) {
            Modelo.cargarDatosTabla(Vista);
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
            Image imageScaled = icon.getImage().getScaledInstance(Vista.imgR.getWidth(),
            Vista.imgR.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imageScaled);

            // Establecer la imagen en el JLabel
            Vista.imgR.setIcon(scaledIcon);

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
    
    
    //ejecuta el buscador al empezar a escribir en la barra de busqueda
    @Override
    public void keyReleased(KeyEvent e) {
            if (e.getSource() == Vista.txtBuscarR) {
                Modelo.Buscar(Vista.tbListaProductosRepuestos, Vista.txtBuscarR);
        }
            
    }
        
}    
  


