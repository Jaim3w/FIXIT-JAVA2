package Modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class mdlRegistro2 {

    private String duiEmpleado;
    private String correoUsuario;
    private String nombreEmpledo;
    private String apellidoEmplead;
    private String imagenUrlEmpleado;
    private File imagenSeleccionada;
    private String fechaNacimiento;
    private String telefonoEmpleado;
    
    public String getDuiEmpleado() {
        return duiEmpleado;
    }

    public void setDuiEmpleado(String duiEmpleado) {
        this.duiEmpleado = duiEmpleado;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getNombreEmpledo() {
        return nombreEmpledo;
    }

    public void setNombreEmpledo(String nombreEmpledo) {
        this.nombreEmpledo = nombreEmpledo;
    }

    public String getApellidoEmplead() {
        return apellidoEmplead;
    }

    public void setApellidoEmplead(String apellidoEmplead) {
        this.apellidoEmplead = apellidoEmplead;
    }

    public String getImagenUrlEmpleado() {
        return imagenUrlEmpleado;
    }

    public void setImagenUrlEmpleado(String imagenUrlEmpleado) {
        this.imagenUrlEmpleado = imagenUrlEmpleado;
    }

    public File getImagenSeleccionada() {
        return imagenSeleccionada;
    }

    public void setImagenSeleccionada(File imagenSeleccionada) {
        this.imagenSeleccionada = imagenSeleccionada;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }
    
     private String subirImagenImgur (File imageFile) throws IOException, ParseException {
    // Cargar la imagen y convertirla en Base64
    byte[] fileContent = Files.readAllBytes(imageFile.toPath());
    String encodedImage = Base64.getEncoder().encodeToString(fileContent);
 
    // URL de la API de IMGBB
    String uploadUrl = "https://api.imgbb.com/1/upload";
 
    // Crear un cliente HTTP
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost uploadFile = new HttpPost(uploadUrl);
 
    // Reemplazar por tu propia API key de IMGBB
    String apiKey = "0bc1da3a295622b966c14cd6d1d05627";
 
    // Crear el body de la solicitud con la API key y la imagen en Base64
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("key", apiKey));
    params.add(new BasicNameValuePair("image", encodedImage));
 
    // Establecer los parámetros como entidad del request
    uploadFile.setEntity(new UrlEncodedFormEntity(params));
 
    // Ejecutar la solicitud de subida
    CloseableHttpResponse response = httpClient.execute(uploadFile);
    String jsonResponse = EntityUtils.toString(response.getEntity());
 
    // Analizar la respuesta JSON para obtener la URL de la imagen
    JSONObject responseObject = new JSONObject(jsonResponse);
    String uploadedUrl = responseObject.getJSONObject("data").getString("url");
 
    response.close();
    return uploadedUrl;
}
    
   public void Guardar() {
    Connection conexion = null;
    PreparedStatement pstmt = null;
    
    try {
        conexion = Conexion.getConexion();
        
        String urlImagen = subirImagenImgur(imagenSeleccionada);
        setImagenUrlEmpleado(urlImagen);
        
        String sql = "INSERT INTO Empleado (Dui_empleado, UUID_usuario, Nombre, Apellido, ImagenEmpleado, FechaNacimiento, Telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, getDuiEmpleado());
        pstmt.setString(2, getCorreoUsuario());
        pstmt.setString(3, getNombreEmpledo());
        pstmt.setString(4, getApellidoEmplead());
        pstmt.setString(5, getImagenUrlEmpleado());
        pstmt.setString(6, getFechaNacimiento());
        pstmt.setString(7, getTelefonoEmpleado());
        pstmt.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Empleado guardado exitosamente.");
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el Empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        // Cerrar la conexión en caso de error
        try {
            if (conexion != null) conexion.close();
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Asegúrate de cerrar la conexión y el PreparedStatement siempre
        try {
            if (pstmt != null) pstmt.close();
            if (conexion != null) conexion.close();
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
}
