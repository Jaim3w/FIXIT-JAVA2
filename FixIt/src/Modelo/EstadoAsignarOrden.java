
package Modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JComboBox;

public class EstadoAsignarOrden {
    private String UUID_estado;
    private String Nombre;

    public String getUUID_estado() {
        return UUID_estado;
    }

    public void setUUID_estado(String UUID_estado) {
        this.UUID_estado = UUID_estado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public EstadoAsignarOrden(){
        
    }
    
    public EstadoAsignarOrden(String uuid, String nombre)
    {
        this.UUID_estado = uuid;
        this.Nombre = nombre;
    }
    
      @Override
    public String toString()
    {
        return Nombre;
    }
    
    public void CargarComboEstadoAsignarOrden(JComboBox comboBox){    
        Connection conexion = Conexion.getConexion();
        comboBox.removeAllItems();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from EstadoAsignarOrden");
            while (rs.next()) {
                String uuid = rs.getString("UUID_estado");
                String nombre = rs.getString("Nombre");
                comboBox.addItem(new EstadoAsignarOrden(uuid,nombre));  
                
                if (comboBox.getItemCount() > 0) {
            comboBox.setSelectedIndex(0); // Selecciona el primer ítem
        }
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();  
        }
    }
}
