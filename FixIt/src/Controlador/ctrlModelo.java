
package Controlador;

import Modelo.ClientesCarro;
import Modelo.ModeloCarro;
import Modelo.mdlModelo;
import Vistas.frmModelo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ctrlModelo implements MouseListener, KeyListener {
    private mdlModelo modelo;
    private frmModelo vista;
    private ModeloCarro mMarcas;
    
    public ctrlModelo(mdlModelo modelo, frmModelo vista, ModeloCarro mMarcas) {
        this.modelo = modelo;
        this.vista = vista;
        this.mMarcas = mMarcas;
        
        vista.btnAgregar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        
        this.mMarcas.CargarComboModelos(vista.cmbMarca);
        vista.cmbMarca.addActionListener(e -> {
            if (e.getSource() == vista.cmbMarca) {
                System.out.println("ComboBox seleccionado");
                ModeloCarro selectedItem = (ModeloCarro) vista.cmbMarca.getSelectedItem();
                if (selectedItem != null) {
                    String uuidMarca = selectedItem.getUuidMarca();
                    mMarcas.setUuidMarca(uuidMarca);
                    System.out.println("uuid del modelo seleccionado: " + uuidMarca);
                } else {
                    System.out.println("No se seleccionó ningún modelo");
                }
            }
        });
        
        modelo.Mostrar(vista.tbModelos);
        modelo.limpiar(vista);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnAgregar) {
        // Validar que los campos no estén vacíos
        if (vista.txtNombre.getText().isEmpty() || vista.cmbMarca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            System.out.println("Guardando vehículo...");
            ModeloCarro modeloSeleccionado = (ModeloCarro) vista.cmbMarca.getSelectedItem();

            modelo.setNombre(vista.txtNombre.getText());
            modelo.setUuidMarca(modeloSeleccionado.getUuidMarca());

            modelo.Guardar();
            modelo.Mostrar(vista.tbModelos);
            modelo.limpiar(vista);

            System.out.println("Carro guardado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar el vehículo: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("Error al guardar el vehículo: " + ex.getMessage());
        }
    }
        
        if (e.getSource() == vista.btnActualizar) {
        if (vista.txtNombre.getText().isEmpty() || vista.cmbMarca.getSelectedItem() == null)  {
            JOptionPane.showMessageDialog(vista, "no puedes actualizar a datos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ModeloCarro modeloSeleccionado = (ModeloCarro) vista.cmbMarca.getSelectedItem();
                
                modelo.setNombre(vista.txtNombre.getText());
                modelo.setUuidMarca(modeloSeleccionado.getUuidMarca());

                modelo.Actualizar(vista.tbModelos);
                modelo.Mostrar(vista.tbModelos);
                modelo.limpiar(vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al actualizar el carro", "Error", JOptionPane.WARNING_MESSAGE);
            }
        
        }
    }

        //se asegura de que no haya datos vacios porque significaria que no esta seleccionando nada
        if (e.getSource() == vista.btnEliminar) {
            modelo.Eliminar(vista.tbModelos);
                modelo.Mostrar(vista.tbModelos);
                modelo.limpiar(vista);
       }
        
        //ejecuta la limpieza de campos
        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }
        
        //carga los datos en la tabla
        if (e.getSource() == vista.tbModelos) {
            modelo.cargarDatosTabla(vista);
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
