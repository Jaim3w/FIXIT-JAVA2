package Modelo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public void cargarDatosPerfil(int id) {
    Connection conexion = Conexion.getConexion();  // Obtener la conexión de la misma manera que en la otra función
    
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
            "    Usuario.CorreoElectronico = 'adriel@gmail.com'";
    
    try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
        pstmt.setInt(1, id);  // Si estás pasando un parámetro 'id' como se indica
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


}
