package Vistas.elemetos;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Define los colores del degradado
        Color color1 = new Color(255, 215, 0);  // Amarillo dorado
        Color color2 = Color.BLACK;             // Negro

        // Crea el degradado de izquierda a derecha
        int width = getWidth();
        int height = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);

        // Aplica el degradado
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GradientPanel panel = new GradientPanel();
        
        // Configurar el JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(panel);
        frame.setVisible(true);
    }
}
