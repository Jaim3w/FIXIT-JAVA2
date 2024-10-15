
package Modelo;

import java.sql.*;

public class Conexion {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
<<<<<<< HEAD
    //private static final String USUARIO = "jaimedb";
    //private static final String CONTRASENA = "qatar24";
    private static final String USUARIO = "fixit";
    private static final String CONTRASENA = "fixit";
=======

    private static final String USUARIO = "ch";
    private static final String CONTRASENA = "ch";

>>>>>>> bd8d0f017a18bd87f1863fed6d43844838de64ef

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
