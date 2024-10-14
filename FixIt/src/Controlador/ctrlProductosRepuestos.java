package Controlador;

import Modelo.Categorias;
import Modelo.mdlProductosRepuestos;
import Vistas.frmProductosRepuestos;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Kevin
 */
public class ctrlProductosRepuestos implements MouseListener, KeyListener {
    
    private mdlProductosRepuestos Modelo;
    private frmProductosRepuestos Vista;
    private Categorias mCategorias;
    
    public ctrlProductosRepuestos (mdlProductosRepuestos Modelo, frmProductosRepuestos Vista, Categorias mCategorias) {
    this.Modelo = Modelo;
    this.Vista = Vista;
    this.mCategorias = mCategorias;
        
    Vista.btnAgregarR.addMouseListener(this);
    Vista.btnEliminarR.addMouseListener(this);
    Vista.btnActualizarR.addMouseListener(this);
    Vista.btnBuscarR.addMouseListener(this);
    Vista.btnLimpiarr.addMouseListener(this);
    Vista.tbListaProductosRepuestos.addMouseListener(this);
    
    //carga el contenido de los combo box
        this.mCategorias.CargarComboCategorias(Vista.cmbCategoria);
        
        Vista.cmbCategoria.addActionListener(e -> {
            if (e.getSource() == Vista.cmbCategoria) {
                System.out.println("ComboBox seleccionado");
                Categorias selectedItem = (Categorias) Vista.cmbCategoria.getSelectedItem();
                if (selectedItem != null) {
                    String dui = selectedItem.getUUID_item();
                    mCategorias.setUUID_item(dui);
                    System.out.println("Categoria seleccionada: " + dui);
                } else {
                    System.out.println("No se seleccionó ningúna Categoria");
                }
            }
        });
        
        Modelo.Mostrar(Vista.tbListaProductosRepuestos);
        Modelo.limpiar(Vista);
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
