
package Modelo;

import Vistas.frmFacturacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Factura {
    
    private String FacturaIdentificacion;
    private String FechaEmision;
    private String FechaVencimiento;

    public String getFacturaIdentificacion() {
        return FacturaIdentificacion;
    }

    public void setFacturaIdentificacion(String FacturaIdentificacion) {
        this.FacturaIdentificacion = FacturaIdentificacion;
    }

    public String getFechaEmision() {
        return FechaEmision;
    }

    public void setFechaEmision(String FechaEmision) {
        this.FechaEmision = FechaEmision;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = Conexion.getConexion();
        try {
            
            
            String generatedUUID = UUID.randomUUID().toString();

              //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO Factura(UUID_factura, FacturaIdentificacion, FechaEmision, FechaVencimiento) VALUES (?, ?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
                PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, generatedUUID);
            pstmt.setString(2, getFacturaIdentificacion());
            pstmt.setString(3, getFechaEmision());
            pstmt.setString(4, getFechaVencimiento());
            
            //Ejecutar la consulta
            pstmt.executeUpdate();
            
           System.out.println("Factura guardada con UUID: " + generatedUUID);

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
            Connection conexion = Conexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Cliente", "Emision", "Vencimiento"});
        try {
            //Consulta a ejecutar
            String query = "SELECT UUID_factura, FacturaIdentificacion, FechaEmision, FechaVencimiento FROM Factura";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_factura"), 
                    rs.getString("FacturaIdentificacion"), 
                    rs.getString("FechaEmision"), 
                    rs.getString("FechaVencimiento")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
            
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
        
          if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

        String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from Factura where UUID_factura = ?";
            PreparedStatement deleteProveedor = conexion.prepareStatement(sql);
            deleteProveedor.setString(1, miUUID);
            deleteProveedor.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = Conexion.getConexion();
        
        try{
         int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
            
            if (getFacturaIdentificacion().isEmpty() || getFechaEmision().isEmpty() || getFechaVencimiento().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
                //Ejecutamos la Query
                String sql = "update Factura set FacturaIdentificacion= ?, FechaEmision = ?, FechaVencimiento = ? where UUID_factura = ?";
                PreparedStatement updateFac = conexion.prepareStatement(sql);

               updateFac.setString(1, getFacturaIdentificacion());
               updateFac.setString(2, getFechaEmision());
               updateFac.setString(3, getFechaVencimiento());
               updateFac.setString(4, miUUID);
               updateFac.executeUpdate();
        }

        //obtenemos que fila seleccionó el usuari

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
       
    } 
    
    
    public void limpiar(frmFacturacion vista) {
        vista.txtcliente.setText("");
        vista.txtFechaFactura.setText("");
        vista.txtFechaVencimiento.setText("");
    }
    
    
    public void cargarDatosTabla(frmFacturacion vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbFacturas.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String ClienteDeTB = vista.tbFacturas.getValueAt(filaSeleccionada, 1).toString();
            String FechaDeTb = vista.tbFacturas.getValueAt(filaSeleccionada, 2).toString();
            String VencimientoDeTB = vista.tbFacturas.getValueAt(filaSeleccionada, 3).toString();

            // Establece los valores en los campos de texto
            vista.txtcliente.setText(ClienteDeTB);
            vista.txtFechaFactura.setText(FechaDeTb);
            vista.txtFechaVencimiento.setText(VencimientoDeTB);
        }
    } 
    
}
