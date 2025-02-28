
package Controlador;

import Modelo.mdlDetalleFactura;
import Vistas.frmDetalleFactura;

import Modelo.Factura;
import Modelo.ProductoRep;
import Modelo.mdlAsignarOrden;
import Vistas.frmFacturacion;
import Vistas.frmReporte;
import Vistas.frmReporteFram;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class ctrlDetalleFactura implements MouseListener, KeyListener {
    private mdlDetalleFactura Modelo;
    private frmDetalleFactura Vista;
    private Factura ModeloFac;
    private ProductoRep ModeloProducto;
    private mdlAsignarOrden ModeloOrden;
    
    
    public ctrlDetalleFactura(mdlDetalleFactura Modelo, frmDetalleFactura Vista, Factura Modelo1, ProductoRep Modelo2, mdlAsignarOrden Modelo3){
        
        this.Modelo = Modelo;
        this.Vista = Vista;
        this.ModeloFac = Modelo1;
        this.ModeloProducto = Modelo2;
        this.ModeloOrden = Modelo3;
        
         Vista.tbDetalleFactura.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            Modelo.CargarDatosTabla(Vista);
        }
    });
         
          System.out.println("Controlador creado");
        
        
        Vista.btnAgregar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        Vista.btnCargar.addMouseListener(this);
        Vista.btnNuevaFactura.addMouseListener(this);
        Vista.btnImprimirFac.addMouseListener(this);
        
        Modelo1.CargarComboFactura(Vista.cmbFactura);
        Modelo2.CargarProductosRep(Vista.cmbProductoRepuesto);
        Modelo3.CargarComboAsignaciones(Vista.cmbAsignarOrden);

        
        Modelo.Mostrar(Vista.tbDetalleFactura);
        
        
        Vista.cmbFactura.addActionListener(e -> {
    Object selectedItem = Vista.cmbFactura.getSelectedItem();
            if (selectedItem instanceof Factura) {
                Factura facturaSeleccionada = (Factura) selectedItem;
                ModeloFac.setUUID_factura(facturaSeleccionada.getUUID_factura());
                System.out.println("Factura seleccionada UUID: " + facturaSeleccionada.getUUID_factura());
            } else {
                System.err.println("No se seleccionó ninguna Factura válida.");
            }
        });

  
        Vista.cmbProductoRepuesto.addActionListener(e -> {
            Object selectedItem = Vista.cmbProductoRepuesto.getSelectedItem();
            if (selectedItem instanceof ProductoRep) {
                ProductoRep productoSeleccionado = (ProductoRep) selectedItem;
                ModeloProducto.setUUID_productoRepuesto(productoSeleccionado.getUUID_productoRepuesto());
                System.out.println("Producto seleccionado UUID: " + productoSeleccionado.getUUID_productoRepuesto());
            } else {
                System.err.println("No se seleccionó ningún Producto válido.");
            }
        });

        
        Vista.cmbAsignarOrden.addActionListener(e -> {
            Object selectedItem = Vista.cmbAsignarOrden.getSelectedItem();
            if (selectedItem instanceof mdlAsignarOrden) {
                mdlAsignarOrden asignarOrdenSeleccionada = (mdlAsignarOrden) selectedItem;
                ModeloOrden.setUUID_AsignarOrden(asignarOrdenSeleccionada.getUUID_AsignarOrden());
                System.out.println("Asignación seleccionada UUID: " + asignarOrdenSeleccionada.getUUID_AsignarOrden());
            } else {
                System.err.println("No se seleccionó ninguna Asignación válida.");
            }
        });
        
         // Añadir un MouseListener para capturar el clic en la tabla
        Vista.tbDetalleFactura.addMouseListener(this);
        
}

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == Vista.btnNuevaFactura) {
            frmFacturacion nuevoModeloFrame = new frmFacturacion();
            nuevoModeloFrame.setLocationRelativeTo(null);
            nuevoModeloFrame.setVisible(true);

            nuevoModeloFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // Refrescar la lista de modelos si es necesario
                    ModeloFac.CargarComboFactura(Vista.cmbFactura);
                }
            });
        }
        
        if (e.getSource() == Vista.btnImprimirFac) {
            frmReporteFram nuevoImprimirFacFrame = new frmReporteFram();
            //nuevoImprimirFacFrame.setLocationRelativeTo(null);
            nuevoImprimirFacFrame.setVisible(true);
        }
        
      if (e.getSource() == Vista.btnAgregar) {
    // Verificar si los campos de texto están vacíos
    try {
        System.out.println("Guardando detalle...");

        if(Vista.cmbFactura.getSelectedIndex() != -1 &&
            Vista.cmbProductoRepuesto.getSelectedIndex() != -1 &&
           Vista.cmbAsignarOrden.getSelectedIndex() != -1){
        
        Modelo.setUUID_factura(ModeloFac.getUUID_factura());
        Modelo.setUUID_productoRepuesto(ModeloProducto.getUUID_productoRepuesto());
        Modelo.setUUID_AsignarOrden(ModeloOrden.getUUID_AsignarOrden());

        Modelo.InsertarDetalleFactura();
                Modelo.Mostrar(Vista.tbDetalleFactura);
                
                } else {
                JOptionPane.showMessageDialog(Vista, "Seleccione todos los elementos.", "Error", JOptionPane.WARNING_MESSAGE);
            }
   
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(Vista, "Dato ingresado erróneo", "Error", JOptionPane.WARNING_MESSAGE);
        System.out.println("Error al guardar la asignación: " + ex.getMessage());
    }
}
      
      

if (e.getSource() == Vista.btnEliminar) {
    int filaSeleccionada = Vista.tbDetalleFactura.getSelectedRow(); // Obtiene el índice de la fila seleccionada

    if (filaSeleccionada == -1) { // Si no hay ninguna fila seleccionada
        JOptionPane.showMessageDialog(Vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        // Llama al método para eliminar, pasando la tabla y la fila seleccionada
        Modelo.EliminarDetalleFactura(Vista.tbDetalleFactura);
        // Muestra nuevamente la tabla actualizada
        Modelo.Mostrar(Vista.tbDetalleFactura);
        // Limpia los campos o la vista si es necesario
    }
}

if (e.getSource() == Vista.tbDetalleFactura) {
    Modelo.CargarDatosTabla(Vista);
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
