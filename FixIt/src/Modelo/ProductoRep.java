
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;


public class ProductoRep {
    
    private String UUID_productoRepuesto;
    private String Nombre;

    public String getUUID_productoRepuesto() {
        return UUID_productoRepuesto;
    }

    public void setUUID_productoRepuesto(String UUID_productoRepuesto) {
        this.UUID_productoRepuesto = UUID_productoRepuesto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public ProductoRep(){
        
    }
    
    public ProductoRep(String uuid, String nombre)
    {
        this.UUID_productoRepuesto = uuid;
        this.Nombre = nombre;
    }
    
      @Override
    public String toString()
    {
        return Nombre;
    }
    
    
    public void CargarProductosRep(JComboBox comboBox){    
        Connection conexion = Conexion.getConexion();
        comboBox.removeAllItems();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("Select ProductoRepuesto.UUID_productoRepuesto, ProductoRepuesto.Nombre from ProductoRepuesto");
            while (rs.next()) {
                String uuid = rs.getString("UUID_productoRepuesto");
                String nombre = rs.getString("Nombre");
                comboBox.addItem(new ProductoRep(uuid,nombre)); 
                
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
