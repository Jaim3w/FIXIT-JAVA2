package main;

import Vistas.dashboard;
import com.formdev.flatlaf.FlatLightLaf;

public class main {
    
    public static void main(String[] args) {
        // Instalar el LookAndFeel FlatLightLaf
        FlatLightLaf.install();

        // Ejecutar en el hilo de eventos de la interfaz gráfica (AWT Event Dispatch Thread)
        java.awt.EventQueue.invokeLater(() -> {
            // Crear una instancia del dashboard y hacerlo visible
            dashboard db = new dashboard();
            db.setVisible(true);
        });
    }
}