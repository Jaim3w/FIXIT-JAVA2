package main;

import Vistas.frmRegistrarse;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class main {

    public static void main(String[] args) {
        // Instalar el LookAndFeel FlatIntelliJLaf por defecto
        try {
            FlatIntelliJLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar un estilo de fuente minimalista
        Font minimalFont = new Font("SansSerif", Font.PLAIN, 14); // Fuente sencilla y moderna
        UIManager.put("defaultFont", minimalFont); // Aplicar la fuente a todos los componentes

        // Ejecutar en el hilo de eventos de la interfaz gráfica (AWT Event Dispatch Thread)
        EventQueue.invokeLater(() -> {
            // Crear el formulario de registro, lo que también inicializa todos sus componentes
            frmRegistrarse.initfrmRegistrarse();
        });
    }

    private static void changeThemes(boolean dark) {
        if (FlatLaf.isLafDark() != dark) {
            EventQueue.invokeLater(() -> {
                FlatAnimatedLafChange.showSnapshot();
                if (!dark) {
                    // Configura el tema claro
                    FlatIntelliJLaf.setup();

                    // Colores para el tema claro
                    UIManager.put("Component.accentColor", new Color(255, 223, 0)); // Color amarillo
                    UIManager.put("Table.alternateRowColor", new Color(255, 255, 224)); // Amarillo pálido
                    UIManager.put("Table.selectionBackground", new Color(255, 223, 0)); // Fondo amarillo en selección
                    UIManager.put("Table.selectionForeground", Color.BLACK); // Texto negro en selección
                    UIManager.put("Component.unselectedForeground", new Color(169, 169, 169)); // Texto gris cuando no está seleccionado
                    UIManager.put("Component.selectionBackground", new Color(255, 223, 0, 128)); // Fondo amarillo opaco en selección

                    // Cambiar color de borde de enfoque y seleccionado
                    UIManager.put("Component.focusedBorderColor", new Color(255, 223, 0, 128)); // Borde de enfoque amarillo opaco
                    UIManager.put("Component.selectedBorderColor", new Color(255, 223, 0, 128)); // Borde seleccionado amarillo opaco
                    UIManager.put("Label.foreground", Color.BLACK); // Texto de JLabel negro en tema claro

                } else {
                    // Configura el tema oscuro
                    FlatDarculaLaf.setup();

                    // Colores para el tema oscuro
                    UIManager.put("Component.accentColor", new Color(255, 223, 0)); // Color amarillo
                    UIManager.put("Table.alternateRowColor", new Color(50, 50, 50)); // Un color oscuro para alternar
                    UIManager.put("Table.selectionBackground", new Color(255, 223, 0)); // Fondo amarillo en selección
                    UIManager.put("Table.selectionForeground", Color.WHITE); // Texto blanco en selección
                    UIManager.put("Component.unselectedForeground", new Color(200, 200, 200)); // Texto gris claro cuando no está seleccionado
                    UIManager.put("Component.selectionBackground", new Color(255, 223, 0, 128)); // Fondo amarillo opaco en selección

                    // Cambiar color de borde de enfoque y seleccionado
                    UIManager.put("Component.focusedBorderColor", new Color(255, 223, 0, 128)); // Borde de enfoque amarillo opaco
                    UIManager.put("Component.selectedBorderColor", new Color(255, 223, 0, 128)); // Borde seleccionado amarillo opaco
                    UIManager.put("Label.foreground", Color.WHITE); // Texto de JLabel blanco en tema oscuro
                }

                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            });
        }
    }
}
