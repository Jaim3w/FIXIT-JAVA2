
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Vistas.frmProveedor;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;
public class mdlProveedor {
    
    private String codigo;
    private String representante;
    private String marca;
    private String telefono;
    private String Correo;
    private String Direccion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
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
            
          String correo = getCorreo();

        // Validar el formato del correo electrónico
        if (!isValidEmail(correo)) {
            
                // mensaje de error
                JOptionPane.showMessageDialog(null, "Debe ingresar un correo electrónico válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }
        
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO Proveedor(Codigo_proveedor, Nombre, Marca, Telefono, Correo_Electronico, Direccion) VALUES (?, ?, ?, ?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
                PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, getCodigo() );
            pstmt.setString(2, getRepresentante());
            pstmt.setString(3, getMarca());
            pstmt.setString(4, getTelefono());
            pstmt.setString(5, correo);
            pstmt.setString(6, getDireccion());
          
            
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
        modelo.setColumnIdentifiers(new Object[]{"Codigo_proveedor", "Nombre", "Marca", "Telefono", "Correo_Electronico", "Direccion"});
        try {
            //Consulta a ejecutar
            String query = "SELECT * FROM Proveedor";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("Codigo_proveedor"), 
                    rs.getString("Nombre"), 
                    rs.getString("Marca"), 
                    rs.getString("Telefono"), 
                    rs.getString("Correo_Electronico"), 
                    rs.getString("Direccion")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            
            //tabla.getColumnModel().getColumn(0).setMinWidth(0);
            //tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            //tabla.getColumnModel().getColumn(0).setWidth(0);
            
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
            String sql = "delete from Proveedor where Codigo_proveedor = ?";
            PreparedStatement deleteProveedor = conexion.prepareStatement(sql);
            deleteProveedor.setString(1, miId);
            deleteProveedor.executeUpdate();
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
                
                 String correo = getCorreo();

        if (!isValidEmail(correo)) {
            
                // mensaje de error
                JOptionPane.showMessageDialog(null, "Debe ingresar un correo electrónico válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }
        
                //Ejecutamos la Query
                String sql = "update Proveedor set Nombre = ?, Marca = ?, Telefono = ?, Correo_Electronico = ?, Direccion = ?  where Codigo_proveedor = ?";
                PreparedStatement updateProveedor = conexion.prepareStatement(sql);

               updateProveedor.setString(1, getRepresentante());
               updateProveedor.setString(2, getMarca());
               updateProveedor.setString(3, getTelefono());
               updateProveedor.setString(4, correo);
               updateProveedor.setString(5, getDireccion());
               updateProveedor.setString(6, miDui);
               updateProveedor.executeUpdate();

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
        modelo.setColumnIdentifiers(new Object[]{"Codigo_proveedor", "Nombre", "Marca", "Telefono", "Correo_Electronico", "Direccion"});
        try {
            String sql = "SELECT * FROM Proveedor WHERE Representante LIKE ? || '%'";
            PreparedStatement deleteProveedor = conexion.prepareStatement(sql);
            deleteProveedor.setString(1, miTextField.getText());
            ResultSet rs = deleteProveedor.executeQuery();

            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("Codigo_proveedor"), rs.getString("Nombre"), rs.getString("Marca"), rs.getString("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Direccion")});
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
     
     public void limpiar(frmProveedor vista) {
        vista.txtCodigo.setText("");
        vista.txtRepresentante.setText("");
        vista.txtMarca.setText("");
        vista.txtTelefono.setText("");
        vista.txtCorreo.setText("");
        vista.txtDireccion.setText("");
        
    }
     
     public void cargarDatosTabla(frmProveedor vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbProveedores.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String CodigoDeTb = vista.tbProveedores.getValueAt(filaSeleccionada, 0).toString();
            String RepresentanteDeTB = vista.tbProveedores.getValueAt(filaSeleccionada, 1).toString();
            String MarcaDeTb = vista.tbProveedores.getValueAt(filaSeleccionada, 2).toString();
            String TelefonoDeTB = vista.tbProveedores.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.tbProveedores.getValueAt(filaSeleccionada, 4).toString();
            String DireccionDeTB = vista.tbProveedores.getValueAt(filaSeleccionada, 5).toString();

            // Establece los valores en los campos de texto
            vista.txtCodigo.setText(CodigoDeTb);
            vista.txtRepresentante.setText(RepresentanteDeTB);
            vista.txtMarca.setText(MarcaDeTb);
            vista.txtTelefono.setText(TelefonoDeTB);
            vista.txtCorreo.setText(CorreoDeTB);
            vista.txtDireccion.setText(DireccionDeTB);
        }
    }  
     
     
}
