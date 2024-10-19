package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

public class MarcasCarro {
    private String uuidMarca;
    private String NombreMarca;
    
    public MarcasCarro(){
        
    }

    public MarcasCarro(String uuidMarca, String NombreMarca) {
        this.uuidMarca = uuidMarca;
        this.NombreMarca = NombreMarca;
    }

    public String getUuidMarca() {
        return uuidMarca;
    }

    public void setUuidMarca(String uuidMarca) {
        this.uuidMarca = uuidMarca;
    }

    public String getNombreMarca() {
        return NombreMarca;
    }

    public void setNombreMarca(String NombreMarca) {
        this.NombreMarca = NombreMarca;
    }

    @Override
    public String toString() {
        return NombreMarca;
    }

    public void CargarComboMarcas(JComboBox comboBox){
    Connection conexion = Conexion.getConexion();
    comboBox.removeAllItems();
    try {
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery("SELECT UUID_marca, Nombre FROM Marca");
        while(rs.next()){
            String uuid = rs.getString("UUID_marca");
            String nombre = rs.getString("Nombre");
            comboBox.addItem(new MarcasCarro(uuid, nombre));
        }
    } catch(SQLException e) {
        e.printStackTrace();
    }
}

}
