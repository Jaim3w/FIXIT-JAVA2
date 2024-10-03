package Controlador;

import Modelo.Roles;
import Modelo.Usuarios;
import Vistas.Loginjava;
import Vistas.frmRegistrarse;
import Vistas.frmRegistroParte2;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.btnAgregarUser) {
            System.out.println("se dion cliccasdfasdf");
            if (Vista.txtCorreoUser.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene los campos vacíos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!Vista.txtCorreoUser.getText().contains("@") || !Vista.txtCorreoUser.getText().contains(".com")) {
                JOptionPane.showMessageDialog(null, "Formato de correo inválido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (Vista.txtContra.getText().length() <= 6) {
                JOptionPane.showMessageDialog(null, "Número de caracteres insuficiente, ingrese más de 6 caracteres", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            modelo.setCorreoElectronico(Vista.txtCorreoUser.getText());
            modelo.setContrasena(Vista.txtContra.getText());
            modelo.setUUID_rol(mRoles.getUUID_rol());

            modelo.InsertarUser();
            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito", "Usuario registrado", JOptionPane.INFORMATION_MESSAGE);

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