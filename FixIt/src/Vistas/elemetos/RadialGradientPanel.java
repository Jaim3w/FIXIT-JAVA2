package Vistas.elemetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RadialGradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtén el tamaño del panel
        int width = getWidth();
        int height = getHeight();

        // Define los colores y las posiciones (offsets) del degradado
        float[] dist = {0.0f, 1.0f}; // Posiciones: 0% y 100%
        Color[] colors = {
            new Color(0, 0, 0, 235), // Negro con 40% de opacidad
            new Color(0, 0, 0, 255)  // Negro con 100% de opacidad
        };

        // Crea el degradado radial, con el centro en la esquina superior izquierda
        // y extendiéndose hacia la esquina inferior derecha
        RadialGradientPaint rgp = new RadialGradientPaint(
            new Point2D.Double(0, 0),              // Centro del degradado (esquina superior izquierda)
            (float)Math.sqrt(width * width + height * height), // Radio del degradado (diagonal del panel)
            dist,                                   // Posiciones de los colores
            colors                                  // Colores del degradado
        );

        // Aplica el degradado como pintura
        g2d.setPaint(rgp);
        g2d.fillRect(0, 0, width, height);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Crea una instancia del panel con el degradado
        RadialGradientPanel panel = new RadialGradientPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}

