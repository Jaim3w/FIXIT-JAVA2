
package Controlador;

import Modelo.Factura;
import Vistas.frmFacturacion;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ctrlFactura implements MouseListener, KeyListener{
    
    private Factura Modelo;
    private frmFacturacion Vista;
    
    public ctrlFactura(Factura Modelo, frmFacturacion Vista){
        
        this.Modelo = Modelo;
        this.Vista = Vista;
        
        this.Vista.btnGuardar.addActionListener(e -> guardarFac());

         Vista.tbFacturas.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            Modelo.cargarDatosTabla(Vista);
        }
    });
         
        System.out.println("Controlador creado");
        
        
        Vista.btnGuardar.addMouseListener(this);
        Vista.btnActualizar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        Vista.btnLimpiar.addMouseListener(this);
        Vista.tbFacturas.addMouseListener(this);
        
        Modelo.Mostrar(Vista.tbFacturas);
        
    }
    
    private void guardarFac() {
        // Lógica para guardar el proveedor
        System.out.println("Guardar fac");
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == Vista.btnGuardar) {
            if (Vista.txtcliente.getText().isEmpty() || Vista.txtFechaFactura.getText().isEmpty() 
                    || Vista.txtFechaVencimiento.getText().isEmpty())
                     {
                JOptionPane.showMessageDialog(Vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                System.out.println("Guardando proveedor...");

                    //Asignar lo de la vista al modelo
                    Modelo.setFacturaIdentificacion(Vista.txtcliente.getText());
                    Modelo.setFechaEmision(Vista.txtFechaFactura.getText());
                    Modelo.setFechaVencimiento(Vista.txtFechaVencimiento.getText());
                   

                    //Ejecutar el metodo 
                    Modelo.Guardar();
                    Modelo.Mostrar(Vista.tbFacturas);
                    
                    
                System.out.println("Proveedor guardado correctamente.");

                    
                    
                    //Modelo.limpiar(Vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Vista, "Dato ingresado erroneo", "Error", JOptionPane.WARNING_MESSAGE);
                    System.out.println("Error al guardar la factura: " + ex.getMessage());

                }
                
            }
        }
        
        
        if (e.getSource() == Vista.btnEliminar) {
            if (Vista.txtcliente.getText().isEmpty() || Vista.txtFechaFactura.getText().isEmpty() 
                    || Vista.txtFechaVencimiento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Modelo.Eliminar(Vista.tbFacturas);
                Modelo.Mostrar(Vista.tbFacturas);
                Modelo.limpiar(Vista);
            }
        }
        
        if (e.getSource() == Vista.btnActualizar) {
            if (Vista.txtcliente.getText().isEmpty() || Vista.txtFechaFactura.getText().isEmpty() 
                    || Vista.txtFechaVencimiento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    Modelo.setFacturaIdentificacion(Vista.txtcliente.getText());
                    Modelo.setFechaEmision(Vista.txtFechaFactura.getText());
                    Modelo.setFechaVencimiento(Vista.txtFechaVencimiento.getText());

                    //Ejecutar el método    
                    Modelo.Actualizar(Vista.tbFacturas);
                    Modelo.Mostrar(Vista.tbFacturas);
                    Modelo.limpiar(Vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
         if (e.getSource() == Vista.btnLimpiar) {
            Modelo.limpiar(Vista);
        }

        if (e.getSource() == Vista.tbFacturas) {
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
