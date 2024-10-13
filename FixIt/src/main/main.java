package main;

import Vistas.frmRegistrarse;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;
import java.awt.Font;

public class main {

    public static void main(String[] args) {
        // Instalar el LookAndFeel FlatMacLightLaf
        try {
            // Cargar el LookAndFeel y aplicar las propiedades desde el archivo
            FlatMacLightLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar un estilo de fuente minimalista
        Font minimalFont = new Font("SansSerif", Font.PLAIN, 14); // Fuente sencilla y moderna
        UIManager.put("defaultFont", minimalFont); // Aplicar la fuente a todos los componentes

        // Ejecutar en el hilo de eventos de la interfaz gráfica (AWT Event Dispatch Thread)
        java.awt.EventQueue.invokeLater(() -> {
            // Crear el formulario de registro, lo que también inicializa todos sus componentes
            frmRegistrarse.initfrmRegistrarse();
        });
    }
}

