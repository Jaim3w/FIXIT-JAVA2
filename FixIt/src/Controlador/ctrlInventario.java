/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ProductosRepuestos;
import Modelo.Proveedores;
import Modelo.mdlInventario;
import Vistas.frmInventario;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class ctrlInventario implements MouseListener, KeyListener{
    
    private mdlInventario Modelo;
    private frmInventario Vista;
    private ProductosRepuestos mProductosRepuestos;
    private Proveedores mProveedores;
    
    public ctrlInventario (mdlInventario Modelo, frmInventario Vista, ProductosRepuestos mProductosRepuestos, Proveedores mProveedores) {
    this.Modelo = Modelo;
    this.Vista = Vista;
    this.mProductosRepuestos = mProductosRepuestos;
    this.mProveedores = mProveedores;
        
    Vista.btnGuardarInventario.addMouseListener(this);
    Vista.btnBorrarInventario.addMouseListener(this);
    Vista.btnActualizarInventario.addMouseListener(this);
    Vista.btnBuscarInventario.addMouseListener(this);
    Vista.btnLimpiarInventario.addMouseListener(this);
    Vista.tbInventario.addMouseListener(this);
    
    //carga el contenido de los combo box
        this.mProductosRepuestos.CargarComboCategorias(Vista.cmbProdcutosRepuestos);
        
        Vista.cmbProdcutosRepuestos.addActionListener(e -> {
            if (e.getSource() == Vista.cmbProdcutosRepuestos) {
                System.out.println("ComboBox seleccionado");
                ProductosRepuestos selectedItem = (ProductosRepuestos) Vista.cmbProdcutosRepuestos.getSelectedItem();
                if (selectedItem != null) {
                    String uuid = selectedItem.getUUID_ProductoRepuesto();
                    mProductosRepuestos.setUUID_ProductoRepuesto(uuid);
                    System.out.println("Categoria seleccionada: " + uuid);
                } else {
                    System.out.println("No se seleccionó ningúna Categoria");
                }
            }
        });
        
        Modelo.mostrarInventario(Vista.tbInventario);
        Modelo.limpiarCampos(Vista);
    
//carga el contenido de los combo box
        this.mProveedores.CargarComboCategorias(Vista.cmbProveedores);
        
        Vista.cmbProveedores.addActionListener(e -> {
            if (e.getSource() == Vista.cmbProveedores) {
                System.out.println("ComboBox seleccionado");
                Proveedores selectedItem = (Proveedores) Vista.cmbProveedores.getSelectedItem();
                if (selectedItem != null) {
                    String uuid = selectedItem.getCodigo();
                    mProveedores.setCodgio(uuid);
                    System.out.println("Categoria seleccionada: " + uuid);
                } else {
                    System.out.println("No se seleccionó ningúna Categoria");
                }
            }
        });
        
        Modelo.mostrarInventario(Vista.tbInventario);
        Modelo.limpiarCampos(Vista);
    }
    
    
    private void guardarInventario() {
        System.out.println("Guardar Inventario");
    }
    
    @Override
   public void mouseClicked(MouseEvent e) {
    // ejecución al dar clic a boton guardar
    if (e.getSource() == Vista.btnGuardarInventario) {
        // Validar que los campos no estén vacíos
        if (Vista.txtCantidad.getText().isEmpty() || Vista.cmbProdcutosRepuestos.getSelectedItem() == null
                || Vista.txtFechaInventario.getText().isEmpty() || Vista.cmbProveedores.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Detener la ejecución si hay campos vacíos
        }
    }
    
    try {
        System.out.println("Guardando Inventario...");
    
        ProductosRepuestos productoRepuestoSeleccionado = (ProductosRepuestos) Vista.cmbProdcutosRepuestos.getSelectedItem();
        Proveedores proveedorSeleccionado = (Proveedores) Vista.cmbProveedores.getSelectedItem();

            Modelo.setUUID_productoRepuesto(productoRepuestoSeleccionado.getUUID_ProductoRepuesto());
            Modelo.setCantidad(Integer.parseInt(Vista.txtCantidad.getText()));
            Modelo.setCodigo_proveedor(proveedorSeleccionado.getCodigo());
            Modelo.setFechaSuministro(Vista.txtFechaInventario.getText());

            Modelo.insertarInventario();
            Modelo.mostrarInventario(Vista.tbInventario);
            Modelo.limpiarCampos(Vista);
            
            System.out.println("Inventario guardado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Vista, "Error al guardar el Inventario: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("Error al guardar el Inventario: " + ex.getMessage());
        }
    
     
     if (e.getSource() == Vista.btnActualizarInventario) {
        if (Vista.txtCantidad.getText().isEmpty() || Vista.cmbProdcutosRepuestos.getSelectedItem() == null
                || Vista.txtFechaInventario.getText().isEmpty() || Vista.cmbProveedores.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(Vista, "No se puede actulizar campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            try {

        ProductosRepuestos productoRepuestoSeleccionado = (ProductosRepuestos) Vista.cmbProdcutosRepuestos.getSelectedItem();
        Proveedores proveedorSeleccionado = (Proveedores) Vista.cmbProveedores.getSelectedItem();

                Modelo.setUUID_productoRepuesto(productoRepuestoSeleccionado.getUUID_ProductoRepuesto());               
                Modelo.setCodigo_proveedor(proveedorSeleccionado.getCodigo()); 

            Modelo.setCantidad(Integer.parseInt(Vista.txtCantidad.getText()));
            Modelo.setFechaSuministro(Vista.txtFechaInventario.getText());
            
                Modelo.actualizarInventario(Vista.tbInventario);
                Modelo.mostrarInventario(Vista.tbInventario);
                Modelo.limpiarCampos(Vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el Inventario", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if (e.getSource() == Vista.btnBorrarInventario) {
            if (Vista.txtCantidad.getText().isEmpty() || Vista.cmbProdcutosRepuestos.getSelectedItem() == null
                || Vista.txtFechaInventario.getText().isEmpty() || Vista.cmbProveedores.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(Vista, "No se puede eliminar campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Modelo.eliminarInventario(Vista.tbInventario);
                Modelo.mostrarInventario(Vista.tbInventario);
                Modelo.limpiarCampos(Vista);
            }
       }
        
        //ejecuta la limpieza de campos
        if (e.getSource() == Vista.btnLimpiarInventario) {
            Modelo.limpiarCampos(Vista);
        }
        
        //carga los datos en la tabla
        if (e.getSource() == Vista.tbInventario) {
            Modelo.cargarDatosEnVista(Vista);
          }
      }
   }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
   
}

