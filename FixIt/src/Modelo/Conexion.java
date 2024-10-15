
package Modelo;

import java.sql.*;

public class Conexion {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    //private static final String USUARIO = "jaimedb";
    //private static final String CONTRASENA = "qatar24";
<<<<<<< HEAD
    private static final String USUARIO = "fixit";
    private static final String CONTRASENA = "fixit";
=======
    private static final String USUARIO = "fixxit";
    private static final String CONTRASENA = "fixxit";
>>>>>>> f52180fc8b1021090bcd5af0ec36ff58de3a13bb

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
