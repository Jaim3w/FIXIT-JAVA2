package Modelo;

import javax.swing.JComboBox;
import java.sql.*;

/**
 *
 
@author rdlfp*/
public class Clientes {
    private String Dui_cliente;

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
    private String Nombre;

    public  Clientes(){

    }

   public Clientes(String uuid,String nombre){
   this.Dui_cliente=uuid;
   this.Nombre=nombre;
   }

   @Override
   public String toString(){
    return Nombre;
   }

   //Se cargaran los clinetes

   public void CargarCombo(JComboBox comboBox){
   Connection conexion=Conexion.getConexion();
   comboBox.removeAllItems();
       try {
           Statement statement=conexion.createStatement();
           ResultSet  rs=statement.executeQuery("select * from Cliente");
           
           while(rs.next()){
           String uuid=rs.getString("Dui_cliente");
           String nombre=rs.getString("Nombre");
           comboBox.addItem(new Clientes(uuid,nombre));
           }
            if (comboBox.getItemCount() > 0) {
            comboBox.setSelectedIndex(0); // Selecciona el primer ítem
        }
            
       } catch (SQLException ex) {
           ex.printStackTrace();
       } 

   }

    public void CargarComboClientes(JComboBox<String> cmbClienteCarro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}