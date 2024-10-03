/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas.elemetos;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jaimew
 */
public class RadialGradientPanelRedondeado1 extends JPanel {
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtén el tamaño del panel
        int width = getWidth();
        int height = getHeight();

        // Define los colores y las posiciones (offsets) del degradado
        float[] dist = {0.0f, 1.0f}; // Posiciones: 0% y 100%
        Color[] colors = {
            new Color(39, 37, 113, 255), // Color #272571
            new Color(0, 0, 0, 255)      // Negro con 100% de opacidad
        };

        // Crea el degradado radial
        RadialGradientPaint rgp = new RadialGradientPaint(
   new Point2D.Double(width / 2.0, height / 2.0), // Centro del degradado (centro del panel)
            (float)Math.max(width, height),                // Radio del degradado mayor al tamaño del panel
            dist,                                          // Posiciones de los colores
            colors                                    // Colores del degradado
        );

        // Aplica el degradado como pintura
        g2d.setPaint(rgp);

        // Crea una forma redondeada
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width, height, 50, 50);

        // Establece la forma recortada para el panel
        g2d.clip(roundedRectangle);

        // Dibuja el fondo del panel con la forma redondeada
        g2d.fillRect(0, 0, width, height);
    }

    public static void main(String[] args) {
          JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Crea una instancia del panel con el degradado
        RadialGradientPanelRedondeado panel = new RadialGradientPanelRedondeado();
        frame.add(panel);

        frame.setVisible(true);
    }
}
