/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
/**
 *
 * @author Jaimew
 */
public class ClientesCarro {
    private String Dui_cliente;
    private String Nombre;

    public String getDui_cliente() {
        return Dui_cliente;
    }

    public void setDui_cliente(String Dui_cliente) {
        this.Dui_cliente = Dui_cliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public ClientesCarro(){
    
    }
    
    public ClientesCarro(String dui,String nombre){
      this.Dui_cliente=dui;
      this.Nombre=nombre;
    }
    
    @Override
    public String toString()
    {
      return Nombre;
    }
    
    public void CargarComboClientes(JComboBox comboBox) {
    Connection conexion = Conexion.getConexion();
    comboBox.removeAllItems();
    try {
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM Cliente");
        while (rs.next()) {
            String dui = rs.getString("Dui_cliente");
            String nombre = rs.getString("Nombre");
            comboBox.addItem(new ClientesCarro(dui, nombre)); 
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
}
