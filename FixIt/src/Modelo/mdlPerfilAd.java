package Modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;

public class mdlPerfilAd {

    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String nacimiento;
    private String dui;
    private File img;
    private String imgUrl;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
    
    public void cargarDatosPerfil() {
        Connection conexion = Conexion.getConexion();  // Obtener la conexión

        String sql = "SELECT \n" +
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
                "    Usuario.UUID_usuario = Empleado.UUID_usuario\n" +
                "WHERE \n" +
                "    Usuario.CorreoElectronico = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            // Accedemos al correo almacenado en la clase GlobalVars
            String correoUsuario = globalVars.correoUsuario;
            
            pstmt.setString(1, correoUsuario);  // Usamos el correo como parámetro en la consulta
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                this.nombre = rs.getString("Nombre");
                this.apellidos = rs.getString("Apellido");
                this.correo = rs.getString("CorreoElectronico");
                this.telefono = rs.getString("Telefono");
                this.nacimiento = rs.getString("FechaNacimiento");
                this.dui = rs.getString("Dui_empleado");
                this.imgUrl = rs.getString("ImagenEmpleado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();  // Cierra la conexión
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void ActualizarTel() {
    Connection conexion = Conexion.getConexion();
    try {
        String sql = "UPDATE Empleado SET Telefono = ? WHERE Dui_empleado = ?";
        PreparedStatement updateTel = conexion.prepareStatement(sql);
        
        updateTel.setString(1, getTelefono());
        updateTel.setString(2, dui);
        
        updateTel.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Teléfono actualizado correctamente.");
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar el teléfono del empleado: " + e.getMessage());
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
    
    public void ActualizarImg() {
        Connection conexion = Conexion.getConexion();
    try {
        if (img != null) {
        setImgUrl(subirImagenImgur(img)); // Usar la imagen seleccionada
    } else {
        JOptionPane.showMessageDialog(null, "Por favor selecciona una imagen antes de guardar.");
        return; // Salir si no hay imagen seleccionada
    }
        
        setImgUrl(subirImagenImgur(img));
        
        String sql = "UPDATE Empleado SET ImagenEmpleado = ? WHERE Dui_empleado = ?";
        PreparedStatement updateTel = conexion.prepareStatement(sql);
        
        updateTel.setString(1, getImgUrl());
        updateTel.setString(2, dui);
        
        updateTel.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Teléfono actualizado correctamente.");
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar el teléfono del empleado: " + e.getMessage());
    }   catch (IOException ex) {
            Logger.getLogger(mdlPerfilAd.class.getName()).log(Level.SEVERE, null, ex);
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
}




