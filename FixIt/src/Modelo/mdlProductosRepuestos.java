/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vistas.frmProductosRepuestos;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
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
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;
/**
 *
 * @author Kevin
 */
public class mdlProductosRepuestos {
    private String item;
    private String nombre;
    private int precio; 
    private File imagenSeleccionada;
    private String imagenUrl;
    private String imgProdcutoRepuestoUrl;
    
    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
    
    public String getImgCarroUrl() {
        return imgProdcutoRepuestoUrl;
    }

    public void setImgCarroUrl(String imgCarroUrl) {
        this.imgProdcutoRepuestoUrl = imgCarroUrl;
    }
    

    public void setImagenSeleccionada(File imagen) {
        this.imagenSeleccionada = imagen;
    }

    public File getImagenSeleccionada() {
        return this.imagenSeleccionada;
    }
    
    private String subirImagenImgur(File imageFile) throws IOException {
    // Cargar la imagen y convertirla en Base64
    byte[] fileContent = Files.readAllBytes(imageFile.toPath());
    String encodedImage = Base64.getEncoder().encodeToString(fileContent);

    // URL de la API de Imgur
    String uploadUrl = "https://api.imgur.com/3/image";

    // Crear un cliente HTTP
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost uploadFile = new HttpPost(uploadUrl);

    // Configurar las cabeceras para autenticar la API de Imgur
    uploadFile.addHeader("Authorization", "Client-ID 8fade595e9f4606");

    // Crear el JSON para el body de la petición
    JSONObject json = new JSONObject();
    json.put("image", encodedImage);

    // Establecer el JSON como entidad de la petición
    StringEntity entity = new StringEntity(json.toString());
    uploadFile.setEntity(entity);
    uploadFile.addHeader("Content-Type", "application/json");

    // Declarar la respuesta
    CloseableHttpResponse response = null;

    try {
        // Ejecutar la solicitud de subida
        response = httpClient.execute(uploadFile);
        
        // Convertir la entidad de la respuesta a una cadena JSON
        String jsonResponse = EntityUtils.toString(response.getEntity());

        // Analizar la respuesta JSON para obtener la URL de la imagen
        JSONObject responseObject = new JSONObject(jsonResponse);
        String uploadedUrl = responseObject.getJSONObject("data").getString("link");

        return uploadedUrl;
    } catch (ParseException e) {
        e.printStackTrace();
        throw new IOException("Error al parsear la respuesta de la imagen: " + e.getMessage());
    } catch (IOException e) {
        e.printStackTrace();
        throw new IOException("Error de entrada/salida: " + e.getMessage());
    } finally {
        if (response != null) {
            response.close();
        }
        httpClient.close();
        }
    }
    
    public String getItem() {
        return item;
    }

    public void setItem(String placa) {
        this.item = item;
    }
    
   public String getNombre() {
        return nombre;
    }

    public void setNombre(String placa) {
        this.nombre = nombre;
    } 
    
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int placa) {
        this.precio = precio;
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
    
    // Ejecuta la inserción de elementos
    String sql = "INSERT INTO ProductoRepuesto (UUID_productoRepuesto, UUID_item, Nombre, ImagenProductoRepuesto, Precio) VALUES (?, ?, ?, ?)";
    PreparedStatement pstmt = conexion.prepareStatement(sql);
    pstmt.setString(1, UUID.randomUUID().toString());
    pstmt.setString(2, getItem());
    pstmt.setString(3, getNombre());
    pstmt.setInt(4, getPrecio());
    pstmt.executeUpdate();
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error al guardar el carro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
    }
    
    public void Buscar(JTable tabla, JTextField miTextField) {
        Connection conexion = Conexion.getConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Nombre", "Precio", "Imagen", "UUID Item"});

        try {
            String sql = "SELECT r.Nombre, r.Precio, r.ImagenProductoRepuesto, i.nombre AS nombreItem FROM ProductoRepuesto r INNER JOIN CategoriaItem i ON c.UUID_item = i.UUID_item WHERE Nombre LIKE ? || '%'";
            PreparedStatement buscarProducto = conexion.prepareStatement(sql);
            buscarProducto.setString(1, miTextField.getText());
            ResultSet rs = buscarProducto.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("UUID_productoRepuesto"),
                    rs.getString("Nombre"),
                    rs.getString("Precio"),
                    rs.getString("ImagenProductoRepuesto"),
                    rs.getString("UUID_item"),
                });
            }

            tabla.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Error en el método Buscar: " + e);
        }
    }
    
    public void Actualizar(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            String UUID = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                if (imagenSeleccionada != null) {
                    setImagenUrl(subirImagenImgur(imagenSeleccionada));
                }

                String sql = "UPDATE ProductoRepuesto SET Nombre = ?, Precio = ? WHERE UUID_productoRepuesto = ?";
                PreparedStatement updateProducto = conexion.prepareStatement(sql);
                updateProducto.setString(1, getNombre());
                updateProducto.setInt(2, getPrecio());
                updateProducto.setString(3, UUID);
                updateProducto.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void Eliminar(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        String UUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        try {
            String sql = "DELETE FROM ProductoRepuesto WHERE UUID_productoRepuesto = ?";
            PreparedStatement deleteProducto = conexion.prepareStatement(sql);
            deleteProducto.setString(1, UUID);
            deleteProducto.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e);
        }
    }
    
    //limpia los campos poniendolos sin texto o sin elementos cargados
    public void limpiar(frmProductosRepuestos vista) {
        vista.txtNombreR.setText("");
        vista.cmbCategoria.setSelectedItem("");
        vista.txtPrecioR.setText("");
        vista.imgR.setIcon(null);
        vista.imgR.setText("");
    }

    public void Mostrar(JTable tabla) {
        Connection conexion = Conexion.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Nombre", "Precio", "Imagen", "UUID Item"});

        try {
            String sql = "SELECT ProductoRepuesto.UUID_productoRepuesto, ProductoRepuesto.Nombre, ProductoRepuesto.ImagenProductoRepuesto, CategoriaItem.Nombre AS CategoriaNombre, ProductoRepuesto.Precio FROM ProductoRepuesto INNER JOIN CategoriaItem ON ProductoRepuesto.UUID_item = CategoriaItem.UUID_item ORDER BY ProductoRepuesto.Nombre";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Nombre"),
                    rs.getString("Precio"),
                    rs.getString("ImagenProductoRepuesto"),
                    rs.getString("CategoriaNombre"),
                });
            }

            tabla.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error en el método Mostrar: " + e);
        }
    }
    
    public void cargarDatosTabla (frmProductosRepuestos vista) {
    
        int filaSeleccionada = vista.tbListaProductosRepuestos.getSelectedRow();
        
        //asigna los datos de la tabla a cada respectivo imput cuando se le da clic a una fila
        if (filaSeleccionada != -1) {
            String CategoriaTb = vista.tbListaProductosRepuestos.getValueAt(filaSeleccionada, 0).toString();
            String NombreTb = vista.tbListaProductosRepuestos.getValueAt(filaSeleccionada, 1).toString();
            String imagenUrlTb = vista.tbListaProductosRepuestos.getValueAt(filaSeleccionada, 2).toString();
            String PrecioTb = vista.tbListaProductosRepuestos.getValueAt(filaSeleccionada, 3).toString();
            
            
            
            vista.cmbCategoria.setSelectedItem(CategoriaTb);
            vista.txtNombreR.setText(NombreTb);
            vista.imgR.setText(imagenUrlTb);
            vista.txtPrecioR.setText(PrecioTb);
        }
    }

    public void Buscar(String busqueda, JTable tbListaProductosRepuestos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}


