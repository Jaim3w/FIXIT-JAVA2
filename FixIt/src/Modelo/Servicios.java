
package Modelo;
import java.sql.*;
import java.util.UUID;
import javax.swing.JComboBox;


public class Servicios {
    private String UUID_servicio;
    private String Nombre;
    private String Descripcion;
    private Double Precio;

    public String getUUID_servicio() {
        return UUID_servicio;
    }

    public void setUUID_servicio(String UUID_servicio) {
        this.UUID_servicio = UUID_servicio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }
    
    public Servicios(){
        
    }
    
    public Servicios(String uuid, String nombre)
    {
        this.UUID_servicio = uuid;
        this.Nombre = nombre;
    }
    
      @Override
    public String toString()
    {
        return Nombre;
    }
    
    public void CargarComboServicio(JComboBox comboBox){    
        Connection conexion = Conexion.getConexion();
        comboBox.removeAllItems();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from Servicio");
            while (rs.next()) {
                String uuid = rs.getString("UUID_servicio");
                String nombre = rs.getString("Nombre");
                comboBox.addItem(new Servicios(uuid,nombre)); 
                
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
