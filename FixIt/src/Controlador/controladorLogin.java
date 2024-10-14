package Controlador;

import Modelo.Usuarios;
import Modelo.globalVars;
import Vistas.EnviarcorreoE;
import Vistas.Loginjava;
import Vistas.dashboard;
import Vistas.frmRegistrarse;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

public class controladorLogin implements MouseInputListener {
    Usuarios modelo;
    Loginjava vista;

    public controladorLogin(Usuarios modelo, Loginjava vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Debugging statements
        System.out.println("controladorLogin inicializado");

        System.out.println("MouseListener agregado a btnOlvidemicontra");
        
        this.vista.btnLogin.addMouseListener(this);
        this.vista.btnOlvidecontra.addMouseListener(this);
        this.vista.btnIrRegister.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked en Loginjava");

        if (e.getSource() == vista.btnLogin) {
            System.out.println("btnLogin clickeado");

            // Validaciones para btnLogin
            if (vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene los campos vacíos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                System.out.println("Campo de correo vacío");
                return;
            }

            if (!vista.txtCorreo.getText().contains("@") || !vista.txtCorreo.getText().contains(".com")) {
                JOptionPane.showMessageDialog(null, "Formato de correo inválido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                System.out.println("Formato de correo inválido");
                return;
            }

            if (vista.txtContra.getPassword().length <= 6) {
                JOptionPane.showMessageDialog(null, "Número de caracteres insuficiente, ingrese más de 6 caracteres", "Advertencia", JOptionPane.WARNING_MESSAGE);
                System.out.println("Contraseña demasiado corta");
                return;
            }

            modelo.setCorreoElectronico(vista.txtCorreo.getText());
            modelo.setContrasena(new String(vista.txtContra.getPassword()));

            boolean comprobar = modelo.IniciarLogin();
            System.out.println("Resultado del login: " + comprobar);
            
            globalVars.correoUsuario = vista.txtCorreo.getText();

            if (comprobar) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso", "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
                vista.dispose();

                try {
                    dashboard nuevaVentana = new dashboard();
                    nuevaVentana.setVisible(true);
                    System.out.println("Ventana del dashboard abierta");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al abrir la ventana del dashboard: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.WARNING_MESSAGE);
                System.out.println("Credenciales incorrectas");
            }
        }
        
        

        if (e.getSource() == vista.btnOlvidecontra) {
    System.out.println("btnOlvidemicontra clickeado");
 Vistas.EnviarcorreoE.initEnviarCorreoE();
 vista.dispose();
  
}
        
         if (e.getSource() == vista.btnIrRegister){
        
        
            frmRegistrarse.initfrmRegistrarse();
            vista.dispose();
        }
       
                       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed triggered");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased triggered");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered triggered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited triggered");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("mouseDragged triggered");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("mouseMoved triggered");
    }
}