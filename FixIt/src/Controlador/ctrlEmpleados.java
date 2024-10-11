package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import Modelo.mdlEmpleados;
import Modelo.Usuarios;
import Vistas.frmUsuarios; 
/**
 *
 * @author Kevin
 */
public class ctrlEmpleados implements MouseListener, KeyListener {
    private mdlEmpleados Modelo;
    private frmUsuarios Vista;
    private Usuarios mUsuarios;
    
    public ctrlEmpleados(mdlEmpleados Modelo, frmUsuarios Vista, Usuarios mUsuarios){
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.mUsuarios = mUsuarios;
        
        Vista.btnGuardar.addMouseListener(this);
        Vista.btnSubirImagen.addMouseListener(this);
        Vista.dtgempleado.addMouseListener(this);
        
        this.mUsuarios.CargarComboEmpleado(Vista.cmbCorreoEmpleado);
        
        Vista.cmbCorreoEmpleado.addActionListener(e -> {
            if (e.getSource() == Vista.cmbCorreoEmpleado) {
                System.out.println("ComboBox seleccionado");
                Usuarios selectedItem = (Usuarios) Vista.cmbCorreoEmpleado.getSelectedItem();
                if (selectedItem != null) {
                    String uuid = selectedItem.getUUID_usuario();
                    mUsuarios.setUUID_usuario(uuid);
                    System.out.println("uuid de usuario seleccionado: " + uuid);
                } else {
                    System.out.println("No se seleccionó ningún usuario");
                }
            }
        });
        
        Modelo.Mostrar(Vista.dtgempleado);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == Vista.dtgempleado) {
            Modelo.cargarDatosTabla(Vista);
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
