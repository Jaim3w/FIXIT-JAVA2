package Modelo;

import Vistas.frmCitas;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class mdlCitas {
    private String UUID_cita;
    private String Dui_cliente;
    private String Dui_empleado;
    private String fecha_cita;
    private String Hora_cita;
    private String Descripcion;

    // Getters y Setters

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

    // Inserción de citas
    public void insertarCitas() {
    Connection conexion = null;
    try {
        conexion = Conexion.getConexion();
        PreparedStatement addCita = conexion.prepareStatement(
            "INSERT INTO Cita(UUID_cita, Dui_cliente, Dui_empleado, Fecha_cita, Hora_cita, Descripcion) VALUES(?, ?, ?, ?, ?, ?)"
        );
        addCita.setString(1, UUID.randomUUID().toString());
        addCita.setString(2, getDui_cliente());
        addCita.setString(3, getDui_empleado());
        addCita.setString(4, getFecha_cita());
        addCita.setString(5, getHora_cita());
        addCita.setString(6, getDescripcion());
        addCita.execute();
        addCita.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al insertar la cita: " + ex.getMessage());
    } finally {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

    // Mostrar citas en tabla
    public void mostrarCitas(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        DefaultTableModel model = new DefaultTableModel();
        tabla.setModel(model);

        model.addColumn("UUID");
        model.addColumn("Cliente");
        model.addColumn("Empleado");
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn("Descripción");

        String sql = "SELECT Cita.UUID_cita, Cliente.Nombre AS Cliente, Empleado.Nombre AS Empleado, " +
                     "Cita.Fecha_cita, Cita.Hora_cita, Cita.Descripcion " +
                     "FROM Cita INNER JOIN Cliente ON Cita.Dui_cliente = Cliente.Dui_cliente " +
                     "INNER JOIN Empleado ON Cita.Dui_empleado = Empleado.Dui_empleado";

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            model.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("UUID_cita");
                row[1] = rs.getString("Cliente");
                row[2] = rs.getString("Empleado");
                row[3] = rs.getString("Fecha_cita");
                row[4] = rs.getString("Hora_cita");
                row[5] = rs.getString("Descripcion");
                model.addRow(row);
            }
            tabla.setModel(model);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
            rs.close();
            st.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar cita
    public void eliminarCita(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String miID = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                String sql = "DELETE FROM Cita WHERE UUID_cita = ?";
                PreparedStatement eliminarCita = conexion.prepareStatement(sql);
                eliminarCita.setString(1, miID);
                eliminarCita.executeUpdate();
                eliminarCita.close();
                conexion.close();
                System.out.println("Cita eliminada correctamente.");
                mostrarCitas(tabla); // Refresca la tabla
            } catch (SQLException e) {
                System.out.println("Error al eliminar cita: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }

    // Actualizar cita
    public void actualizarCita(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                String sql = "UPDATE Cita SET Fecha_cita = ?, Hora_cita = ?, Descripcion = ? WHERE UUID_cita = ?";
                PreparedStatement actualizarCita = conexion.prepareStatement(sql);

                actualizarCita.setString(1, getFecha_cita());
                actualizarCita.setString(2, getHora_cita());
                actualizarCita.setString(3, getDescripcion());
                actualizarCita.setString(4, miUUId);

                actualizarCita.executeUpdate();
                actualizarCita.close();
                conexion.close();
                System.out.println("Cita actualizada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar cita: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }
    

    // Método para limpiar los campos
    public void limpiarCampos(frmCitas vista) {
        vista.txtHora.setText("");   
        vista.txtFecha.setText("");
        vista.txtDEsc.setText("");
    }

    // Cargar datos en la vista
    public void cargarDatosEnVista(frmCitas vista) {
        int filaSeleccionada = vista.tbCitas.getSelectedRow();
        if (filaSeleccionada != -1) {
            String fecha = vista.tbCitas.getValueAt(filaSeleccionada, 3).toString();
            String hora = vista.tbCitas.getValueAt(filaSeleccionada, 4).toString();
            String descripcion = vista.tbCitas.getValueAt(filaSeleccionada, 5).toString();

            vista.txtFecha.setText(fecha);
            vista.txtHora.setText(hora);
            vista.txtDEsc.setText(descripcion);

            // Carga de DUI cliente y empleado en los ComboBox
            String duiCliente = vista.tbCitas.getValueAt(filaSeleccionada, 1).toString();
            String duiEmpleado = vista.tbCitas.getValueAt(filaSeleccionada, 2).toString();

            for (int i = 0; i < vista.cmbCliente.getItemCount(); i++) {
                if (vista.cmbCliente.getItemAt(i).toString().contains(duiCliente)) {
                    vista.cmbCliente.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < vista.cmbEmpleado.getItemCount(); i++) {
                if (vista.cmbEmpleado.getItemAt(i).toString().contains(duiEmpleado)) {
                    vista.cmbEmpleado.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }

    // Método para obtener todas las citas
    // Método modificado para obtener todas las citas y actualizar en tiempo real
    public ArrayList<String[]> obtenerCitasCards() {
    ArrayList<String[]> citas = new ArrayList<>();
    Connection conexion = Conexion.getConexion();
    String sql = "SELECT Cita.UUID_cita, Cliente.Nombre AS Cliente, Empleado.Nombre AS Empleado, " +
                 "Cita.Fecha_cita, Cita.Hora_cita, Cita.Descripcion " +
                 "FROM Cita INNER JOIN Cliente ON Cita.Dui_cliente = Cliente.Dui_cliente " +
                 "INNER JOIN Empleado ON Cita.Dui_empleado = Empleado.Dui_empleado " +
                 "ORDER BY Cita.Fecha_cita DESC, Cita.Hora_cita DESC";  // Ordenamos por fecha y hora para mostrar las más recientes

    try {
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] cita = new String[6];
            cita[0] = rs.getString("UUID_cita");
            cita[1] = rs.getString("Cliente");
            cita[2] = rs.getString("Empleado");
            cita[3] = rs.getString("Fecha_cita");
            cita[4] = rs.getString("Hora_cita");
            cita[5] = rs.getString("Descripcion");
            citas.add(cita);
        }
        rs.close();
        st.close();
        conexion.close();
    System.out.println("Citas obtenidas: " + citas.size());
        for (String[] cita : citas) {
            System.out.println("Cita: " + Arrays.toString(cita));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return citas;
}

     // Método para actualizar una cita en la base de datos

    public void actualizarCitacard(String uuidCita) {
    Connection conexion = Conexion.getConexion();
    String sql = "UPDATE Cita SET Fecha_cita = ?, Hora_cita = ?, Descripcion = ? WHERE UUID_cita = ?";

    try {
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, getFecha_cita()); // Usar getter para obtener el valor
        ps.setString(2, getHora_cita()); // Usar getter para obtener el valor
        ps.setString(3, getDescripcion()); // Usar getter para obtener el valor
        ps.setString(4, uuidCita);
        ps.executeUpdate();
        ps.close();
        conexion.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Método para eliminar una cita de la base de datos
    public void eliminarCitacard(String uuidCita) {
    Connection conexion = Conexion.getConexion();
    String sql = "DELETE FROM Cita WHERE UUID_cita = ?";

    try {
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, uuidCita);
        ps.executeUpdate();
        ps.close();
        conexion.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    
}

       