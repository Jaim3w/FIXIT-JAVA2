/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuarios;
import Vistas.frmNuevoUsuario;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author caryt
 */
public class ctrlNewUser implements MouseListener {
    Usuarios modelo;
    frmNuevoUsuario Vista;
    
    public ctrlNewUser(Usuarios modelo, frmNuevoUsuario vista) {
        this.modelo = modelo;
        this.Vista = vista;
        
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

            modelo.InsertarUserEmpleado();
            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito", "Usuario registrado", JOptionPane.INFORMATION_MESSAGE);
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
}
