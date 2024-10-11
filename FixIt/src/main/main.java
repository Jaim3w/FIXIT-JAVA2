package main;

import Vistas.dashboard;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class main {

    public static void main(String[] args) {
        // Instalar el LookAndFeel FlatMacDarkLaf
        FlatMacDarkLaf.install();

        // Ejecutar en el hilo de eventos de la interfaz gráfica (AWT Event Dispatch Thread)
        java.awt.EventQueue.invokeLater(() -> {
            // Crear el dashboard, lo que también inicializa todos sus componentes
            dashboard db = new dashboard();
            db.setVisible(true); // Mostrar el dashboard después de la inicialización
        });
    }
}
