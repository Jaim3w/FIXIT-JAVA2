/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Jaimew
 */
import Vistas.frmCarros;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import javax.swing.ImageIcon; // Asegúrate de importar esto
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;


public class mdlCarros {
    private String placa;
    private String cliente;
    private String modelo;
    private String color;
    private String anoCarro;
    private String registroFecha;
    private String descripcion;
    private File imagenSeleccionada;
    private String imagenUrl;
    private String imgCarroUrl;
    
    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
    
    public String getImgCarroUrl() {
        return imgCarroUrl;
    }

    public void setImgCarroUrl(String imgCarroUrl) {
        this.imgCarroUrl = imgCarroUrl;
    }
    

    public void setImagenSeleccionada(File imagen) {
        this.imagenSeleccionada = imagen;
    }

    public File getImagenSeleccionada() {
        return this.imagenSeleccionada;
    }
    
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
   
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAnoCarro() {
        return anoCarro;
    }

    public void setAnoCarro(String anoCarro) {
        this.anoCarro = anoCarro;
    }

    public String getRegistroFecha() {
        return registroFecha;
    }

    public void setRegistroFecha(String registroFecha) {
        this.registroFecha = registroFecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    //funcion que obtiene la fecha y la pone en formato de dia-mes-año
    public String obtenerFechaActual() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return now.format(formatter);
    }
    
    public void Guardar() {
    Connection conexion = Conexion.getConexion();

    try {
    // Verifica que el campo de imagen no esté vacío
    if (imagenSeleccionada != null) {
        setImgCarroUrl(subirImagenImgur(imagenSeleccionada)); // Usar la imagen seleccionada
    } else {
        JOptionPane.showMessageDialog(null, "Por favor selecciona una imagen antes de guardar.");
        return; // Salir si no hay imagen seleccionada
    }
    
    // Obtiene la fecha actual
    setRegistroFecha(obtenerFechaActual());

    // Ejecuta la inserción de elementos
    String sql = "INSERT INTO Carro (Placa_carro, Dui_cliente, UUID_modelo, Color, Ano, ImagenCarro, FechaRegistro, Descripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement pstmt = conexion.prepareStatement(sql);
    pstmt.setString(1, getPlaca());
    pstmt.setString(2, getCliente());
    pstmt.setString(3, getModelo());
    pstmt.setString(4, getColor());
    pstmt.setString(5, getAnoCarro());
    pstmt.setString(6, getImgCarroUrl());
    pstmt.setString(7, getRegistroFecha());
    pstmt.setString(8, getDescripcion());
    pstmt.executeUpdate();
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error al guardar el carro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}

}


    //funcion para realizar busqueda
    public void Buscar(JTable tabla, JTextField miTextField) {
        Connection conexion = Conexion.getConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Placas", "Cliente", "Modelo", "Color", "Año", "Imagen", "Fecha de Registro", "Descripcion"});
        
        //busca todos los datos con un inner join basandose en la placa
        try {
            String sql = "SELECT " +
                       "c.Placa_carro, " +
                       "c.Color, " +
                       "c.Ano, " +
                       "c.ImagenCarro, " +
                       "c.FechaRegistro, " +
                       "c.Descripcion, " +
                       "m.Nombre AS NombreModelo, " +
                       "cl.Nombre AS NombreCliente " +
                       "FROM Carro c " +
                       "INNER JOIN Modelo m ON c.UUID_modelo = m.UUID_modelo " +
                       "INNER JOIN Cliente cl ON c.Dui_cliente = cl.Dui_cliente WHERE Placa_carro LIKE ? || '%'";
            PreparedStatement buscarCarro = conexion.prepareStatement(sql);
            buscarCarro.setString(1, miTextField.getText());
            ResultSet rs = buscarCarro.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Placa_carro"),
                    rs.getString("NombreCliente"),
                    rs.getString("NombreModelo"),
                    rs.getString("Color"), 
                    rs.getString("Ano"), 
                    rs.getString("FechaRegistro"), 
                    rs.getString("Descripcion"), 
                    rs.getString("ImagenCarro"), 
                });
            }

            tabla.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
    
    //funcion para actualizar
    public void Actualizar(JTable tabla) {
    Connection conexion = Conexion.getConexion();
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        String Placas = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            if (imagenSeleccionada != null) {
                setImgCarroUrl(subirImagenImgur(imagenSeleccionada));
            }

            String sql = "UPDATE Carro SET Color = ?, Ano = ?, Descripcion = ?, UUID_modelo = ?, Dui_cliente = ?, ImagenCarro = ? WHERE Placa_carro = ?";
            PreparedStatement updateCarro = conexion.prepareStatement(sql);
            updateCarro.setString(1, getColor());
            updateCarro.setString(2, getAnoCarro());
            updateCarro.setString(3, getDescripcion());
            updateCarro.setString(4, getModelo());
            updateCarro.setString(5, getCliente());
            updateCarro.setString(6, getImgCarroUrl());
            updateCarro.setString(7, Placas);
            updateCarro.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el carro: " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(mdlCarros.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
            Logger.getLogger(mdlCarros.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }


    //funcion de eliminar
    public void Eliminar(JTable tabla) {
        Connection conexion = Conexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //elimina los datos que contenga la fila con la placa que se selecciono
        try {
            String sql = "delete from Carro where Placa_carro = ?";
            PreparedStatement deleteCarro = conexion.prepareStatement(sql);
            deleteCarro.setString(1, miId);
            deleteCarro.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    //limpia los campos poniendolos sin texto o sin elementos cargados
    public void limpiar(frmCarros vista) {
        vista.txtPlacaCarro.setText("");
        vista.cmbClienteCarro.setSelectedItem("");
        vista.cmbModeloCarro.setSelectedItem("");
        vista.txtColorCarro.setText("");
        vista.txtAnoCarro.setText("2024");
        vista.txtDescripcionCarro.setText("");
        vista.lblImagenCarro.setIcon(null);
        vista.lblImagenCarro.setText("");
    }
   
    //funcion que hace que los datos se muestren en la tabla siempre que se carga la actividad
    public void Mostrar(JTable tabla) {
    Connection conexion = Conexion.getConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"Placas", "Cliente", "Modelo", "Color", "Año", "Fecha de Registro", "Descripcion", "Imagen"});
    
    try {
        // Consulta a ejecutar
        String query = "SELECT " +
                       "c.Placa_carro, " +
                       "c.Color, " +
                       "c.Ano, " +
                       "c.ImagenCarro, " +
                       "c.FechaRegistro, " +
                       "c.Descripcion, " +
                       "m.Nombre AS NombreModelo, " +
                       "cl.Nombre AS NombreCliente " +
                       "FROM Carro c " +
                       "INNER JOIN Modelo m ON c.UUID_modelo = m.UUID_modelo " +
                       "INNER JOIN Cliente cl ON c.Dui_cliente = cl.Dui_cliente";
        
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getString("Placa_carro"),
                rs.getString("NombreCliente"),
                rs.getString("NombreModelo"),
                rs.getString("Color"), 
                rs.getString("Ano"), 
                rs.getString("FechaRegistro"), 
                rs.getString("Descripcion"), 
                rs.getString("ImagenCarro"), 
                
            });
        }
        tabla.setModel(modelo);
        
    } catch (Exception e) {
        System.out.println("Este es el error en el modelo, método mostrar: " + e);
    }
}

    //funcion que carga los datos en la tabla
    public void cargarDatosTabla(frmCarros vista) {
    int filaSeleccionada = vista.tbListaCarros.getSelectedRow();
    
    // Asigna los datos de la tabla a cada respectivo input cuando se le da clic a una fila
    if (filaSeleccionada != -1) {
        String PlacaTb = vista.tbListaCarros.getValueAt(filaSeleccionada, 0).toString();
        String ClienteTb = vista.tbListaCarros.getValueAt(filaSeleccionada, 1).toString();
        String ModeloTb = vista.tbListaCarros.getValueAt(filaSeleccionada, 2).toString();
        String ColorTb = vista.tbListaCarros.getValueAt(filaSeleccionada, 3).toString();
        String AnoTb = vista.tbListaCarros.getValueAt(filaSeleccionada, 4).toString();
        String IngresoTb = vista.tbListaCarros.getValueAt(filaSeleccionada, 5).toString();
        String DescripcionTb = vista.tbListaCarros.getValueAt(filaSeleccionada, 6).toString();
        String imagenUrlTb = vista.tbListaCarros.getValueAt(filaSeleccionada, 7).toString();
        
        vista.txtPlacaCarro.setText(PlacaTb);
        vista.cmbClienteCarro.setSelectedItem(ClienteTb);
        vista.cmbModeloCarro.setSelectedItem(ModeloTb);
        vista.txtColorCarro.setText(ColorTb);
        vista.txtAnoCarro.setText(AnoTb);
        vista.txtIngresoCarro.setText(IngresoTb);
        vista.txtDescripcionCarro.setText(DescripcionTb);

        // Cargar la imagen desde la URL y mostrarla en el JLabel
        try {
            // Crear un ImageIcon a partir de la URL
            ImageIcon icon = new ImageIcon(new URL(imagenUrlTb)); // URL debe ser de tipo java.net.URL
            vista.lblImagenCarro.setIcon(icon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            vista.lblImagenCarro.setText("Error al cargar la imagen");
        }
    }
}

    
    public ArrayList<String[]> obtenerCarrosCards() {
    ArrayList<String[]> carros = new ArrayList<>();
    Connection conexion = Conexion.getConexion();
    
    // Utilizar el INNER JOIN proporcionado
    String sql = "SELECT " +
                 "c.Placa_carro, " +
                 "c.Color, " +
                 "c.Ano, " +
                 "c.ImagenCarro, " +
                 "c.FechaRegistro, " +
                 "c.Descripcion, " +
                 "m.Nombre AS NombreModelo, " +
                 "cl.Nombre AS NombreCliente " +
                 "FROM Carro c " +
                 "INNER JOIN Modelo m ON c.UUID_modelo = m.UUID_modelo " +
                 "INNER JOIN Cliente cl ON c.Dui_cliente = cl.Dui_cliente";

    try {
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] carro = new String[8]; // Cambiar a 8 para incluir todos los campos necesarios
            carro[0] = rs.getString("Placa_carro");
            carro[1] = rs.getString("NombreCliente");
            carro[2] = rs.getString("NombreModelo");
            carro[3] = rs.getString("Color");
            carro[4] = rs.getString("Ano");
            carro[5] = rs.getString("FechaRegistro");
            carro[6] = rs.getString("Descripcion");
            carro[7] = rs.getString("ImagenCarro");
            carros.add(carro);
        }
        rs.close();
        st.close();
        conexion.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return carros;
}
private ImageIcon resizeImage(String imagePath, int width, int height) {
    ImageIcon icon = new ImageIcon(imagePath);
    Image img = icon.getImage(); // transform it 
    Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // scale it the smooth way
    return new ImageIcon(newImg); // transform it back
}

    
    
    public void Buscar(String busqueda, JTable tbListaCarros) {
    }
}
