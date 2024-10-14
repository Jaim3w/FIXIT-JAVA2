package main;

import Vistas.Loginjava;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;
import javax.swing.UIManager;

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
            // Crear el formulario de registro
            Loginjava.initLogin();
        });
    }
}


