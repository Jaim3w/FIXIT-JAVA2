/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vistas.frmInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin
 */
public class mdlInventario {
    private String uuid_DetalleProveedor;
    private String uuid_productoRepuesto;
    private String codigo_proveedor;
    private int cantidad;
    private String fechaSuministro;
    
    public String getUUID_DetalleProveedor() {
        return uuid_DetalleProveedor;
    }

    public void setUUID_DetalleProveedor(String uuid_DetalleProveedor) {
        this.uuid_DetalleProveedor = uuid_DetalleProveedor;
    }
    
    public String getUUID_productoRepuesto() {
        return uuid_productoRepuesto;
    }

    public void setUUID_productoRepuesto(String uuid_productoRepuesto) {
        this.uuid_productoRepuesto = uuid_productoRepuesto;
    }
    
    public String getCodigo_proveedor() {
        return codigo_proveedor;
    }

    public void setCodigo_proveedor(String codigo_proveedor) {
        this.codigo_proveedor = codigo_proveedor;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getFechaSuministro() {
        return fechaSuministro;
    }

    public void setFechaSuministro(String fechaSuministro) {
        this.fechaSuministro = fechaSuministro;
    }
    
    public void insertarInventario() {
    Connection conexion = null;
    try {
        conexion = Conexion.getConexion();
        PreparedStatement addInventario = conexion.prepareStatement(
            "INSERT INTO DetalleProveedor(UUID_DetalleProveedor, UUID_productoRepuesto, Codigo_proveedor, Cantidad, FechaSuministro) VALUES(?, ?, ?, ?, ?)"
        );
        addInventario.setString(1, UUID.randomUUID().toString());
        addInventario.setString(2, getUUID_productoRepuesto());
        addInventario.setString(3, getCodigo_proveedor());
        addInventario.setInt(4, getCantidad());
        addInventario.setString(5, getFechaSuministro());
        addInventario.execute();
        addInventario.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al insertar el inventario: " + ex.getMessage());
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
    
    public void mostrarInventario(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        DefaultTableModel model = new DefaultTableModel();
        tabla.setModel(model);

        model.addColumn("UUID_DetalleProveedor");
        model.addColumn("Producto o Repuesto");
        model.addColumn("Proveedor");
        model.addColumn("Cantidad");
        model.addColumn("Fecha de suministro");

        String sql = "SELECT dp.UUID_DetalleProveedor, dp.Cantidad, dp.FechaSuministro, pr.Nombre, p.Nombre " +
                       "FROM DetalleProveedor dp " +
                       "INNER JOIN ProductoRepuesto pr ON dp.UUID_productoRepuesto = pr.UUID_productoRepuesto " +
                       "INNER JOIN Proveedor p ON dp.Codigo_proveedor = p.Codigo_proveedor";

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            model.setRowCount(0); 

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("UUID_DetalleProveedor");
                row[1] = rs.getString("Producto o Repuesto");
                row[2] = rs.getString("Proveedor");
                row[3] = rs.getString("Cantidad");
                row[4] = rs.getString("Fecha de suministro");
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
    
    public void eliminarInventario(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String miID = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                String sql = "DELETE FROM DetalleProveedor WHERE UUID_DetalleProveedor = ?";
                PreparedStatement eliminarCita = conexion.prepareStatement(sql);
                eliminarCita.setString(1, miID);
                eliminarCita.executeUpdate();
                eliminarCita.close();
                conexion.close();
                System.out.println("Inventario eliminada correctamente.");
                mostrarInventario(tabla); // Refresca la tabla
            } catch (SQLException e) {
                System.out.println("Error al eliminar inventario: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }
    
    public void actualizarInventario(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                String sql = "UPDATE DetalleProveedor SET Codigo_proveedor = ?, Cantidad = ?, FechaSuministro = ? WHERE UUID_DetalleProveedor = ?";
                PreparedStatement actualizarInventario = conexion.prepareStatement(sql);

                actualizarInventario.setString(1, getCodigo_proveedor());
                actualizarInventario.setInt(2, getCantidad());
                actualizarInventario.setString(3, getFechaSuministro());
                actualizarInventario.setString(4, miUUId);

                actualizarInventario.executeUpdate();
                actualizarInventario.close();
                conexion.close();
                System.out.println("Inventario actualizada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar inventario: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }
    
    public void limpiarCampos(frmInventario vista) {
        vista.txtCantidad.setText("");   
        vista.cmbProdcutosRepuestos.setSelectedItem("");
        vista.cmbProveedores.setSelectedItem("");
        vista.txtFechaInventario.setText("");
    }
    
    public void cargarDatosEnVista(frmInventario vista) {
        int filaSeleccionada = vista.tbInventario.getSelectedRow();
        if (filaSeleccionada != -1) {
            String cantidad = vista.tbInventario.getValueAt(filaSeleccionada, 3).toString();
            String fecha = vista.tbInventario.getValueAt(filaSeleccionada, 4).toString();

            vista.txtCantidad.setText(cantidad);
            vista.txtFechaInventario.setText(fecha);

            
            String producto = vista.tbInventario.getValueAt(filaSeleccionada, 1).toString();
            String proveedor = vista.tbInventario.getValueAt(filaSeleccionada, 2).toString();

            for (int i = 0; i < vista.cmbProdcutosRepuestos.getItemCount(); i++) {
                if (vista.cmbProdcutosRepuestos.getItemAt(i).toString().contains(producto)) {
                    vista.cmbProdcutosRepuestos.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < vista.cmbProveedores.getItemCount(); i++) {
                if (vista.cmbProveedores.getItemAt(i).toString().contains(proveedor)) {
                    vista.cmbProveedores.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }
    
    
    
}
