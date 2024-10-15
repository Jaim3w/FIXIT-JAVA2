package Controlador;

import Modelo.mdlDash;
import Vistas.Loginjava;
import Vistas.dashboardpanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ctrlDash implements MouseListener, KeyListener {
    private mdlDash Modelo;
    private dashboardpanel Vista;

    public ctrlDash(mdlDash modelo, dashboardpanel vista) {
        this.Modelo = modelo;
        this.Vista = vista;
        
        //Vista.imgExit.addMouseListener(true);
        cargarDatos();
    }

    public void cargarDatos() {
        // Cargar el nombre del perfil desde el modelo
        Modelo.cargarNombreEmpleadoPorCorreo();  // Llama al método que obtiene solo el nombre en el modelo

        // Asignar el nombre obtenido al componente de la vista (JLabel)
        Vista.getTxtNombre().setText(Modelo.getNombre());  // Solo asigna el nombre
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.imgExit) {
        // Mostrar el cuadro de diálogo de confirmación
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Estás seguro de que deseas cerrar sesión?",
            "Confirmar cierre de sesión",
            JOptionPane.YES_NO_OPTION
        );

        // Verificar la respuesta del usuario
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Si elige "Sí", redirigir a la pantalla de login
            Loginjava.initLogin();
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
