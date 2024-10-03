
package Controlador;

import Modelo.Usuarios;
import Vistas.Loginjava;
import Vistas.NuevaContra;
import Vistas.dashboard;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author rdlfp
 */
public class controladorContra implements MouseListener {

    Usuarios modelo;
    NuevaContra vista;
    

    public controladorContra(Usuarios Modelo,NuevaContra Vistas) {
        this.modelo=Modelo;
        this.vista=Vistas;
        
        Vistas.btnActua.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnActua) {
            if (vista.txtNueva.getText().length() <= 6) {
                JOptionPane.showMessageDialog(vista, "La contraseña debe de tener 6 o mas carcarteres");
            } else {
                modelo.setContrasena(vista.txtNueva.getText());
                modelo.ActualizarContrasena(modelo);
                JOptionPane.showMessageDialog(vista, "Contraseña actualizada");
                 Vistas.Loginjava.initLogin();
                 vista.dispose();

            }

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
