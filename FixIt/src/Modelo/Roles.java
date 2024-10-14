
package Modelo;

import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Roles {
    private String UUID_rol;
    private  String Nombre;

    public String getUUID_rol() {
        return UUID_rol;
    }

    public void setUUID_rol(String UUID_rol) {
        this.UUID_rol = UUID_rol;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public Roles(){
    
    }
    
    public Roles(String uuid,String nombre){
      this.UUID_rol=uuid;
      this.Nombre=nombre;
    }
   
    @Override
    public String toString()
    {
      return Nombre;
    }
    
    
   public void CargarComboRoles(JComboBox comboBox){
    Connection conexion = Conexion.getConexion();
    comboBox.removeAllItems(); // Limpiar los ítems anteriores
    
    try {
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery("select * from rol");
        
        // Cargar los elementos en el comboBox
        while (rs.next()) {
            String uuid = rs.getString("UUID_rol");
            String nombre = rs.getString("Nombre");
            comboBox.addItem(new Roles(uuid, nombre));
        }
        
        // Seleccionar el primer ítem si existe
        if (comboBox.getItemCount() > 0) {
            comboBox.setSelectedIndex(0); // Selecciona el primer ítem
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
