package Controlador;

import Modelo.mdlProveedor;
import Vistas.frmProveedor;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class ctrlProveedor implements MouseListener, KeyListener {
    private mdlProveedor Modelo;
    private frmProveedor Vista;

    public ctrlProveedor(mdlProveedor Modelo, frmProveedor Vista) {
        this.Modelo = Modelo;
        this.Vista = Vista;

        this.Vista.btnGuardarProveedor.addActionListener(e -> guardarProveedor());

        System.out.println("Controlador creado");

        Vista.btnGuardarProveedor.addMouseListener(this);
        Vista.btnEliminarProveedor.addMouseListener(this);
        Vista.btnActualizarProveedor.addMouseListener(this);
        Vista.btnLimpiarCamposProveedor.addMouseListener(this);
        Vista.txtBuscarProveedor.addKeyListener(this);
        Vista.tbProveedores.addMouseListener(this);

        Modelo.Mostrar(Vista.tbProveedores);
    }

    private void guardarProveedor() {
        System.out.println("Guardar proveedor");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnGuardarProveedor) {
            if (!validarCampos()) {
                return;
            }

            try {
                System.out.println("Guardando proveedor...");

                // Asignar lo de la vista al modelo
                Modelo.setCodigo(Vista.txtCodigo.getText());
                Modelo.setRepresentante(Vista.txtRepresentante.getText());
                Modelo.setMarca(Vista.txtMarca.getText());
                Modelo.setTelefono(Vista.txtTelefono.getText());
                Modelo.setCorreo(Vista.txtCorreo.getText());
                Modelo.setDireccion(Vista.txtDireccion.getText());

                // Ejecutar el método 
                Modelo.Guardar();
                Modelo.Mostrar(Vista.tbProveedores);

                System.out.println("Proveedor guardado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al guardar el proveedor: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == Vista.btnEliminarProveedor) {
            if (Vista.txtCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Modelo.Eliminar(Vista.tbProveedores);
                Modelo.Mostrar(Vista.tbProveedores);
                Modelo.limpiar(Vista);
            }
        }

        if (e.getSource() == Vista.btnActualizarProveedor) {
            if (!validarCampos()) {
                return;
            }

            try {
                // Asignar lo de la vista al modelo al momento de darle clic a actualizar
                Modelo.setCodigo(Vista.txtCodigo.getText());
                Modelo.setRepresentante(Vista.txtRepresentante.getText());
                Modelo.setMarca(Vista.txtMarca.getText());
                Modelo.setTelefono(Vista.txtTelefono.getText());
                Modelo.setCorreo(Vista.txtCorreo.getText());
                Modelo.setDireccion(Vista.txtDireccion.getText());

                // Ejecutar el método    
                Modelo.Actualizar(Vista.tbProveedores);
                Modelo.Mostrar(Vista.tbProveedores);
                Modelo.limpiar(Vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el proveedor: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == Vista.btnLimpiarCamposProveedor) {
            Modelo.limpiar(Vista);
        }

        if (e.getSource() == Vista.tbProveedores) {
            Modelo.cargarDatosTabla(Vista);
        }
    }

    private boolean validarCampos() {
        if (Vista.txtCodigo.getText().isEmpty() || Vista.txtRepresentante.getText().isEmpty() ||
            Vista.txtMarca.getText().isEmpty() || Vista.txtTelefono.getText().isEmpty() ||
            Vista.txtCorreo.getText().isEmpty() || Vista.txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validación del D.U.I.
        String dui = Vista.txtCodigo.getText();
        if (dui.length() != 9 || !dui.matches("\\d+")) {
            JOptionPane.showMessageDialog(Vista, "El codigo debe tener 10 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validación del teléfono (solo números permitidos)
        // Validación del teléfono (aceptando el guion como parte del formato)
        String telefono = Vista.txtTelefono.getText();
        if (!telefono.matches("\\d{4}-\\d{4}")) {  // Formato con guion ####-####
            JOptionPane.showMessageDialog(Vista, "El teléfono debe estar en el formato ####-####", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        return true;
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
        if (e.getSource() == Vista.txtBuscarProveedor) {
            Modelo.Buscar(Vista.tbProveedores, Vista.txtBuscarProveedor);
        }
    }
}