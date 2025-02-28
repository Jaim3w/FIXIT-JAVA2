
package Controlador;

import Modelo.MarcasCarro;
import Modelo.mdlModelo;
import Vistas.frmMarca;
import Vistas.frmModelo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;


public class ctrlModelo implements MouseListener, KeyListener {
    private mdlModelo modelo;
    private frmModelo vista;
    private MarcasCarro mMarcas;
    
    public ctrlModelo(mdlModelo modelo, frmModelo vista, MarcasCarro mMarcas) {
        this.modelo = modelo;
        this.vista = vista;
        this.mMarcas = mMarcas;
        
        vista.btnAgregar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.tbModelos.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        vista.btnNewMarca.addMouseListener(this);
        
        this.mMarcas.CargarComboMarcas(vista.cmbMarca);
        vista.cmbMarca.addActionListener(e -> {
            if (e.getSource() == vista.cmbMarca) {
                System.out.println("ComboBox seleccionado");
                MarcasCarro selectedItem = (MarcasCarro) vista.cmbMarca.getSelectedItem();
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
        
        if (e.getSource() == vista.btnNewMarca) {
            frmMarca nuevoMarcaFrame = new frmMarca();
            nuevoMarcaFrame.setLocationRelativeTo(null);
            nuevoMarcaFrame.setVisible(true);

            vista.dispose();
            
            nuevoMarcaFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Refrescar la lista de modelos si es necesario
                    mMarcas.CargarComboMarcas(vista.cmbMarca);
                }
            });
        }
        
        if (e.getSource() == vista.btnAgregar) {
        // Validar que los campos no estén vacíos
        if (vista.txtNombre.getText().isEmpty() || vista.cmbMarca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            System.out.println("Guardando vehículo...");
            MarcasCarro modeloSeleccionado = (MarcasCarro) vista.cmbMarca.getSelectedItem();

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
                MarcasCarro marcaSeleccionado = (MarcasCarro) vista.cmbMarca.getSelectedItem();
                
                modelo.setNombre(vista.txtNombre.getText());
                modelo.setUuidMarca(marcaSeleccionado.getUuidMarca());

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
