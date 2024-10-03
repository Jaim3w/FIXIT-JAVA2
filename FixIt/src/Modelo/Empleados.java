
package Modelo;

import java.sql.*;
import javax.swing.JComboBox;

/**
 *
 * @author rdlfp
 */
public class Empleados {
    private String Dui_empleado;

    public String getDui_empleado() {
        return Dui_empleado;
    }

    public void setDui_empleado(String Dui_empleado) {
        this.Dui_empleado = Dui_empleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    private String Nombre;
    
    public Empleados(){
    
    }
    
    public Empleados(String uuid,String nombre){
    this.Dui_empleado=uuid;
    this.Nombre=nombre;
    }
    
    @Override
    public String toString(){
    return Nombre;
    }
    
    //Se cargan los empleados
    public void Cargarcombo(JComboBox comoBox){
    Connection conexion=Conexion.getConexion();
    comoBox.removeAllItems();
        try {
            Statement statement=conexion.createStatement();
            ResultSet rs=statement.executeQuery("select * from Empleado");
            while(rs.next()){
            String uuid=rs.getString("Dui_empleado");
            String nombre=rs.getString("Nombre");
            comoBox.addItem(new Empleados(uuid,nombre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
}
