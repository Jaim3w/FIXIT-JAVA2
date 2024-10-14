package main;

import Vistas.Loginjava;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;

public class main {

    public static void main(String[] args) {
        // Instalar el LookAndFeel FlatIntelliJLaf por defecto
        try {
            FlatMacDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ejecutar en el hilo de eventos de la interfaz gráfica (AWT Event Dispatch Thread)
        EventQueue.invokeLater(() -> {
            // Crear el formulario de registro, lo que también inicializa todos sus componentes
            Loginjava.initLogin();
        });
    }
}
