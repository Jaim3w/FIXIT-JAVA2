/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin
 */
public class mdlEmpleados {
    
 private String duiEmpleado;
    private String correoEmpleado;
    private String nombre;
    private String apellido;
    private String imagenEmpleado;
    private String fechaNacimiento;
    private String telefono;

    // Getters y Setters
    public String getDuiEmpleado() {
        return duiEmpleado;
    }

    public void setDuiEmpleado(String duiEmpleado) {
        this.duiEmpleado = duiEmpleado;
    }

    public String getUuidUsuario() {
        return correoEmpleado;
    }

    public void setUuidUsuario(String uuidUsuario) {
        this.correoEmpleado = uuidUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagenEmpleado() {
        return imagenEmpleado;
    }

    public void setImagenEmpleado(String imagenEmpleado) {
        this.imagenEmpleado = imagenEmpleado;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    } 
    
    public void InsertarEmpleado(){
    Connection conexion=Conexion.getConexion();
        try {
            PreparedStatement addEmpleado=conexion.prepareStatement("INSERT INTO Empleado (Dui_empleado, UUID_usuario, Nombre, Apellido, ImagenEmpleado, FechaNacimiento, Telefono) VALUES (?, ?, ?, ?, ?, ?, ?)");
            addEmpleado.setString(1, getDuiEmpleado());
            addEmpleado.setString(2, getUuidUsuario());
            addEmpleado.setString(3, getNombre());
            addEmpleado.setString(4, getApellido());
            addEmpleado.setString(5, getImagenEmpleado());
            addEmpleado.setString(6, getFechaNacimiento());
            addEmpleado.setString(7, getTelefono());
            addEmpleado.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void Mostrar(JTable tabla){
    Connection conexion=Conexion.getConexion();
    
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Dui_empleado","UUID_usuario","Nombre","Apellido","ImagenEmpleado","FechaDeNacimiento","Telefono"});
        try {
            String query="Select * from Empleados";
            Statement statement=conexion.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString(duiEmpleado),
                rs.getString(correoEmpleado),
                rs.getString(nombre),
                rs.getString(apellido),
                rs.getString(imagenEmpleado),
                rs.getString(fechaNacimiento),
                rs.getString(telefono) });  
            }
            
            tabla.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en la funcion mostrar "+ e);
        }
    }
    
}
