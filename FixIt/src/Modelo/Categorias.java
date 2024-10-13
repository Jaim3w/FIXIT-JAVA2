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
public class Categorias {
    
    private String UUID_item;
    private String Nombre;
    
    public Categorias(){
    }
    
    public Categorias(String UUID_item, String Nombre) {
        this.UUID_item = UUID_item;
        this.Nombre = Nombre;
    }
    
    public String getUUID_item() {
        return UUID_item;
    }

    public void setUUID_item(String UUID_item) {
        this.UUID_item = UUID_item;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    @Override
    public String toString() {
        return Nombre;
    }
    
    public void CargarComboCategorias(JComboBox comboBox){
        Connection conexion = Conexion.getConexion();
        comboBox.removeAllItems();
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("select UUID_item, Nombre from CategoriaItem");
            while(rs.next()){
                String uuid = rs.getString("UUID_item");
                String nombre = rs.getString("Nombre");
                comboBox.addItem(new Categorias(uuid, nombre));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
