
package Modelo;
import static Modelo.Conexion.getConexion;
import Vistas.frmCitas;
import com.formdev.flatlaf.ui.FlatListCellBorder;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author rdlfp
 */
public class mdlCitas {
    private String UUID_cita;

    public String getUUID_cita() {
        return UUID_cita;
    }

    public void setUUID_cita(String UUID_cita) {
        this.UUID_cita = UUID_cita;
    }

    public String getDui_cliente() {
        return Dui_cliente;
    }

    public void setDui_cliente(String Dui_cliente) {
        this.Dui_cliente = Dui_cliente;
    }

    public String getDui_empleado() {
        return Dui_empleado;
    }

    public void setDui_empleado(String Dui_empleado) {
        this.Dui_empleado = Dui_empleado;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHora_cita() {
        return Hora_cita;
    }

    public void setHora_cita(String Hora_cita) {
        this.Hora_cita = Hora_cita;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    private  String Dui_cliente;
    private String Dui_empleado;
    private String fecha_cita;
    private String Hora_cita;
    private String Descripcion;
    
    
    //Funcion para agregar citas
    
    public void InsertarCitas(){
    Connection conexion=Conexion.getConexion();
        try {
            PreparedStatement addCita=conexion.prepareStatement("insert into Cita(UUID_cita,Dui_cliente,Dui_empleado,Fecha_cita,Hora_cita,Descripcion) values(?,?,?,?,?,?)");
            addCita.setString(1, UUID.randomUUID().toString());
            addCita.setString(2, getDui_cliente());
            addCita.setString(3, getDui_empleado());
            addCita.setNString(4, getFecha_cita());
            addCita.setString(5, getHora_cita());
            addCita.setString(6, getDescripcion());
            addCita.execute();
        } catch (SQLException ex) {
          System.out.println("este es el error"+ ex);
        }
    }
    
    //funcion para mostrar datos
    
  public void Mostrar(javax.swing.JTable tabla) {
    Connection conexion = Conexion.getConexion(); // Usar la clase de conexión
    DefaultTableModel model = new DefaultTableModel();
    tabla.setModel(model);

    model.addColumn("UUID");
    model.addColumn("Cliente");
    model.addColumn("Empleado");
    model.addColumn("Fecha");
    model.addColumn("Hora");
    model.addColumn("Descripción");

 String sql = " SELECT Cita.UUID_cita, Cliente.Nombre AS Cliente, Empleado.Nombre AS Empleado, Cita.Fecha_cita AS Fecha, Cita.Hora_cita AS  Hora, Cita.Descripcion FROM Cita INNER JOIN Cliente ON Cita.Dui_cliente = Cliente.Dui_cliente INNER JOIN Empleado ON Cita.Dui_empleado = Empleado.Dui_empleado";


    try {
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        model.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos
        
        while (rs.next()) {
            Object[] row = new Object[6];
            row[0] = rs.getString("UUID_cita");
            row[1] = rs.getString("Cliente");
            row[2] = rs.getString("Empleado");
            row[3] = rs.getString("Fecha");
            row[4] = rs.getString("Hora");
            row[5] = rs.getString("Descripcion");
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
    
    
    //funcion para eliminar citas
    public void Eliminarcita(JTable tabla){
    Connection conexion=Conexion.getConexion();
    
      int filaSelecionada = tabla.getSelectedRow();
      
      if(filaSelecionada != -1){
      
       String miID = tabla.getValueAt(filaSelecionada, 0).toString();
        try {
            String sql = "DELETE FROM Cita WHERE UUID_cita = ?";
            PreparedStatement eliminarCita = conexion.prepareStatement(sql);
            eliminarCita.setString(1,miID);
            
            eliminarCita.executeUpdate();
            
            System.out.println("cita Eliminada Correctamente.");
            
            Mostrar(tabla);
            
        } catch (SQLException e) {
            System.out.println("error al eliminar Cita" + e);
        } 
      }
      else System.out.println("no se econtro nada");
      }
    
     
    
    //funcion actualizar
   public void ActualizarCitas(JTable tabla) {
    Connection conexion = Conexion.getConexion();

    int filaSeleccionada = tabla.getSelectedRow();

    if (filaSeleccionada != -1) {
        // Obtenemos el id de la fila seleccionada (UUID_CITA)
        String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            String sql = "UPDATE Cita SET Fecha_cita = ?, Hora_cita = ?, Descripcion = ? WHERE UUID_CITA = ?";
            PreparedStatement actualizarCita = conexion.prepareStatement(sql);
            
            // Asignamos los valores de los parámetros
            actualizarCita.setString(1, getFecha_cita());
            actualizarCita.setString(2, getHora_cita());
            actualizarCita.setString(3, getDescripcion());
            actualizarCita.setString(4, miUUId); // Falta asignar el UUID_CITA como cuarto parámetro

            // Ejecutamos la actualización
            actualizarCita.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Este es el error en el método de actualizar: " + e);
        }
    } else {
        System.out.println("No se ha seleccionado ninguna fila.");
    }
}


        
    
    public void limpiar(frmCitas vista) {
        vista.txtHora.setText("");   
        vista.txtFecha.setText("");
        vista.txtDEsc.setText("");

    }
    
    public void CargarDatosTabla(frmCitas vista) {
    // Obtener la fila seleccionada de la tabla
    int filaSeleccionada = vista.tbCitas.getSelectedRow();

    if (filaSeleccionada != -1) {
        // Obtener los valores de la fila seleccionada
        String UUIDTB = vista.tbCitas.getValueAt(filaSeleccionada, 0).toString();
        String DUICLTB = vista.tbCitas.getValueAt(filaSeleccionada, 1).toString();
        String DUIEMTB = vista.tbCitas.getValueAt(filaSeleccionada, 2).toString();
        String FechaTB = vista.tbCitas.getValueAt(filaSeleccionada, 3).toString();
        String HoraTB = vista.tbCitas.getValueAt(filaSeleccionada, 4).toString();
        String DescripcionTB = vista.tbCitas.getValueAt(filaSeleccionada, 5).toString();
        
        // Asignar los valores a los campos de texto
        vista.txtFecha.setText(FechaTB);
        vista.txtHora.setText(HoraTB);
        vista.txtDEsc.setText(DescripcionTB);

        // Asignar el valor del DUI del cliente al combo box de clientes
        for (int i = 0; i < vista.cmbCliente.getItemCount(); i++) {
            if (vista.cmbCliente.getItemAt(i).toString().contains(DUICLTB)) {
                vista.cmbCliente.setSelectedIndex(i);
                break;
            }
        }

        // Asignar el valor del DUI del empleado al combo box de empleados
        for (int i = 0; i < vista.cmbEmpleado.getItemCount(); i++) {
            if (vista.cmbEmpleado.getItemAt(i).toString().contains(DUIEMTB)) {
                vista.cmbEmpleado.setSelectedIndex(i);
                break;
            }
        }
        
    } else {
        System.out.println("No se ha seleccionado ninguna fila.");
    }
    }
}