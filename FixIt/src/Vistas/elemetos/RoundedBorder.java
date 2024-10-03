
package Vistas.elemetos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
    private Color color;
    private int thickness = 2; // Grosor del borde
    private int radius = 20; // Radio de las esquinas
    private int padding = 5;

    public RoundedBorder(Color color, int thickness, int radius) {
        this.color = color;
        this.thickness = thickness;
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawRoundRect(x + padding, y + padding, width - padding * 2, height - padding * 2, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness + padding, thickness + padding, thickness + padding, thickness + padding);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = insets.top = insets.bottom = thickness + padding;
        return insets;
    }
}