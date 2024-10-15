package main;

import Vistas.Loginjava;
import Vistas.frmRegistrarse;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.UIManager;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class main {

    public static void main(String[] args) {
        
        // Instalar el LookAndFeel FlatMacDarkLaf
        try {
            FlatMacDarkLaf.setup();
            
            // Personalizar los colores
            UIManager.put("Component.arc", 999);  // Redondeo para todos los componentes
            UIManager.put("Button.arc", 999);
            UIManager.put("Component.focusWidth", 1);  // Ancho de foco
            UIManager.put("Component.focusColor", java.awt.Color.WHITE);  // Color del borde del foco

            // Cambiar colores de selección y otros acentos a blanco
            UIManager.put("TextComponent.selectionBackground", java.awt.Color.WHITE);
            UIManager.put("TextComponent.selectionForeground", java.awt.Color.BLACK); // Texto sobre selección blanca

            UIManager.put("Button.background", java.awt.Color.DARK_GRAY);
            UIManager.put("Button.foreground", java.awt.Color.WHITE);
            UIManager.put("Button.focusedBackground", java.awt.Color.WHITE);

            UIManager.put("ComboBox.selectionBackground", java.awt.Color.WHITE);
            UIManager.put("ComboBox.selectionForeground", java.awt.Color.BLACK);
            UIManager.put("ComboBox.buttonBackground", java.awt.Color.DARK_GRAY);
            UIManager.put("ComboBox.buttonForeground", java.awt.Color.WHITE);
            
            UIManager.put("CheckBox.icon.checkedColor", java.awt.Color.WHITE);  // Color blanco para el ícono de checkbox
            UIManager.put("CheckBox.icon.focusedColor", java.awt.Color.WHITE);
            UIManager.put("CheckBox.icon.hoverBorderColor", java.awt.Color.WHITE);

            UIManager.put("RadioButton.icon.checkedColor", java.awt.Color.WHITE); // Ícono de radio button en blanco
            UIManager.put("RadioButton.icon.focusedColor", java.awt.Color.WHITE);

            // Tablas y listas
            UIManager.put("Table.selectionBackground", java.awt.Color.WHITE);
            UIManager.put("Table.selectionForeground", java.awt.Color.BLACK);
            UIManager.put("List.selectionBackground", java.awt.Color.WHITE);
            UIManager.put("List.selectionForeground", java.awt.Color.BLACK);
            
            // Alternating rows (filas alternas en tablas)
            UIManager.put("Table.alternateRowColor", new java.awt.Color(48, 48, 48));  // Color gris oscuro para las filas alternas
            
            // Selección redondeada en las tablas
            UIManager.put("Table.showRoundedSelection", true); // Activar selección redondeada

        } catch (Exception e) {
            e.printStackTrace();
        }
  // Ejecutar en el hilo de eventos de la interfaz gráfica (AWT Event Dispatch Thread)
        EventQueue.invokeLater(() -> {
           if (esPrimerUso()) {
        System.out.println("Es el primer uso. Mostrando formulario de registro.");
        frmRegistrarse.initfrmRegistrarse(); // Formulario de registro
    } else {
        System.out.println("No es el primer uso. Mostrando pantalla de login.");
        Loginjava.initLogin(); // Pantalla de inicio de sesión
    }
        });
    }

    // Método para verificar si es el primer uso
    public static boolean esPrimerUso() {
    Properties propiedades = new Properties();
    File archivo = new File("config.properties"); // Cambia a la ruta absoluta si es necesario

    System.out.println("Intentando leer el archivo de propiedades en: " + archivo.getAbsolutePath());

    try (FileInputStream fis = new FileInputStream(archivo)) {
        propiedades.load(fis);
        String primerUsoStr = propiedades.getProperty("primerUso", "true");
        System.out.println("Valor leído de 'primerUso': " + primerUsoStr);
        return Boolean.parseBoolean(primerUsoStr);
    } catch (IOException e) {
        System.out.println("Error al leer el archivo de propiedades. Asumiendo que es primer uso.");
        return true; // Si ocurre un error, asumimos que es el primer uso
    }
}

    // Método para marcar que el registro se ha completado
   public static void establecerPrimerUso(boolean primerUso) {
    Properties propiedades = new Properties();
    File archivo = new File("config.properties"); // Cambia a la ruta absoluta si es necesario
    
    // Leer propiedades existentes
    try (FileInputStream fis = new FileInputStream(archivo)) {
        propiedades.load(fis);
    } catch (IOException e) {
        System.out.println("Error al leer el archivo de propiedades. Se creará uno nuevo.");
    }
    
    // Cambiar el valor de "primerUso"
    propiedades.setProperty("primerUso", Boolean.toString(primerUso));
    
    // Guardar las propiedades
    try (FileOutputStream fos = new FileOutputStream(archivo)) {
        propiedades.store(fos, null);
        System.out.println("El archivo de propiedades se actualizó correctamente.");
    } catch (IOException e) {
        System.out.println("Error al guardar el archivo de propiedades.");
        e.printStackTrace();
    }
}
    
    private static boolean obtenerPrimerUso() {
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            propiedades.load(fis);
            return Boolean.parseBoolean(propiedades.getProperty("primerUso", "true"));
        } catch (IOException e) {
            e.printStackTrace();
            return true; // Si hay un error, consideramos que es el primer uso
        }
    }
}