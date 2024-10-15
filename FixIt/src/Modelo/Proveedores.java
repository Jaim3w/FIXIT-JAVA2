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
public class Proveedores {
    
    private String codigo;
    private String representante;
    
    public Proveedores(){
    }
    
    public Proveedores(String codigo, String representante) {
        this.codigo = codigo;
        this.representante = representante;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodgio(String codigo) {
        this.codigo = codigo;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }
    
    @Override
    public String toString() {
        return representante;
    }
    
    public void CargarComboCategorias(JComboBox comboBox){
        Connection conexion = Conexion.getConexion();
        comboBox.removeAllItems();
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Codigo_proveedor, Nombre FROM Proveedor");
            while(rs.next()){
                String uuid = rs.getString("Codigo_proveedor");
                String nombre = rs.getString("Nombre");
                comboBox.addItem(new Proveedores(uuid, nombre));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
}
