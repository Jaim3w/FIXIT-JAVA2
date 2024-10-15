package Modelo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
import Vistas.frmPerfilAd;
import java.util.ArrayList;
import java.util.List;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;

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

    
    
    // Método para subir la imagen a Imgur
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

    // Método para cargar los datos del perfil
    public void cargarDatosPerfil() {
    Connection conexion = Conexion.getConexion();
    
    // Consulta SQL con un placeholder para el correo
    String sql = "SELECT Empleado.Dui_empleado, Empleado.Nombre, Empleado.Apellido, " +
                 "Empleado.ImagenEmpleado, Empleado.FechaNacimiento, Empleado.Telefono, " +
                 "Usuario.CorreoElectronico " +
                 "FROM Usuario INNER JOIN Empleado ON Usuario.UUID_usuario = Empleado.UUID_usuario " +
                 "WHERE Usuario.CorreoElectronico = ?";  // Usar placeholder para el correo

    try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
        // Obtener el correo desde globalVars
        String correoUsuario = globalVars.correoUsuario;  

        // Establecer el valor del correo en el PreparedStatement
        pstmt.setString(1, correoUsuario);

        // Ejecutar la consulta
        ResultSet rs = pstmt.executeQuery();

        // Verificar si se encontraron datos
        if (rs.next()) {
            // Asignar los valores obtenidos a las variables de la clase
            this.nombre = rs.getString("Nombre");
            this.apellidos = rs.getString("Apellido");
            this.correo = rs.getString("CorreoElectronico");
            this.telefono = rs.getString("Telefono");
            this.nacimiento = rs.getString("FechaNacimiento");
            this.dui = rs.getString("Dui_empleado");
            this.imgUrl = rs.getString("ImagenEmpleado");
this.imgUrl = rs.getString("ImagenEmpleado");
System.out.println("URL de la imagen: " + this.imgUrl); // Verifica la URL

            System.out.println("Datos cargados correctamente.");
        } else {
            System.out.println("No se encontraron datos para el correo: " + correoUsuario);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conexion != null) {
                conexion.close();  // Cerrar la conexión
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
public void cargarImagenPerfil(frmPerfilAd Vista, String imgUrl) {
    try {
        // Cargar la imagen desde la URL
        Image image = ImageIO.read(new URL(imgUrl));
        // Redimensionar la imagen si es necesario
        Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Cambia el tamaño según lo necesites
        // Asignar la imagen a un JLabel
        Vista.lbImagen.setIcon(new ImageIcon(scaledImage));
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar la imagen: " + e.getMessage());
    }
}

    // Método para actualizar el teléfono
    public void actualizarTel() {
        Connection conexion = Conexion.getConexion();
        String sql = "UPDATE Empleado SET Telefono = ? WHERE Dui_empleado = ?";

        try (PreparedStatement updateTel = conexion.prepareStatement(sql)) {
            updateTel.setString(1, getTelefono());
            updateTel.setString(2, dui);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el teléfono: " + e.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close(); // Cierra la conexión para evitar fugas de recursos
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Método para actualizar la imagen
    public void actualizarImg() throws ParseException {
        Connection conexion = Conexion.getConexion();

        try {
            if (img != null) {
                setImgUrl(subirImagenImgur(img));  // Subir la imagen y obtener la URL
            } else {
                JOptionPane.showMessageDialog(null, "Por favor selecciona una imagen antes de guardar.");
                return;
            }

            String sql = "UPDATE Empleado SET ImagenEmpleado = ? WHERE Dui_empleado = ?";
            try (PreparedStatement updateImg = conexion.prepareStatement(sql)) {
                updateImg.setString(1, getImgUrl());
                updateImg.setString(2, dui);

                int rowsAffected = updateImg.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Imagen actualizada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar la imagen.");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar la imagen: " + e.getMessage());
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





