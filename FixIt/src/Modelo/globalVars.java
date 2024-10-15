package Modelo;

import java.sql.Connection; // Asegúrate de que esta es la correcta
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class globalVars {
    public static String correoUsuario;
    public static String NombreRol;
    
    public static void cargarRolPorCorreo() {
        String query = "SELECT r.Nombre FROM Usuario u JOIN Rol r ON u.UUID_rol = r.UUID_rol WHERE u.CorreoElectronico = ?";
        
        try (Connection connection = Conexion.getConexion(); // Obtener conexión
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            if (connection == null) {
                System.out.println("Error al conectar a la base de datos.");
                return; // Salir si la conexión es nula
            }
            
            preparedStatement.setString(1, correoUsuario); // Usar la variable global correoUsuario
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                NombreRol = resultSet.getString("Nombre"); // Extraer el nombre del rol
                System.out.println("Rol cargado: " + NombreRol); // Mensaje de verificación
            } else {
                System.out.println("No se encontró el rol para el correo: " + correoUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}

