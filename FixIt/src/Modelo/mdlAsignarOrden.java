
package Modelo;

import Vistas.frmAsignarOrden;
import java.sql.*;
import java.util.List;
import java.util.UUID;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class mdlAsignarOrden {
    
   private String UUID_AsignarOrden; 
   private String UUID_Cita;
   private String UUID_Servicio;
   private String UUID_Estado;
   private String Carro_Empleado;
   private String FechaAsignacion;
   private String FechaFinalizacion;
   private String Descripcion;
   private String Servicio;

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }
   
   

    public String getCarro_Empleado() {
        return Carro_Empleado;
    }

    public void setCarro_Empleado(String Carro_Empleado) {
        this.Carro_Empleado = Carro_Empleado;
    }
   
   
    public String getUUID_AsignarOrden() {
        return UUID_AsignarOrden;
    }

    public void setUUID_AsignarOrden(String UUID_AsignarOrden) {
        this.UUID_AsignarOrden = UUID_AsignarOrden;
    }

    public String getUUID_Cita() {
        return UUID_Cita;
    }

    public void setUUID_Cita(String UUID_Cita) {
        this.UUID_Cita = UUID_Cita;
    }

    public String getUUID_Servicio() {
        return UUID_Servicio;
    }

    public void setUUID_Servicio(String UUID_Servicio) {
        this.UUID_Servicio = UUID_Servicio;
    }

    public String getUUID_Estado() {
        return UUID_Estado;
    }

    public void setUUID_Estado(String UUID_Estado) {
        this.UUID_Estado = UUID_Estado;
    }

    public String getFechaAsignacion() {
        return FechaAsignacion;
    }

    public void setFechaAsignacion(String FechaAsignacion) {
        this.FechaAsignacion = FechaAsignacion;
    }

    public String getFechaFinalizacion() {
        return FechaFinalizacion;
    }

    public void setFechaFinalizacion(String FechaFinalizacion) {
        this.FechaFinalizacion = FechaFinalizacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
    public void AgregarAsignacion() {
    // Validaciones de campos obligatorios
    System.out.println("Valor de UUID_Cita: " + getUUID_Cita());

    if (getUUID_Cita() == null) {
        System.out.println("UUID_Cita no puede ser nulo");
        return;
    }
    if (getUUID_Cita().isEmpty()) {
        System.out.println("UUID_Cita no puede estar vacío");
        return;
    }
    if (getUUID_Servicio() == null || getUUID_Servicio().isEmpty()) {
        System.out.println("UUID_Servicio no puede ser nulo o vacío");
        return;
    }
    if (getUUID_Estado() == null || getUUID_Estado().isEmpty()) {
        System.out.println("UUID_Estado no puede ser nulo o vacío");
        return;
    }
    if (getFechaAsignacion() == null || getFechaAsignacion().isEmpty()) {
        System.out.println("FechaAsignacion no puede ser nulo o vacío");
        return;
    }
    if (getFechaFinalizacion() == null || getFechaFinalizacion().isEmpty()) {
        System.out.println("FechaFinalizacion no puede ser nulo o vacío");
        return;
    }
    if (getDescripcion() == null || getDescripcion().isEmpty()) {
        System.out.println("Descripción no puede ser nula o vacía");
        return;
    }

    // Establecer la conexión
    Connection conexion = null;
    PreparedStatement addAsignacion = null;
    try {
        conexion = Conexion.getConexion();
        
        // Preparar el statement para insertar la asignación
        addAsignacion = conexion.prepareStatement(
            "INSERT INTO AsignarOrden(UUID_AsignarOrden, UUID_Cita, UUID_Servicio, UUID_estado, Carro_Empleado, FechaAsignacion, FechaFinalizacion, Descripcion) VALUES(?,?,?,?,?,?,?,?)"
        );

        // Establecer los parámetros
        addAsignacion.setString(1, UUID.randomUUID().toString()); // Generar un UUID para AsignarOrden
        addAsignacion.setString(2, getUUID_Cita()); // UUID de la cita
        addAsignacion.setString(3, getUUID_Servicio()); // UUID del servicio
        addAsignacion.setString(4, getUUID_Estado()); // UUID del estado
        addAsignacion.setString(5, getCarro_Empleado());
        addAsignacion.setString(6, getFechaAsignacion()); // Fecha de asignación
        addAsignacion.setString(7, getFechaFinalizacion()); // Fecha de finalización
        addAsignacion.setString(8, getDescripcion()); // Descripción de la asignación

        // Ejecutar la inserción
        addAsignacion.executeUpdate();
        System.out.println("Asignación agregada con éxito");

    } catch (SQLException ex) {
        System.out.println("Error al agregar la asignación: " + ex.getMessage());
        ex.printStackTrace(); // Imprimir stack trace para más detalles
    } finally {
        // Cerrar el PreparedStatement y liberar recursos
        try {
            if (addAsignacion != null) {
                addAsignacion.close();
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
    model.addColumn("Asignacion para");
    model.addColumn("Servicio a realizar");
    model.addColumn("Estado del servicio");
    model.addColumn("Carro del cliente a ser revisado");    
    model.addColumn("Fecha de asignacion");
    model.addColumn("Fecha de finalizacion");
    model.addColumn("Descripcion");


 String sql ="Select AsignarOrden.UUID_AsignarOrden, Empleado.Nombre AS \"Asignacion para\", Servicio.Nombre AS \"Servicio a realizar\", EstadoAsignarOrden.Nombre As \"Estado del servicio\", AsignarOrden.Carro_Empleado As \"Carro del cliente a ser revisado\",  AsignarOrden.FechaAsignacion AS \"Fecha de asignacion\", AsignarOrden.FechaFinalizacion AS \"Fecha de finalizacion\", AsignarOrden.Descripcion FROM AsignarOrden INNER JOIN Cita ON AsignarOrden.UUID_cita = Cita.UUID_cita      INNER JOIN Servicio ON AsignarOrden.UUID_servicio = Servicio.UUID_servicio  INNER JOIN EstadoAsignarOrden ON AsignarOrden.UUID_estado = EstadoAsignarOrden.UUID_estado  INNER JOIN Empleado ON Cita.Dui_Empleado = Empleado.Dui_Empleado " ;


    try {
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        model.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos
        
        while (rs.next()) {
            Object[] row = new Object[8];
            row[0] = rs.getString("UUID_AsignarOrden");
            row[1] = rs.getString("Asignacion para");
            row[2] = rs.getString("Servicio a realizar");
            row[3] = rs.getString("Estado del servicio");
            row[4] = rs.getString("Carro del cliente a ser revisado");            
            row[5] = rs.getString("Fecha de asignacion");
            row[6] = rs.getString("Fecha de finalizacion");
            row[7] = rs.getString("Descripcion");
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
  
  public void EliminarAsignacion(JTable tabla){
    Connection conexion=Conexion.getConexion();
    
      int filaSeleccionada = tabla.getSelectedRow();
      
       if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
      
       String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            String sql = "DELETE FROM AsignarOrden WHERE UUID_AsignarOrden = ?";
            PreparedStatement eliminarAsignacion = conexion.prepareStatement(sql);
            eliminarAsignacion.setString(1,miUUID);
            
            eliminarAsignacion.executeUpdate();
            
            System.out.println("cita Eliminada Correctamente.");
            
            Mostrar(tabla);
            
        } catch (SQLException e) {
            System.out.println("error al eliminar Cita" + e);
        } 
      
      }
    
     
    
    //funcion actualizar
   public void ActualizarAsignacion(JTable tabla) {
    Connection conexion = Conexion.getConexion();
    
      try{
         int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
            
            if (getFechaAsignacion().isEmpty() || getFechaFinalizacion().isEmpty()  || getDescripcion().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
                //Ejecutamos la Query
               String sql = "UPDATE AsignarOrden SET FechaAsignacion = ?, FechaFinalizacion = ?, Descripcion = ? WHERE UUID_AsignarOrden = ?";                PreparedStatement updateFac = conexion.prepareStatement(sql);
            PreparedStatement actualizarAsignacion = conexion.prepareStatement(sql);
            
               actualizarAsignacion.setString(1, getFechaAsignacion());
               actualizarAsignacion.setString(2, getFechaFinalizacion());
               actualizarAsignacion.setString(3, getDescripcion());
               actualizarAsignacion.setString(4, miUUID);
               actualizarAsignacion.executeUpdate();
        }

        //obtenemos que fila seleccionó el usuari

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
           
   }
   
   public void cargarDatosTabla(frmAsignarOrden vista) {
    // Obtén la fila seleccionada 
    int filaSeleccionada = vista.tbAsignaciones.getSelectedRow();

    // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
    if (filaSeleccionada != -1) {
        // Accede a los valores de las celdas y verifica su tipo
        Object citaObj = vista.tbAsignaciones.getValueAt(filaSeleccionada, 1);
        Object servicioObj = vista.tbAsignaciones.getValueAt(filaSeleccionada, 2);
        Object estadoObj = vista.tbAsignaciones.getValueAt(filaSeleccionada, 3);
        Object clienteObj = vista.tbAsignaciones.getValueAt(filaSeleccionada, 4);        
        Object fechaAsignacionObj = vista.tbAsignaciones.getValueAt(filaSeleccionada, 5);
        Object fechaFinalizacionObj = vista.tbAsignaciones.getValueAt(filaSeleccionada, 6);
        Object descripcionObj = vista.tbAsignaciones.getValueAt(filaSeleccionada, 7);

        //convertir a String solo si el objeto no es null
        String CitaDeTb = (citaObj != null) ? citaObj.toString() : "";
        String ServicioDeTb = (servicioObj != null) ? servicioObj.toString() : "";
        String EstadoDeTb = (estadoObj != null) ? estadoObj.toString() : "";
        String ClienteDeTb = (clienteObj != null) ? clienteObj.toString() : "";        
        String FechaAsignacionDeTb = (fechaAsignacionObj != null) ? fechaAsignacionObj.toString() : "";
        String FechaFinalizacionDeTb = (fechaFinalizacionObj != null) ? fechaFinalizacionObj.toString() : "";
        String DescripcionDeTb = (descripcionObj != null) ? descripcionObj.toString() : "";

        // Establece los valores en los campos de texto
        vista.txtCliente.setText(ClienteDeTb);
        vista.txtFechaAsignacion.setText(FechaAsignacionDeTb);
        vista.txtFechaFinalizacion.setText(FechaFinalizacionDeTb);
        vista.txtDescripcion.setText(DescripcionDeTb);
        vista.cmbCita.setSelectedIndex(filaSeleccionada);
        vista.cmbServicio.setSelectedIndex(filaSeleccionada);
        vista.cmbEstado.setSelectedIndex(filaSeleccionada);
      
        // Selecciona el item en el combo box de citas
        for (int i = 0; i < vista.cmbCita.getItemCount(); i++) {
            if (vista.cmbCita.getItemAt(i).toString().contains(CitaDeTb)) {
                vista.cmbCita.setSelectedIndex(i);
                break;
            }
        }

        // Asignar el valor del servicio al combo box de servicios
        for (int i = 0; i < vista.cmbServicio.getItemCount(); i++) {
            if (vista.cmbServicio.getItemAt(i).toString().contains(ServicioDeTb)) {
                vista.cmbServicio.setSelectedIndex(i);
                break;
            }
        }
        
        // Asignar el estado al combo box de estados
        for (int i = 0; i < vista.cmbEstado.getItemCount(); i++) {
            if (vista.cmbEstado.getItemAt(i).toString().contains(EstadoDeTb)) {
                vista.cmbEstado.setSelectedIndex(i);
                break;
            }
        }
    } else {
        System.out.println("No se ha seleccionado ninguna fila.");
    }
}

  
  public void Buscar(JTable tabla, JTextField miTextField) {
    // Creamos una variable igual a ejecutar el método de la clase de conexión
    Connection conexion = Conexion.getConexion();

    // Definimos el modelo de la tabla
    DefaultTableModel model = new DefaultTableModel();
    
    model.setColumnIdentifiers(new Object[]{
        "UUID_AsignarOrden",
        "Asignacion para",
        "Servicio a realizar",
        "Estado del servicio",
        "Carro del cliente a ser revisado",
        "Fecha de asignacion",
        "Fecha de finalizacion",
        "Descripcion"
    });

    try {
        // Definimos la consulta SQL con un JOIN
        String sql = "SELECT AsignarOrden.UUID_AsignarOrden, Empleado.Nombre AS \"Asignacion para\", Servicio.Nombre AS \"Servicio a realizar\", EstadoAsignarOrden.Nombre AS \"Estado del servicio\", AsignarOrden.Carro_Empleado AS \"Carro del cliente a ser revisado\", AsignarOrden.FechaAsignacion AS \"Fecha de asignacion\", AsignarOrden.FechaFinalizacion AS \"Fecha de finalizacion\", AsignarOrden.Descripcion FROM AsignarOrden INNER JOIN Cita ON AsignarOrden.UUID_cita = Cita.UUID_cita INNER JOIN Servicio ON AsignarOrden.UUID_servicio = Servicio.UUID_servicio INNER JOIN EstadoAsignarOrden ON AsignarOrden.UUID_estado = EstadoAsignarOrden.UUID_estado INNER JOIN Empleado ON Cita.Dui_Empleado = Empleado.Dui_Empleado WHERE Empleado.Nombre LIKE ? OR Servicio.Nombre LIKE ? OR AsignarOrden.Descripcion LIKE ? ORDER BY AsignarOrden.FechaAsignacion DESC";

        PreparedStatement pstmt = conexion.prepareStatement(sql);
        String busqueda = miTextField.getText() + "%"; // Agregar el comodín % aquí
        pstmt.setString(1, busqueda);
        pstmt.setString(2, busqueda);
        pstmt.setString(3, busqueda);
        
        ResultSet rs = pstmt.executeQuery();
        
        model.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos

        while (rs.next()) {
            // Llenamos el modelo por cada vez que recorremos el ResultSet
            model.addRow(new Object[]{
                rs.getString("UUID_AsignarOrden"),
                rs.getString("Asignacion para"),
                rs.getString("Servicio a realizar"),
                rs.getString("Estado del servicio"),
                rs.getString("Carro del cliente a ser revisado"),
                rs.getString("Fecha de asignacion"),
                rs.getString("Fecha de finalizacion"),
                rs.getString("Descripcion")
            });
        }

        // Asignamos el nuevo modelo lleno a la tabla
        tabla.setModel(model);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setWidth(0);

        // Cierra ResultSet y PreparedStatement
        rs.close();
        pstmt.close();
        
    } catch (SQLException e) {
        System.out.println("Este es el error en el modelo, método de buscar: " + e);
    }
}

     public void limpiar(frmAsignarOrden vista) {
        vista.txtFechaAsignacion.setText("");
        vista.txtFechaFinalizacion.setText("");
        vista.txtDescripcion.setText("");
        vista.txtCliente.setText("");
    }


      public void CargarComboAsignaciones(JComboBox comboBox){    
        Connection conexion = Conexion.getConexion();
        comboBox.removeAllItems();
        try{
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("select AsignarOrden.UUID_asignarOrden, Servicio.Nombre, AsignarOrden.Carro_Empleado from AsignarOrden Inner Join Servicio on AsignarOrden.UUID_servicio = Servicio.UUID_servicio");
            while (rs.next()) {
                String uuid = rs.getString("UUID_AsignarOrden");
                String servicio = rs.getString("Nombre");
                String cliente = rs.getString("Carro_Empleado");

            System.out.println("UUID: " + uuid + ", Nombre: " + servicio + ", Fecha: " + cliente);

                 mdlAsignarOrden asignar = new mdlAsignarOrden(uuid, servicio,cliente);

                comboBox.addItem(asignar);       
            }
             // Verifica si hay elementos en el JComboBox y selecciona el primero
        if (comboBox.getItemCount() > 0) {
            comboBox.setSelectedIndex(0); // Selecciona el primer ítem
        }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();  
        }
    }
    

    public mdlAsignarOrden(String uuid, String servicio, String cliente)
    {
        this.UUID_AsignarOrden = uuid;
        this.Servicio = servicio;
        this.Carro_Empleado = cliente;
    }
    
    public mdlAsignarOrden() {

    }
    
      @Override
    public String toString()
    {
        return Servicio + " - " +  Carro_Empleado; // Concatenacion campos con un separador
    }
} 

