package Controlador;

import Modelo.Conexion;
import Modelo.mdlPerfilAd;
import Vistas.frmPerfilAd;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ctrlPerfilAd implements MouseListener, KeyListener {
    private mdlPerfilAd Modelo;
    private frmPerfilAd Vista;

    public ctrlPerfilAd(mdlPerfilAd modelo, frmPerfilAd vista) {
        this.Modelo = modelo;
        this.Vista = vista;
        cargarDatos(); // Llama a cargarDatos cuando se inicializa el controlador
    }

    public void cargarDatos() {
        // Obtener la conexión usando el método estático
        Connection conexion = Conexion.getConexion();

        if (conexion != null) {
            Modelo.cargarDatosPerfil(1); // Ya no es necesario pasar la conexión, asume que se obtiene internamente

            // Asignar los datos obtenidos a los componentes de la vista
            Vista.getTxtNombre().setText(Modelo.getNombre());
            Vista.getTxtApellidos().setText(Modelo.getApellidos());
            Vista.getTxtCorreo().setText(Modelo.getCorreo());
            Vista.getTxtTelefono().setText(Modelo.getTelefono());
            Vista.getTxtNacimiento().setText(Modelo.getNacimiento());
            Vista.getTxtDui().setText(Modelo.getDui());

            // Cerrar la conexión
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
