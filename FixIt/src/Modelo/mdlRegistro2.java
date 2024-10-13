package Modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import javax.swing.JOptionPane;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
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
