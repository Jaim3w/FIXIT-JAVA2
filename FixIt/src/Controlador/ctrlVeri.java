/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vistas.CodigoVeri;
import Vistas.NuevaContra;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author rdlfp
 */
public class ctrlVeri implements MouseListener{
    private CodigoVeri vista;
    
    public ctrlVeri(CodigoVeri  Vistas){
    this.vista = Vistas;
  
  
    Vistas.btncontinuar.addMouseListener(this);
 
 }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Integer.parseInt(vista.txtCodi.getText() )==controladorCorreo.numeroAle){
            
            NuevaContra.initNuevaContras();
            vista.dispose();
            

        } else {
       JOptionPane.showMessageDialog(vista, "Codigo erroneo", "Recuperación de Contraseña", JOptionPane.WARNING_MESSAGE);

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
