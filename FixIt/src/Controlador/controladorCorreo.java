


package Controlador;

import Modelo.Credenciales;
import Modelo.EnviarCorreo;
import Modelo.Usuarios;
import Vistas.EnviarcorreoE;
import Vistas.NuevaContra;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class controladorCorreo implements MouseListener {
    
    static Random random=new Random();
    static  int numeroAle=1000 + random.nextInt(9000);
    public static Usuarios Verificar=new Usuarios();

    private Credenciales modelo;
    private EnviarcorreoE vista;
  

    public controladorCorreo(Credenciales modelo, EnviarcorreoE vista) {
        this.modelo = modelo;
        this.vista = vista;

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
            System.out.println("btnenviar clickeado");
            
            
           

            String recipient = vista.txtCorreo.getText();
            String subject = "Recuperación de contraseña";
            String content = "Este es el código de verificación: " + numeroAle;

            // Enviar correo

        if (e.getSource()== vista.btnenviar) {
            
            Verificar.setCorreoElectronico(vista.txtCorreo.getText());
         
          
          

            EnviarCorreo.enviarCorreo(recipient, subject, content);
            System.out.println("Correo enviado a: " + recipient);

           Vistas.CodigoVeri.initCodigoVeri();
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
