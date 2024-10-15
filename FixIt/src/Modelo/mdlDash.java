package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mdlDash {
    public String nombre;
    public String correo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void cargarNombreEmpleadoPorCorreo() {
    Connection conexion = Conexion.getConexion();
    
    // Consulta SQL para extraer solo el Nombre del empleado basado en el correo
    String sql = "SELECT Empleado.Nombre " +
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
            // Extraer solo el nombre
            this.nombre = rs.getString("Nombre");

            // Mostrar el nombre en la consola o actualizar algún componente si lo necesitas
            System.out.println("Nombre del empleado: " + nombre);
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

}
