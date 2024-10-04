
package Modelo;

import java.sql.*;

public class Conexion {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    //private static final String USUARIO = "jaimeu";
    //private static final String CONTRASENA = "qatar24";
<<<<<<< HEAD
    private static final String USUARIO = "FIXIT";
    private static final String CONTRASENA = "FIXIT";
=======
    private static final String USUARIO = "fixa";
    private static final String CONTRASENA = "qatar24";
>>>>>>> 3c3d0d9232d6954d4c2e0e60e29cc3c05a40641c

    public static Connection getConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
 
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            return conexion;
        } catch (SQLException e) {
            System.out.println("Este es el error" + e);
              return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("este es el error de la clase" + ex);
              return null;
        }
    }
}
