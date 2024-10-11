/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vistas.frmUsuarios;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;

/**
 *
 * @author Kevin
 */
public class mdlEmpleados {
    
 private String duiEmpleado;
    private String correoEmpleado;
    private String nombre;
    private String apellido;
    private String imagenEmpleado;
    private File imagenSeleccionada;
    private String fechaNacimiento;
    private String telefono;

    // Getters y Setters
    public String getDuiEmpleado() {
        return duiEmpleado;
    }

    public void setDuiEmpleado(String duiEmpleado) {
        this.duiEmpleado = duiEmpleado;
    }

    public String getUuidUsuario() {
        return correoEmpleado;
    }

    public void setUuidUsuario(String uuidUsuario) {
        this.correoEmpleado = uuidUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagenEmpleado() {
        return imagenEmpleado;
    }

    public void setImagenEmpleado(String imagenEmpleado) {
        this.imagenEmpleado = imagenEmpleado;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    } 
    
    private String subirImagenImgur(File imageFile) throws IOException {
    // Cargar la imagen y convertirla en Base64
    byte[] fileContent = Files.readAllBytes(imageFile.toPath());
    String encodedImage = Base64.getEncoder().encodeToString(fileContent);

    // URL de la API de Imgur
    String uploadUrl = "https://api.imgur.com/3/image";

    // Crear un cliente HTTP
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost uploadFile = new HttpPost(uploadUrl);

    // Configurar las cabeceras para autenticar la API de Imgur
    uploadFile.addHeader("Authorization", "Client-ID 8fade595e9f4606");

    // Crear el JSON para el body de la petición
    JSONObject json = new JSONObject();
    json.put("image", encodedImage);

    // Establecer el JSON como entidad de la petición
    StringEntity entity = new StringEntity(json.toString());
    uploadFile.setEntity(entity);
    uploadFile.addHeader("Content-Type", "application/json");

    // Declarar la respuesta
    CloseableHttpResponse response = null;

    try {
        // Ejecutar la solicitud de subida
        response = httpClient.execute(uploadFile);
        
        // Convertir la entidad de la respuesta a una cadena JSON
        String jsonResponse = EntityUtils.toString(response.getEntity());

        // Analizar la respuesta JSON para obtener la URL de la imagen
        JSONObject responseObject = new JSONObject(jsonResponse);
        String uploadedUrl = responseObject.getJSONObject("data").getString("link");

        return uploadedUrl;
    } catch (ParseException e) {
        e.printStackTrace();
        throw new IOException("Error al parsear la respuesta de la imagen: " + e.getMessage());
    } catch (IOException e) {
        e.printStackTrace();
        throw new IOException("Error de entrada/salida: " + e.getMessage());
    } finally {
        if (response != null) {
            response.close();
        }
        httpClient.close();
        }
    }
    
    public void Guardar() {
    Connection conexion = Conexion.getConexion();

        try {
        String sql = "INSERT INTO Empleado (Dui_empleado, UUID_usuario, Nombre, Apellido, ImagenEmpleado, FechaNacimiento, Telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, getDuiEmpleado());
        pstmt.setString(2, getUuidUsuario());
        pstmt.setString(3, getNombre());
        pstmt.setString(4, getApellido());
        pstmt.setString(5, getImagenEmpleado());
        pstmt.setString(6, getFechaNacimiento());
        pstmt.setString(7, getTelefono());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el Empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

}
    
    public void Mostrar(JTable tabla) {
    Connection conexion = Conexion.getConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"Dui_empleado", "CorreoElectronico", "Nombre", "Apellido", "ImagenEmpleado", "FechaNacimiento", "Telefono"});
    
    try {
        // Consulta a ejecutar
        String query = "SELECT \n" +
                       "    Empleado.Dui_empleado,\n" +
                       "    Empleado.Nombre,\n" +
                       "    Empleado.Apellido,\n" +
                       "    Empleado.ImagenEmpleado,\n" +
                       "    Empleado.FechaNacimiento,\n" +
                       "    Empleado.Telefono,\n" +
                       "    Usuario.CorreoElectronico,\n" +
                       "    Usuario.Contrasena\n" +
                       "FROM \n" +
                       "    Usuario\n" +
                       "INNER JOIN \n" +
                       "    Empleado \n" +
                       "ON \n" +
                       "    Usuario.UUID_usuario = Empleado.UUID_usuario;";
        
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getString("Dui_empleado"),
                rs.getString("CorreoElectronico"),
                rs.getString("Nombre"),
                rs.getString("Apellido"), 
                rs.getString("ImagenEmpleado"), 
                rs.getString("FechaNacimiento"), 
                rs.getString("Telefono"), 
                
            });
        }
        tabla.setModel(modelo);
        
    } catch (Exception e) {
        System.out.println("Este es el error en el modelo, método mostrar: " + e);
    }
}

    //funcion que carga los datos en la tabla
    public void cargarDatosTabla (frmUsuarios vista) {
    
        int filaSeleccionada = vista.dtgempleado.getSelectedRow();
        
        //asigna los datos de la tabla a cada respectivo imput cuando se le da clic a una fila
        if (filaSeleccionada != -1) {
            String DuiTb = vista.dtgempleado.getValueAt(filaSeleccionada, 0).toString();
            String CorreoTb = vista.dtgempleado.getValueAt(filaSeleccionada, 1).toString();
            String NombreTb = vista.dtgempleado.getValueAt(filaSeleccionada, 2).toString();
            String ApellidoTb = vista.dtgempleado.getValueAt(filaSeleccionada, 3).toString();
            String ImagenTb = vista.dtgempleado.getValueAt(filaSeleccionada, 4).toString();
            String FechaTb = vista.dtgempleado.getValueAt(filaSeleccionada, 5).toString();
            String TelefonoTb = vista.dtgempleado.getValueAt(filaSeleccionada, 6).toString();
            
            vista.txtdui.setText(DuiTb);
            vista.cmbCorreoEmpleado.setSelectedItem(CorreoTb);
            vista.txtnombre.setText(NombreTb);
            vista.txtapellido.setText(ApellidoTb);
            vista.txtImagenUrl.setText(ImagenTb);
            vista.txtTelefono.setText(TelefonoTb);
            
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaConvertida = formatoFecha.parse(FechaTb);
            vista.txtFecha.setDate(fechaConvertida);
        }   catch (java.text.ParseException ex) {
                Logger.getLogger(mdlEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
