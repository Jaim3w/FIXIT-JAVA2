/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Jaimew
 */
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import Vistas.frmClientes;
import Modelo.mdlClientes;

public class ctrlClientes implements MouseListener, KeyListener{
    
    private mdlClientes Modelo;
    private frmClientes Vista;
    
    
    public ctrlClientes(mdlClientes Modelo, frmClientes Vista) {
        this.Modelo = Modelo;
        this.Vista = Vista;

        this.Vista.btnAgregar.addActionListener(e -> gaurdarCliente());

        System.out.println("Controlador creado");

        Vista.btnAgregar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        Vista.btnActualizar.addMouseListener(this);
        Vista.btnLimpiar.addMouseListener(this);
        Vista.txtBuscar.addKeyListener(this);
        Vista.tbClientes.addMouseListener(this);

        Modelo.Mostrar(Vista.tbClientes);
    }
     private void gaurdarCliente() {
        System.out.println("Guardar Cliente");
    }
    @Override
    public void mouseClicked(MouseEvent e) {
   if (e.getSource() == Vista.btnAgregar) {
            if (!validarCampos()) {
                return;
            }

            try {
                if (Vista.txtContraseña.getText().length() <= 6) {
                JOptionPane.showMessageDialog(null, "Número de caracteres insuficiente, ingrese más de 6 caracteres", "Advertencia", JOptionPane.WARNING_MESSAGE);
                System.out.println("Contraseña demasiado corta");
                return;
            }
                System.out.println("Guardando Cliente...");

                // Asignar lo de la vista al modelo
                Modelo.setDui_Cliente(Vista.txtduicliente.getText());
                Modelo.setNombre(Vista.txtNombreCliente.getText());
                Modelo.setApellido(Vista.txtApellidoCliente.getText());
                Modelo.setContrasena(Vista.txtContraseña.getText());
                Modelo.setCorreo_Electronico(Vista.txtCorreoCliente.getText());
                Modelo.setTelefono(Vista.txtTelefonoCliente.getText());

                // Ejecutar el método 
                Modelo.Guardar();
                Modelo.Mostrar(Vista.tbClientes);

                System.out.println("Cliente guardado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al guardar el cliente: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == Vista.btnEliminar) {
            if (Vista.txtduicliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Modelo.Eliminar(Vista.tbClientes);
                Modelo.Mostrar(Vista.tbClientes);
                Modelo.limpiar(Vista);
            }
        }

        if (e.getSource() == Vista.btnActualizar) {
            if (!validarCampos()) {
                return;
            }

            try {
                // Asignar lo de la vista al modelo al momento de darle clic a actualizar
                Modelo.setDui_Cliente(Vista.txtduicliente.getText());
                Modelo.setNombre(Vista.txtNombreCliente.getText());
                Modelo.setApellido(Vista.txtApellidoCliente.getText());
                Modelo.setContrasena(Vista.txtContraseña.getText());
                Modelo.setCorreo_Electronico(Vista.txtCorreoCliente.getText());
                Modelo.setTelefono(Vista.txtTelefonoCliente.getText());

                // Ejecutar el método    
                Modelo.Actualizar(Vista.tbClientes);
                Modelo.Mostrar(Vista.tbClientes);
                Modelo.limpiar(Vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Vista, "Error al actualizar el cliente: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == Vista.btnLimpiar) {
            Modelo.limpiar(Vista);
        }

        if (e.getSource() == Vista.tbClientes) {
            Modelo.cargarDatosTabla(Vista);
        }
    }

   private boolean validarCampos() {
    if (Vista.txtduicliente.getText().isEmpty() || Vista.txtNombreCliente.getText().isEmpty() ||
        Vista.txtApellidoCliente.getText().isEmpty() || Vista.txtContraseña.getText().isEmpty() ||
        Vista.txtCorreoCliente.getText().isEmpty() || Vista.txtTelefonoCliente.getText().isEmpty()) {
        JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    String telefono = Vista.txtTelefonoCliente.getText();
    String dui = Vista.txtduicliente.getText();

    // Validar que el teléfono solo contenga números y tenga 8 dígitos
    if (!telefono.matches("\\d{8}")) {
        JOptionPane.showMessageDialog(Vista, "El teléfono debe contener exactamente 8 dígitos y solo números", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Validar que el DUI solo contenga números y tenga 9 dígitos (con un guion)
    if (!dui.matches("\\d{8}-\\d")) {
        JOptionPane.showMessageDialog(Vista, "El DUI debe tener el formato 12345678-9", "Error", JOptionPane.ERROR_MESSAGE);
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

     if (e.getSource() == Vista.txtBuscar) {
            Modelo.Buscar(Vista.tbClientes, Vista.txtBuscar);
        }
    }
}
