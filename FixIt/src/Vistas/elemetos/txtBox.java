
package Vistas.elemetos;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class txtBox extends JTextField {

    public txtBox() {
        super();
        try {
            // Configuración inicial
            setOpaque(false); // Hace el JTextField transparente
            setForeground(Color.WHITE); // Texto blanco
            setCaretColor(Color.WHITE); // Color del cursor
            setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Fuente personalizada más parecida

            // Ajustar el borde dorado con un tono más oscuro y redondeo similar al de la imagen
            setBorder(new RoundedBorder(new Color(0xD4AF37), 2, 15)); // Ajustar color y redondeo

        } catch (Exception e) {
            e.printStackTrace(); // Imprime la excepción en la consola para análisis
        }
    }
}
