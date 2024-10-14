/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vistas.frmUsuarios;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class mdlEmpleados {
    
 private String duiEmpleado;
    private String correoEmpleado;
    private String nombre;
    private String apellido;
    private String imagenEmpleado;
    private File imagenSeleccionada;
    private String fechaNacimiento;
    private String telefono;

    public File getImagenSeleccionada() {
        return imagenSeleccionada;
    }

    public void setImagenSeleccionada(File imagenSeleccionada) {
        this.imagenSeleccionada = imagenSeleccionada;
    }

    // Getters y Setters
    public String getDuiEmpleado() {
        return duiEmpleado;
    }

    public void setDuiEmpleado(String duiEmpleado) {
        this.duiEmpleado = duiEmpleado;
    }

    public String getUuidUsuario() {
        return correoEmpleado;
    }

    public void setUuidUsuario(String uuidUsuario) {
        this.correoEmpleado = uuidUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagenEmpleado() {
        return imagenEmpleado;
    }

    public void setImagenEmpleado(String imagenEmpleado) {
        this.imagenEmpleado = imagenEmpleado;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
    
    public void Guardar() {
    Connection conexion = null;
    PreparedStatement pstmt = null;
    
    try {
        conexion = Conexion.getConexion();
        
        String urlImagen = subirImagenImgur(imagenSeleccionada);
        setImagenEmpleado(urlImagen);
        
        String sql = "INSERT INTO Empleado (Dui_empleado, UUID_usuario, Nombre, Apellido, ImagenEmpleado, FechaNacimiento, Telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        pstmt = conexion.prepareStatement(sql);
        pstmt.setString(1, getDuiEmpleado());
        pstmt.setString(2, getUuidUsuario());
        pstmt.setString(3, getNombre());
        pstmt.setString(4, getApellido());
        pstmt.setString(5, getImagenEmpleado());
        pstmt.setString(6, getFechaNacimiento());
        pstmt.setString(7, getTelefono());
        pstmt.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Empleado guardado exitosamente.");
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el Empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        // Cerrar la conexión en caso de error
        try {
            if (conexion != null) conexion.close();
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Asegúrate de cerrar la conexión y el PreparedStatement siempre
        try {
            if (pstmt != null) pstmt.close();
            if (conexion != null) conexion.close();
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    
    public void Actualizar(JTable tabla) {
    Connection conexion = Conexion.getConexion();
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        String Dui = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        try {
            // Aquí verifica si la imagen ha sido seleccionada nuevamente
            String nuevaImagen = imagenSeleccionada != null ? subirImagenImgur(imagenSeleccionada) : getImagenEmpleado();
            setImagenEmpleado(nuevaImagen);  // Actualiza la URL de la imagen

            String sql = "UPDATE Empleado SET UUID_usuario = ?, Nombre = ?, Apellido = ?, ImagenEmpleado = ?, FechaNacimiento = ?, Telefono = ? WHERE Dui_empleado = ?";
            PreparedStatement updateEmpleado = conexion.prepareStatement(sql);
            updateEmpleado.setString(1, getUuidUsuario());
            updateEmpleado.setString(2, getNombre());
            updateEmpleado.setString(3, getApellido());
            updateEmpleado.setString(4, getImagenEmpleado());  // Asegura que se actualice con la nueva URL de la imagen
            updateEmpleado.setString(5, getFechaNacimiento());
            updateEmpleado.setString(6, getTelefono());
            updateEmpleado.setString(7, Dui);

            // Ejecuta la actualización
            updateEmpleado.executeUpdate();
            JOptionPane.showMessageDialog(null, "Empleado actualizado exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el empleado: " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(mdlEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, selecciona un empleado para actualizar.");
    }
}

    
    public void Eliminar(JTable tabla) {
        Connection conexion = Conexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //elimina los datos que contenga la fila con la placa que se selecciono
        try {
            String sql = "delete from Empleado where Dui_empleado = ?";
            PreparedStatement deleteCarro = conexion.prepareStatement(sql);
            deleteCarro.setString(1, miId);
            deleteCarro.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    //limpia los campos poniendolos sin texto o sin elementos cargados
    public void limpiar(frmUsuarios vista) {
        vista.txtdui.setText("");
        vista.cmbCorreoEmpleado.setSelectedItem("Elige un correo");
        vista.txtnombre.setText("");
        vista.txtapellido.setText("");
        vista.txtImagenUrl.setText("");
        vista.txtImagenUrl.setIcon(null);
        vista.txtTelefono.setText("");
        vista.txtFecha.setDate(null);
    }
    
    public void Mostrar(JTable tabla) {
    Connection conexion = Conexion.getConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"D.U.I", "Correo electronico", "Nombre", "Apellido", "Imagen", "Fecha de nacimiento", "Telefono"});
    
    try {
        // Consulta a ejecutar
        String query = "SELECT \n" +
                       "    Empleado.Dui_empleado,\n" +
                       "    Usuario.CorreoElectronico,\n" +
                       "    Empleado.Nombre,\n" +
                       "    Empleado.Apellido,\n" +
                       "    Empleado.ImagenEmpleado,\n" +
                       "    Empleado.FechaNacimiento,\n" +
                       "    Empleado.Telefono\n" +
                       "FROM \n" +
                       "    Usuario\n" +
                       "INNER JOIN \n" +
                       "    Empleado \n" +
                       "ON \n" +
                       "    Usuario.UUID_usuario = Empleado.UUID_usuario";
        
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery(query);
        
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getString("Dui_empleado"),
                rs.getString("CorreoElectronico"),
                rs.getString("Nombre"),
                rs.getString("Apellido"), 
                rs.getString("ImagenEmpleado"), 
                rs.getString("FechaNacimiento"), 
                rs.getString("Telefono"), 
                
            });
        }
        tabla.setModel(modelo);
        
    } catch (Exception e) {
        System.out.println("Este es el error en el modelo, método mostrar: " + e);
    }
}

    //funcion que carga los datos en la tabla
    public void cargarDatosTabla(frmUsuarios vista) {
    int filaSeleccionada = vista.dtgempleado.getSelectedRow();
    
    // Asigna los datos de la tabla a cada respectivo input cuando se le da clic a una fila
    if (filaSeleccionada != -1) {
        String DuiTb = vista.dtgempleado.getValueAt(filaSeleccionada, 0).toString();
        String CorreoTb = vista.dtgempleado.getValueAt(filaSeleccionada, 1).toString();
        String NombreTb = vista.dtgempleado.getValueAt(filaSeleccionada, 2).toString();
        String ApellidoTb = vista.dtgempleado.getValueAt(filaSeleccionada, 3).toString();
        String ImagenTb = vista.dtgempleado.getValueAt(filaSeleccionada, 4).toString();
        String FechaTb = vista.dtgempleado.getValueAt(filaSeleccionada, 5).toString();
        String TelefonoTb = vista.dtgempleado.getValueAt(filaSeleccionada, 6).toString();
        
        // Asigna los datos a los campos de la vista
        vista.txtdui.setText(DuiTb);
        vista.txtnombre.setText(NombreTb);
        vista.txtapellido.setText(ApellidoTb);
        vista.txtImagenUrl.setText(ImagenTb);
        vista.txtTelefono.setText(TelefonoTb);
        
        if (FechaTb != null && !FechaTb.isEmpty()) {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date fechaConvertida = formatoFecha.parse(FechaTb);
                vista.txtFecha.setDate(fechaConvertida);
            } catch (java.text.ParseException ex) {
                Logger.getLogger(mdlEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            vista.txtFecha.setDate(null);
        }
    }
}
}