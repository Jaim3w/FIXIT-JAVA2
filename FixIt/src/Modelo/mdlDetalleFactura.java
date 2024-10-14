
package Modelo;

import Vistas.frmDetalleFactura;
import java.sql.*;
import java.util.UUID;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class mdlDetalleFactura {
    
   private String UUID_DetalleFactura; 
   private String UUID_factura;
   private String UUID_productoRepuesto;
   private String UUID_AsignarOrden;

    public String getUUID_DetalleFactura() {
        return UUID_DetalleFactura;
    }

    public void setUUID_DetalleFactura(String UUID_DetalleFactura) {
        this.UUID_DetalleFactura = UUID_DetalleFactura;
    }

    public String getUUID_factura() {
        return UUID_factura;
    }

    public void setUUID_factura(String UUID_factura) {
        this.UUID_factura = UUID_factura;
    }

    public String getUUID_productoRepuesto() {
        return UUID_productoRepuesto;
    }

    public void setUUID_productoRepuesto(String UUID_productoRepuesto) {
        this.UUID_productoRepuesto = UUID_productoRepuesto;
    }

    public String getUUID_AsignarOrden() {
        return UUID_AsignarOrden;
    }

    public void setUUID_AsignarOrden(String UUID_AsignarOrden) {
        this.UUID_AsignarOrden = UUID_AsignarOrden;
    }
    
   
//    public void InsertarDetalleFactura(){
//    Connection conexion=Conexion.getConexion();
//        try {
//            PreparedStatement addDetalle=conexion.prepareStatement("insert into DetalleFactura(UUID_DetalleFactura,UUID_factura,UUID_productoRepuesto,UUID_AsignarOrden ) values(?,?,?,?)");
//            addDetalle.setString(1, UUID.randomUUID().toString());
//            addDetalle.setString(2, getUUID_factura());
//            addDetalle.setString(3, getUUID_productoRepuesto());
//            addDetalle.setNString(4, getUUID_AsignarOrden());
//
//            addDetalle.execute();
//        } catch (SQLException ex) {
//          System.out.println("este es el error"+ ex);
//        }
//    }
//    
    
        public void InsertarDetalleFactura() {
    // Validaciones de campos obligatorios
    System.out.println("Valor de UUID_Cita: " + getUUID_productoRepuesto());

    if (getUUID_productoRepuesto() == null) {
        System.out.println("UUID_productoRepuesto no puede ser nulo");
        return;
    }
    if (getUUID_productoRepuesto().isEmpty()) {
        System.out.println("UUID_productoRepuesto no puede estar vacío");
        return;
    }
    if (getUUID_factura() == null || getUUID_factura().isEmpty()) {
        System.out.println("UUID_factura no puede ser nulo o vacío");
        return;
    }
    if (getUUID_AsignarOrden() == null || getUUID_AsignarOrden().isEmpty()) {
        System.out.println("UUID_AsignarOrden no puede ser nulo o vacío");
        return;
    }

    // Establecer la conexión
    Connection conexion = null;
    PreparedStatement addDetalle = null;
    try {
        conexion = Conexion.getConexion();
        
        // Preparar el statement para insertar la asignación
        addDetalle = conexion.prepareStatement(
            "insert into DetalleFactura(UUID_DetalleFactura,UUID_factura,UUID_productoRepuesto,UUID_AsignarOrden ) values(?,?,?,?)"
        );

        // Establecer los parámetros
        addDetalle.setString(1, UUID.randomUUID().toString());
            addDetalle.setString(2, getUUID_factura());
            addDetalle.setString(3, getUUID_productoRepuesto());
            addDetalle.setNString(4, getUUID_AsignarOrden());

        // Ejecutar la inserción
        addDetalle.executeUpdate();
        System.out.println("Asignación agregada con éxito");

    } catch (SQLException ex) {
        System.out.println("Error al agregar la asignación: " + ex.getMessage());
        ex.printStackTrace(); // Imprimir stack trace para más detalles
    } finally {
        // Cerrar el PreparedStatement y liberar recursos
        try {
            if (addDetalle != null) {
                addDetalle.close();
            }
            if (conexion != null) {
                conexion.close(); // Asegúrate de cerrar la conexión también
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar los recursos: " + e.getMessage());
        }
    }
}
    
      public void Mostrar(javax.swing.JTable tabla) {
    Connection conexion = Conexion.getConexion(); // Usar la clase de conexión
    DefaultTableModel model = new DefaultTableModel();
    tabla.setModel(model);

    model.addColumn("UUID");
    model.addColumn("Factura de");
    model.addColumn("Producto o repuesto");
    model.addColumn("Servicio");


 String sql = "SELECT DetalleFactura.UUID_detalleFactura, Factura.FacturaIdentificacion AS \"Factura de\", ProductoRepuesto.Nombre AS \"Producto o repuesto\", Servicio.Nombre AS \"Servicio\" FROM DetalleFactura INNER JOIN Factura ON DetalleFactura.UUID_factura = Factura.UUID_factura INNER JOIN ProductoRepuesto ON DetalleFactura.UUID_productoRepuesto = ProductoRepuesto.UUID_productoRepuesto INNER JOIN AsignarOrden ON DetalleFactura.UUID_asignarOrden = AsignarOrden.UUID_asignarOrden INNER JOIN Servicio ON AsignarOrden.UUID_servicio = Servicio.UUID_servicio";


    try {
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        model.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos
        
        while (rs.next()) {
            Object[] row = new Object[6];
            row[0] = rs.getString("UUID_detalleFactura");
            row[1] = rs.getString("Factura de");
            row[2] = rs.getString("Producto o repuesto");
            row[3] = rs.getString("Servicio");
            model.addRow(row);
        }
        
        tabla.setModel(model);
            
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        
        // Cierra ResultSet y Statement
        rs.close();
        st.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
      
      
      public void EliminarDetalleFactura(JTable tabla){
    Connection conexion=Conexion.getConexion();
    
      int filaSelecionada = tabla.getSelectedRow();
      
      if(filaSelecionada != -1){
      
       String miID = tabla.getValueAt(filaSelecionada, 0).toString();
        try {
            String sql = "DELETE FROM DetalleFactura WHERE UUID_detalleFactura = ?";
            PreparedStatement eliminarDetalle = conexion.prepareStatement(sql);
            eliminarDetalle.setString(1,miID);
            
            eliminarDetalle.executeUpdate();
            
            System.out.println("cita Eliminada Correctamente.");
            
            Mostrar(tabla);
            
        } catch (SQLException e) {
            System.out.println("error al eliminar Cita" + e);
        } 
      }
      else System.out.println("no se econtro nada");
      }
      
      
      
      public void CargarDatosTabla(frmDetalleFactura vista) {
    // Obtener la fila seleccionada de la tabla
    int filaSeleccionada = vista.tbDetalleFactura.getSelectedRow();

    if (filaSeleccionada != -1) {
        // Obtener los valores de la fila seleccionada
        Object facturaObj = vista.tbDetalleFactura.getValueAt(filaSeleccionada, 1);
        Object productoObj = vista.tbDetalleFactura.getValueAt(filaSeleccionada, 2);
        Object asignacionObj = vista.tbDetalleFactura.getValueAt(filaSeleccionada, 3);
        
        
        String FacDeTb = (facturaObj != null) ? facturaObj.toString() : "";
        String ProdDeTb = (productoObj != null) ? productoObj.toString() : "";
        String AsigDeTb = (asignacionObj != null) ? asignacionObj.toString() : "";
        
         vista.cmbFactura.setSelectedIndex(filaSeleccionada);
         vista.cmbProductoRepuesto.setSelectedIndex(filaSeleccionada);
         vista.cmbAsignarOrden.setSelectedIndex(filaSeleccionada);


    
      
        // Asignar el valor deL NOMBRE DE FACTURA del cliente al combo box de FACtura
        for (int i = 0; i < vista.cmbFactura.getItemCount(); i++) {
            if (vista.cmbFactura.getItemAt(i).toString().contains(FacDeTb)) {
                vista.cmbFactura.setSelectedIndex(i);
                break;
            }
        }

        // Asignar el valor del uuidProducto del producto al combo box de producto
        for (int i = 0; i < vista.cmbProductoRepuesto.getItemCount(); i++) {
            if (vista.cmbProductoRepuesto.getItemAt(i).toString().contains(ProdDeTb)) {
                vista.cmbProductoRepuesto.setSelectedIndex(i);
                break;
            }
        }
        
        // Asignar los valores del UUID del la asignacioni al combo box de asignaciones 
        for (int i = 0; i < vista.cmbAsignarOrden.getItemCount(); i++) {
            if (vista.cmbAsignarOrden.getItemAt(i).toString().contains(AsigDeTb)) {
                vista.cmbAsignarOrden.setSelectedIndex(i);
                break;
            }
        }
        
    } else {
        System.out.println("No se ha seleccionado ninguna fila.");
    }
    }
   
}
    