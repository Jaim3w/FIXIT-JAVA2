package Controlador;

import Modelo.Roles;
import Modelo.Usuarios;
import Vistas.Loginjava;
import Vistas.frmRegistrarse;
import Vistas.frmRegistroParte2;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import main.main;
import static main.main.establecerPrimerUso;

public class controlador implements MouseListener {

    Usuarios modelo;
    frmRegistrarse Vista;
    Roles mRoles;

    public controlador(Usuarios modelo, frmRegistrarse vista, Roles mRoles) {
        this.modelo = modelo;
        this.Vista = vista;
        this.mRoles = mRoles;

        Vista.imgFixit.addMouseListener(this);
        Vista.btnAgregarUser.addMouseListener(this);
        this.mRoles.CargarComboRoles(vista.cbComobox);

        // Seleccionar el primer ítem al cargar el combo box, para evitar problemas de null
        if (vista.cbComobox.getItemCount() > 0) {
            vista.cbComobox.setSelectedIndex(0);
        }

        vista.cbComobox.addActionListener(e -> {
            if (e.getSource() == vista.cbComobox) {
                System.out.println("ComboBox seleccionado");
                Roles selectedItem = (Roles) vista.cbComobox.getSelectedItem();
                if (selectedItem != null) {
                    String UUID = selectedItem.getUUID_rol();
                    mRoles.setUUID_rol(UUID);
                    System.out.println("Rol seleccionado UUID: " + UUID);
                } else {
                    System.out.println("No se seleccionó ningún rol");
                }
            }
        });
    }

    
    
    private void btnAgregarUserActionPerformed(ActionEvent evt) {
    // Simular la lógica de registro
    boolean registroExitoso = true; // Cambia esto según la lógica real

    if (registroExitoso) {
        main.establecerPrimerUso(false); // Cambiar a no primer uso
        System.out.println("Registro completado. Cambiado primerUso a 'false'.");
        Vista.dispose(); // Cerrar el formulario de registro
        Loginjava.initLogin(); // Mostrar pantalla de login
    } else {
        System.out.println("Error al registrar el usuario.");
    }

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnAgregarUser) {
            System.out.println("Botón 'Agregar Usuario' clickeado");

            if (Vista.txtCorreoUser.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene los campos vacíos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!Vista.txtCorreoUser.getText().contains("@") || !Vista.txtCorreoUser.getText().contains(".com")) {
                JOptionPane.showMessageDialog(null, "Formato de correo inválido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (Vista.txtContra.getPassword().length <= 6) {
                JOptionPane.showMessageDialog(null, "Número de caracteres insuficiente, ingrese más de 6 caracteres", "Advertencia", JOptionPane.WARNING_MESSAGE);
                System.out.println("Contraseña demasiado corta");
                return;
            }

            // Forzar la obtención del rol seleccionado, incluso si no ha habido interacción con el combo box
            Roles selectedItem = (Roles) Vista.cbComobox.getSelectedItem();
            if (selectedItem != null) {
                mRoles.setUUID_rol(selectedItem.getUUID_rol());
                modelo.setUUID_rol(mRoles.getUUID_rol());
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un rol válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            modelo.setCorreoElectronico(Vista.txtCorreoUser.getText());
            modelo.setContrasena(Vista.txtContra.getText());

            modelo.InsertarUser();
            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito", "Usuario registrado", JOptionPane.INFORMATION_MESSAGE);
            establecerPrimerUso(false);

            // Abrir la ventana de inicio de sesión
            try {
                frmRegistroParte2.initfrmRegistroParte2();
                Vista.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al abrir la ventana de inicio de sesión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == Vista.imgFixit) {
            Loginjava.initLogin();
            Vista.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}


