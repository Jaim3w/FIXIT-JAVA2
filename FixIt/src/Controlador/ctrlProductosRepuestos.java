/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Categorias;
import Modelo.mdlProductosRepuestos;
import Vistas.frmProductosRepuestos;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.html.ImageView;

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
    Vista.btnLimpiarR.addMouseListener(this);
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
        Modelo.Limpiar(Vista);
    }
  
}
