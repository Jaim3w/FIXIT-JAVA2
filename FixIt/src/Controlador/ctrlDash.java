package Controlador;

import Modelo.Conexion;
import Modelo.globalVars;
import Modelo.mdlDash;
import java.sql.Connection;
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
    private Conexion connection;

    public ctrlDash(mdlDash modelo, dashboardpanel vista, Conexion connection) {
        this.Modelo = modelo;
        this.Vista = vista;
        this.connection = connection;
        
        cargarDatos();
        verificarRol();
    }

    public void cargarDatos() {
        // Cargar el nombre del empleado desde la base de datos usando el correo global
        Modelo.cargarNombreEmpleadoPorCorreo();  // Llama al método que obtiene solo el nombre en el modelo
        
        // Cargar el rol desde la base de datos
        Modelo.cargarRol(connection.getConexion(), globalVars.correoUsuario); // Usar la conexión para cargar el rol

        // Asignar el nombre obtenido al componente de la vista (JLabel)
        Vista.getTxtNombre().setText(Modelo.getNombre());  // Solo asigna el nombre
    }
    
    private void verificarRol() {
        // Verifica si el rol es "Empleado" para ocultar ciertos JLabel
        if ("Empleado".equalsIgnoreCase(globalVars.NombreRol)) {
            Vista.lbl_usuarios_y_empleados.setVisible(false);
            Vista.lbl_proveedores.setVisible(false);
            Vista.lbl_asignaciones.setVisible(false);
        } else {
            Vista.lbl_usuarios_y_empleados.setVisible(true);
            Vista.lbl_proveedores.setVisible(true);
            Vista.lbl_asignaciones.setVisible(true);
        }
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
