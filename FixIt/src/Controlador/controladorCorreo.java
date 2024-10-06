package Controlador;

import Modelo.Credenciales;
import Modelo.EnviarCorreo;
import Modelo.Usuarios;
import Vistas.EnviarcorreoE;
import Vistas.NuevaContra;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JOptionPane;

public class controladorCorreo implements MouseListener {

    static Random random = new Random();
    static int numeroAle = 1000 + random.nextInt(9000);

    private Credenciales modelo;
    private EnviarcorreoE vista;
    private Usuarios modelito;
    public static String correoGlobal;

    public controladorCorreo(Credenciales modelo, EnviarcorreoE vista, Usuarios Modelito) {
        this.modelo = modelo;
        this.vista = vista;
        this.modelito = Modelito;

        if (vista.btnenviar == null) {
            System.out.println("btnenviar es null");
        } else {
            vista.btnenviar.addMouseListener(this);
            System.out.println("MouseListener agregado a btnenviar");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnenviar) {

            modelito.setCorreoElectronico(vista.txtCorreo.getText());
            correoGlobal = vista.txtCorreo.getText();
            String recipient = vista.txtCorreo.getText();
            String subject = "Recuperación de contraseña";
            String content = "Este es el código de verificación: " + numeroAle;
            EnviarCorreo.enviarCorreo(recipient, subject, content);
            System.out.println("Correo enviado a: " + recipient);
            boolean comprobar = modelito.ValidarCorreo();
            System.out.println("Resultado del correo: " + comprobar);
            if (comprobar) {
                JOptionPane.showMessageDialog(vista, "Correo enviado", "Correo Enviado", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("btnenviar clickeado");

                System.err.println("esto es lo que le mando al setCorreo" + vista.txtCorreo.getText());
                Vistas.CodigoVeri.initCodigoVeri();
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "credencial incorecta", "Error", JOptionPane.WARNING_MESSAGE);
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
