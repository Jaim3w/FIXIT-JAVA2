/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas.elemetos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

public class TxtBoxContrasenia extends JPasswordField { // Clase pública

    public TxtBoxContrasenia() {
        super();
        try {
            // Eliminar el texto por defecto
            setText(""); // Asegurarse de que no haya texto predeterminado

            // Configuración inicial del JPasswordField
            setOpaque(false); // Hace el JPasswordField transparente
            setForeground(new Color(0x0084A0)); // Texto con el color #0084A0
            setCaretColor(Color.BLACK); // Color del cursor de texto
            setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Fuente y tamaño del texto
            
            // Establecer el carácter para ocultar la contraseña
            setEchoChar('•'); // Caracter que reemplaza los dígitos

            // Ajustar el borde con esquinas redondeadas y color #0084A0
            setBorder(new RoundedBorder(new Color(0x0084A0), 2, 15)); // Color #0084A0 con borde redondeado

            // Configuraciones adicionales (puedes personalizar más si es necesario)
            setBackground(Color.BLACK); // Fondo negro
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Clase interna para crear un borde redondeado personalizado
    private static class RoundedBorder implements Border {
        private final Color color;
        private final int thickness;
        private final int radius;

        public RoundedBorder(Color color, int thickness, int radius) {
            this.color = color;
            this.thickness = thickness;
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.thickness + 1, this.thickness + 1, this.thickness + 1, this.thickness + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // Borde redondeado
        }
    }
}
