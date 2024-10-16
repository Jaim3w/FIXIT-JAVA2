package Modelo;

import Controlador.controladorCorreo;
import Vistas.frmRegistrarse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author rdlfp
 */
public class Usuarios {

    private String UUID_usuario;
    private String CorreoElectronico;
    private String Contrasena;
    private String UUID_rol;

    public String getCorreo() {
        return CorreoElectronico;
    }

    //getters y setters
    public String getUUID_usuario() {
        return UUID_usuario;
    }

    public void setUUID_usuario(String UUID_usuario) {
        this.UUID_usuario = UUID_usuario;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Encriptqacion.encriptar(Contrasena);
    }

    public String getUUID_rol() {
        return UUID_rol;
    }

    public void setUUID_rol(String UUID_rol) {
        this.UUID_rol = UUID_rol;
    }

    // Constructor

    public Usuarios(String uuid_rol, String uuid, String correo, String contrasena) {
        this.UUID_rol = uuid_rol;
        this.UUID_usuario = uuid;
        this.CorreoElectronico = correo;
        this.Contrasena = contrasena;
    }

    // Sobrescribir el método toString para mostrar el correo electrónico en el ComboBox
    @Override
    public String toString() {
        return this.getCorreo(); // O cualquier otro campo relevante
    }

   
    //Aqui se insertan los usuarios la base
    public void InsertarUser() {

        Connection conexion = Conexion.getConexion();
        try {

            PreparedStatement addUser = conexion.prepareStatement("Insert into Usuario(UUID_usuario,UUID_rol,CorreoElectronico,Contrasena) values(?,(SELECT UUID_rol FROM Rol WHERE Nombre = 'Administrador') ,?,?)");
            addUser.setString(1, UUID.randomUUID().toString());
            addUser.setString(2, getCorreoElectronico());
            addUser.setString(3, getContrasena());
            addUser.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en el modelo" + e);
        }
    }
    
    public void InsertarUserEmpleado() {
        Connection conexion = Conexion.getConexion();
        try {

            PreparedStatement addUser = conexion.prepareStatement("Insert into Usuario(UUID_usuario, UUID_rol, CorreoElectronico, Contrasena) values(?,(SELECT UUID_rol FROM Rol WHERE Nombre = 'Empleado') ,?,?)");
            addUser.setString(1, UUID.randomUUID().toString());
            addUser.setString(2, getCorreoElectronico());
            addUser.setString(3, getContrasena());
            addUser.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en el modelo" + e);
        }
    }

    public boolean Verificar() {

        boolean usuarios = false;

        Connection con = Conexion.getConexion();
        try {

            PreparedStatement Verificar = con.prepareStatement("select * from Usuario");
            ResultSet rs = Verificar.executeQuery();

            usuarios = rs.next();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    //Aqui se hace el loguin del programa
    public boolean IniciarLogin() {

        Connection conexion = Conexion.getConexion();
        boolean resultado = false;

        try {
            String sql = "Select * from Usuario where CorreoElectronico = ? and Contrasena = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, getCorreoElectronico());
            statement.setString(2, getContrasena());

            ResultSet resulset = statement.executeQuery();
            if (resulset.next()) {
                resultado = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error en el modelo");
        }

        return resultado;
    }

    //funcion para actualizar la contraseña
    public void ActualizarContrasena(Usuarios usuario) {
        Connection conexion = Conexion.getConexion();
        try {
            PreparedStatement smpt = conexion.prepareStatement("Update Usuario set Contrasena = ? where CorreoElectronico = ?");
            smpt.setString(1, usuario.getContrasena());
            System.err.println("ESTE ES EL CORREO AL QUE LE ESTOY CACTUALIZANDO LA CONRA" + controladorCorreo.correoGlobal);
            smpt.setString(2, controladorCorreo.correoGlobal);
            smpt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errro al actualizar la contraseña" + e.getMessage());
        }
    }
       public Usuarios(){

}
    
   /*public void CargarComboUser(JComboBox comboBox){
   Connection conexion=Conexion.getConexion();
   comboBox.removeAllItems();
       try {
           Statement statement=conexion.createStatement();
           ResultSet  rs=statement.executeQuery("select * from Usuario");
           while(rs.next()){
           String uuid=rs.getString("UUID_usuario");
           String uuidrol=rs.getString("UUID_rol");
           String correo=rs.getString("CorreoElectronico");
           String contrasena=rs.getString("Contrasena");
           comboBox.addItem(new Usuarios(uuid,uuidrol,correo,contrasena));
           }
       } catch (SQLException ex) {
           ex.printStackTrace();
       } 
   
   }*/
   
   public void CargarComboUser(JComboBox comboBox) {
    Connection conexion = Conexion.getConexion();
    comboBox.removeAllItems();
    try {
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery("select * from Usuario");
        while (rs.next()) {
            String uuid = rs.getString("UUID_usuario");
            String uuidrol = rs.getString("UUID_rol");
            String correo = rs.getString("CorreoElectronico");
            String contrasena = rs.getString("Contrasena");
            comboBox.addItem(new Usuarios(uuidrol, uuid, correo, contrasena)); // Cargar usuarios
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
   
    public void CargarComboEmpleado(JComboBox comboBox) {
    Connection conexion = Conexion.getConexion();
    comboBox.removeAllItems(); // Limpia el ComboBox
    try {
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery("SELECT u.UUID_usuario, u.CorreoElectronico, u.Contrasena, u.UUID_rol " +
                                              "FROM Usuario u " +
                                              "JOIN Rol r ON u.UUID_rol = r.UUID_rol " +
                                              "WHERE r.Nombre = 'Empleado'");
        while (rs.next()) {
            String uuid = rs.getString("UUID_usuario");
            String uuidrol = rs.getString("UUID_rol");
            String correo = rs.getString("CorreoElectronico");
            String contrasena = rs.getString("Contrasena");
            comboBox.addItem(new Usuarios(uuidrol, uuid, correo, contrasena)); // Agrega objetos Usuarios
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

   
   public boolean ValidarCorreo() {

        Connection conexion = Conexion.getConexion();
        boolean resultado = false;
        try {
            String sql = "Select * from Usuario where CorreoElectronico = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, getCorreoElectronico());
            ResultSet resulset = statement.executeQuery();
            if (resulset.next()) {
                resultado = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error en el modelo");
        }

        return resultado;
    }


}