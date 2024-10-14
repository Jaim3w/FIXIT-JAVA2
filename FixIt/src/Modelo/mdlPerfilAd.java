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

    // Método para subir la imagen a Imgur
    private String subirImagenImgur(File imageFile) throws IOException {
        byte[] fileContent = Files.readAllBytes(imageFile.toPath());
        String encodedImage = Base64.getEncoder().encodeToString(fileContent);

        String uploadUrl = "https://api.imgur.com/3/image";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(uploadUrl);

        uploadFile.addHeader("Authorization", "Client-ID 8fade595e9f4606");

        JSONObject json = new JSONObject();
        json.put("image", encodedImage);

        StringEntity entity = new StringEntity(json.toString());
        uploadFile.setEntity(entity);
        uploadFile.addHeader("Content-Type", "application/json");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(uploadFile);
            String jsonResponse = EntityUtils.toString(response.getEntity());

            JSONObject responseObject = new JSONObject(jsonResponse);
            return responseObject.getJSONObject("data").getString("link");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new IOException("Error al subir la imagen: " + e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
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
    public void actualizarImg() {
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





