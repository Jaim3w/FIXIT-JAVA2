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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Vistas.frmClientes;

/**
 *
 * @author Jaimew
 */
public class mdlClientes {
    private String Dui_Cliente;
    private String Nombre;
    private String Apellido;
    private String Contrasena;
    private String Correo_Electronico;
    private String Telefono;

    public String getDui_Cliente() {
        return Dui_Cliente;
    }

    public void setDui_Cliente(String Dui_Cliente) {
        this.Dui_Cliente = Dui_Cliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
     this.Contrasena = Encriptqacion.encriptar(Contrasena);    
    }

    public String getCorreo_Electronico() {
        return Correo_Electronico;
    }

    public void setCorreo_Electronico(String Correo_Electronico) {
        this.Correo_Electronico = Correo_Electronico;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    private boolean isValidEmail(String email) {
    // Expresión regular para validar el formato del email
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
    
}
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = Conexion.getConexion();
        try {
            
          String correo = getCorreo_Electronico();

        // Validar el formato del correo electrónico
        if (!isValidEmail(correo)) {
            
                // mensaje de error
                JOptionPane.showMessageDialog(null, "Debe ingresar un correo electrónico válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }
        
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO Cliente (Dui_Cliente, Nombre, Apellido, Contrasena, Correo_Electronico, Telefono) VALUES (?, ?, ?, ?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
             PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, getDui_Cliente());
            pstmt.setString(2, getNombre());
            pstmt.setString(3, getApellido());
            pstmt.setString(4, getContrasena());
            pstmt.setString(5, correo);
            pstmt.setString(6, getTelefono());
          
            
            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
        
    }
     public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = Conexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Dui_Cliente", "Nombre", "Apellido", "Contrasena", "Correo_Electronico", "Telefono"});
        try {
            //Consulta a ejecutar
            String query = "SELECT * FROM Cliente";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("Dui_Cliente"), 
                    rs.getString("Nombre"), 
                    rs.getString("Apellido"), 
                    rs.getString("Contrasena"), 
                    rs.getString("Correo_Electronico"), 
                    rs.getString("Correo_Electronico")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
        
    }
     public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = Conexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from Cliente where Dui_Cliente = ?";
            PreparedStatement deleteCliente = conexion.prepareStatement(sql);
            deleteCliente.setString(1, miId);
            deleteCliente.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
      public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = Conexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miDui = tabla.getValueAt(filaSeleccionada, 0).toString();
            
            try {
                
                 String correo = getCorreo_Electronico();

        if (!isValidEmail(correo)) {
            
                // mensaje de error
                JOptionPane.showMessageDialog(null, "Debe ingresar un correo electrónico válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }
        
                //Ejecutamos la Query
                String sql = "update Cliente set Nombre = ?, Apellido = ?, Contrasena = ?, Correo_Electronico = ?, Telefono = ?  where Dui_Cliente = ?";
                PreparedStatement updateCliente = conexion.prepareStatement(sql);

               updateCliente.setString(1, getNombre());
               updateCliente.setString(2, getApellido());
               updateCliente.setString(3, getContrasena());
               updateCliente.setString(4, correo);
               updateCliente.setString(5, getTelefono());
               updateCliente.setString(6, miDui);
               updateCliente.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
      public void Buscar(JTable tabla, JTextField miTextField) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = Conexion.getConexion();

        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Dui_Cliente", "Nombre", "Apellido", "Contrasena", "Correo_Electronico", "Telefono"});
        try {
            String sql = "SELECT * FROM Cliente WHERE Nombre LIKE ? || '%'";
            PreparedStatement deleteCliente = conexion.prepareStatement(sql);
            deleteCliente.setString(1, miTextField.getText());
            ResultSet rs = deleteCliente.executeQuery();

            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("Dui_Cliente"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("Contrasena"), rs.getString("Correo_Electronico"), rs.getString("Telefono")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
        public void limpiar(frmClientes vista) {
        vista.txtduicliente.setText("");
        vista.txtNombreCliente.setText("");
        vista.txtApellidoCliente.setText("");
        vista.txtContraseña.setText("");
        vista.txtCorreoCliente.setText("");
        vista.txtTelefonoCliente.setText("");
        
    }
        public void cargarDatosTabla(frmClientes vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbClientes.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String DuiDeTb = vista.tbClientes.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTb = vista.tbClientes.getValueAt(filaSeleccionada, 1).toString();
            String ApellidoDeTb = vista.tbClientes.getValueAt(filaSeleccionada, 2).toString();
            String ContrasenaDeTb= vista.tbClientes.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.tbClientes.getValueAt(filaSeleccionada, 4).toString();
            String TelefonoDeTB = vista.tbClientes.getValueAt(filaSeleccionada, 5).toString();

            // Establece los valores en los campos de texto
            vista.txtduicliente.setText(DuiDeTb);
            vista.txtNombreCliente.setText(NombreDeTb);
            vista.txtApellidoCliente.setText(ApellidoDeTb);
            vista.txtContraseña.setText(ContrasenaDeTb);
            vista.txtCorreoCliente.setText(CorreoDeTB);
            vista.txtTelefonoCliente.setText(TelefonoDeTB);
        }
    }  
}
