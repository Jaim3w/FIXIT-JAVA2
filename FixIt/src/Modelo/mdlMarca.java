
package Modelo;

import Vistas.frmMarca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class mdlMarca {
    private String uuidMarca;
    private String Nombre;
    private String Descripcion;

    public String getUuidMarca() {
        return uuidMarca;
    }

    public void setUuidMarca(String uuidMarca) {
        this.uuidMarca = uuidMarca;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    public void Guardar() {
        Connection conexion = Conexion.getConexion();

        try {
            String sql = "INSERT INTO Marca (UUID_marca, Nombre, Descripcion) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNombre());
            pstmt.setString(3, getDescripcion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la marca: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }
    
    public void Actualizar(JTable tabla) {
    Connection conexion = Conexion.getConexion();
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        String uuid = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            String sql = "UPDATE Marca SET Nombre = ?, Descripcion = ? WHERE UUID_marca = ?";
            PreparedStatement updateMarca = conexion.prepareStatement(sql);
            updateMarca.setString(1, getNombre());
            updateMarca.setString(2, getDescripcion());
            updateMarca.setString(3, uuid);
            updateMarca.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el carro: " + e.getMessage());
        }
        }
    }
    
    public void Eliminar(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        try {
            String sql = "delete from Marca where Nombre = ?";
            PreparedStatement deleteModelo = conexion.prepareStatement(sql);
            deleteModelo.setString(1, miId);
            deleteModelo.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    //limpia los campos poniendolos sin texto o sin elementos cargados
    public void limpiar(frmMarca vista) {
        vista.txtNombre.setText("");
        vista.txtDescripcion.setText("");
    }
    
    public void Buscar(JTable tabla, JTextField miTextField) {
        Connection conexion = Conexion.getConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Marca", "Descripcion"});
        
        //busca todos los datos con un inner join basandose en la placa
        try {
            String sql = "SELECT from Marca WHERE Nombre LIKE ? ";
            PreparedStatement buscarModelo = conexion.prepareStatement(sql);
            buscarModelo.setString(1, miTextField.getText());
            ResultSet rs = buscarModelo.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Nombre"),
                    rs.getString("Descripcion"),
                });
            }

            tabla.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
    
    public void Mostrar(JTable tabla) {
    Connection conexion = Conexion.getConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"Marca", "Descripcion"});
    
    try {
        // Consulta a ejecutar
        String query = "SELECT Nombre, Descripcion from Marca";
        
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getString("Nombre"),
                rs.getString("Descripcion"),
            });
        }
        tabla.setModel(modelo);
        
    } catch (Exception e) {
        System.out.println("Este es el error en el modelo, método mostrar: " + e);
    }
}
    
    public void cargarDatosTabla(frmMarca vista) {
    int filaSeleccionada = vista.tbMarcas.getSelectedRow();
    
    if (filaSeleccionada != -1) {
        String marcaTb = vista.tbMarcas.getValueAt(filaSeleccionada, 0).toString();
        String descripcionTb = vista.tbMarcas.getValueAt(filaSeleccionada, 1).toString();
        
        vista.txtNombre.setText(marcaTb);
        vista.txtDescripcion.setText(descripcionTb);
    }
}
}
