
package Modelo;

import Vistas.frmModelo;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
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
import org.apache.hc.core5.http.ParseException;


public class mdlModelo {
    private String uuidModelo;
    private String uuidMarca;
    private String Nombre;

    public String getUuidModelo() {
        return uuidModelo;
    }

    public void setUuidModelo(String uuidModelo) {
        this.uuidModelo = uuidModelo;
    }

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
    
    public void Guardar() {
        Connection conexion = Conexion.getConexion();

        try {
            String sql = "INSERT INTO Modelo (UUID_modelo, UUID_marca, nombre) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getUuidMarca());
            pstmt.setString(3, getNombre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el carro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }
    
    public void Actualizar(JTable tabla) {
    Connection conexion = Conexion.getConexion();
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        String modeloName = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            String sql = "UPDATE Modelo SET Nombre = ?, UUID_marca = ? WHERE UUID_modelo = ?";
            PreparedStatement updateCarro = conexion.prepareStatement(sql);
            updateCarro.setString(1, getNombre());
            updateCarro.setString(2, getUuidMarca());
            updateCarro.setString(3, modeloName);
            updateCarro.executeUpdate();
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
            String sql = "delete from Modelo where Nombre = ?";
            PreparedStatement deleteModelo = conexion.prepareStatement(sql);
            deleteModelo.setString(1, miId);
            deleteModelo.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    //limpia los campos poniendolos sin texto o sin elementos cargados
    public void limpiar(frmModelo vista) {
        vista.txtNombre.setText("");
        vista.cmbMarca.setSelectedItem(null);
    }
    
    public void Buscar(JTable tabla, JTextField miTextField) {
        Connection conexion = Conexion.getConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Marca", "Modelo"});
        
        //busca todos los datos con un inner join basandose en la placa
        try {
            String sql = "SELECT Marca.Nombre AS NombreMarca, Modelo.Nombre AS NombreModelo\n" +
                        "FROM Modelo\n" +
                        "INNER JOIN Marca ON Modelo.UUID_marca = Marca.UUID_marca\n" +
                        "WHERE Modelo.Nombre LIKE ? OR Marca.Nombre LIKE ?";
            PreparedStatement buscarModelo = conexion.prepareStatement(sql);
            buscarModelo.setString(1, miTextField.getText());
            ResultSet rs = buscarModelo.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("NombreMarca"),
                    rs.getString("NombreModelo"),
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
    modelo.setColumnIdentifiers(new Object[]{"Marca", "Modelo"});
    
    try {
        // Consulta a ejecutar
        String query = "SELECT Marca.Nombre AS NombreMarca, Modelo.Nombre AS NombreModelo\n" +
                        "FROM Modelo\n" +
                        "INNER JOIN Marca ON Modelo.UUID_marca = Marca.UUID_marca";
        
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getString("NombreMarca"),
                rs.getString("NombreModelo"),
                
            });
        }
        tabla.setModel(modelo);
        
    } catch (Exception e) {
        System.out.println("Este es el error en el modelo, método mostrar: " + e);
    }
}
    
    public void cargarDatosTabla(frmModelo vista) {
    int filaSeleccionada = vista.tbModelos.getSelectedRow();
    
    if (filaSeleccionada != -1) {
        String marcaTb = vista.tbModelos.getValueAt(filaSeleccionada, 0).toString();
        String modeloTb = vista.tbModelos.getValueAt(filaSeleccionada, 1).toString();
        
        vista.txtNombre.setText(marcaTb);
        vista.cmbMarca.setSelectedItem(modeloTb);
    }
}
}
