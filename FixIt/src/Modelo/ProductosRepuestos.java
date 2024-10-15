/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author Kevin
 */
public class ProductosRepuestos {
    
    private String UUID_productoRepuesto;
    private String nombre;
    
    public ProductosRepuestos(){
}
    
    public ProductosRepuestos(String UUID_prodcutosRepuestos, String nombre) {
        
    this.UUID_productoRepuesto = UUID_prodcutosRepuestos;
    this.nombre = nombre;
  
    }
    
    public String getUUID_ProductoRepuesto() {
        return UUID_productoRepuesto;
    }

    public void setUUID_ProductoRepuesto(String UUID_productoRepuesto) {
        this.UUID_productoRepuesto = UUID_productoRepuesto;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    public void CargarComboCategorias(JComboBox comboBox){
        Connection conexion = Conexion.getConexion();
        comboBox.removeAllItems();
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT UUID_productoRepuesto, Nombre FROM ProductoRepuesto");
            while(rs.next()){
                String uuid = rs.getString("UUID_productoRepuesto");
                String nombre = rs.getString("Nombre");
                comboBox.addItem(new ProductosRepuestos(uuid, nombre));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
        
}
