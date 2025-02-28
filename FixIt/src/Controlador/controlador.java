package Controlador;

import Modelo.Roles;
import Modelo.Usuarios;
import Vistas.Loginjava;
import Vistas.frmRegistrarse;
import Vistas.frmRegistroParte2;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import main.main;

public class controlador implements MouseListener {

    Usuarios modelo;
    frmRegistrarse Vista;

    public controlador(Usuarios modelo, frmRegistrarse vista) {
        this.modelo = modelo;
        this.Vista = vista;

        Vista.imgFixit.addMouseListener(this);
        Vista.btnAgregarUser.addMouseListener(this);
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

            modelo.setCorreoElectronico(Vista.txtCorreoUser.getText());
            modelo.setContrasena(Vista.txtContra.getText());

            modelo.InsertarUser();
            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito", "Usuario registrado", JOptionPane.INFORMATION_MESSAGE);

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


