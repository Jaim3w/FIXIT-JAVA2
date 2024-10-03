package Vistas.elemetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class RoundedWhitePanel extends JPanel {
    private int arcWidth;
    private int arcHeight;

    public RoundedWhitePanel(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        g2d.setColor(Color.WHITE); 
        g2d.fill(roundedRect);

        g2d.setColor(Color.BLACK); 
        g2d.draw(roundedRect);
    }
}

