
package Controlador;

import Modelo.mdlMarca;
import Vistas.frmMarca;
import Vistas.frmModelo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;


public class ctrlMarca implements MouseListener, KeyListener {
    private mdlMarca modelo;
    private frmMarca vista;
    
    public ctrlMarca(mdlMarca modelo, frmMarca vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnAgregar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.tbMarcas.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        vista.btnVolver.addMouseListener(this);
        
        modelo.Mostrar(vista.tbMarcas);
        modelo.limpiar(vista);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == vista.btnVolver) {
            frmModelo nuevoModelo2Frame = new frmModelo();
            nuevoModelo2Frame.setLocationRelativeTo(null);
            nuevoModelo2Frame.setVisible(true);

            vista.dispose();
        }
        
        if (e.getSource() == vista.btnAgregar) {
        // Validar que los campos no estén vacíos
        if (vista.txtNombre.getText().isEmpty() || vista.txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            System.out.println("Guardando vehículo...");

            modelo.setNombre(vista.txtNombre.getText());
            modelo.setDescripcion(vista.txtDescripcion.getText());

            modelo.Guardar();
            modelo.Mostrar(vista.tbMarcas);
            modelo.limpiar(vista);

            System.out.println("Marca registrada correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar la marca: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("Error al guardar la marca: " + ex.getMessage());
        }
    }
        
        if (e.getSource() == vista.btnActualizar) {
        if (vista.txtNombre.getText().isEmpty() || vista.txtDescripcion.getText().isEmpty())  {
            JOptionPane.showMessageDialog(vista, "no puedes actualizar a datos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                modelo.setNombre(vista.txtNombre.getText());
                modelo.setDescripcion(vista.txtDescripcion.getText());

                modelo.Actualizar(vista.tbMarcas);
                modelo.Mostrar(vista.tbMarcas);
                modelo.limpiar(vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al actualizar la marca", "Error", JOptionPane.WARNING_MESSAGE);
            }
        
        }
    }

        //se asegura de que no haya datos vacios porque significaria que no esta seleccionando nada
        if (e.getSource() == vista.btnEliminar) {
            modelo.Eliminar(vista.tbMarcas);
                modelo.Mostrar(vista.tbMarcas);
                modelo.limpiar(vista);
       }
        
        //ejecuta la limpieza de campos
        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }
        
        //carga los datos en la tabla
        if (e.getSource() == vista.tbMarcas) {
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
