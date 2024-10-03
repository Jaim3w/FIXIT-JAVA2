
package Vistas.elemetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import javax.swing.JComponent;

public class RippleEffect {

    private JComponent component;
    private Color rippleColor = new Color(220, 220, 220);
    private int rippleRadius = 0;
    private int rippleX;
    private int rippleY;

    public RippleEffect(JComponent component) {
        this.component = component;
        this.component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                rippleX = e.getX();
                rippleY = e.getY();
                rippleRadius = 0;
                component.repaint();
            }
        });
    }

    public void setRippleColor(Color rippleColor) {
        this.rippleColor = rippleColor;
    }

    public Color getRippleColor() {
        return rippleColor;
    }

    public void reder(Graphics g, Area area) {
        if (rippleRadius > component.getWidth()) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(rippleColor);
        g2.fillOval(rippleX - rippleRadius, rippleY - rippleRadius, rippleRadius * 2, rippleRadius * 2);
        g2.dispose();
        rippleRadius += 3;  // Controla la velocidad del efecto
        component.repaint();
    }

    void render(Graphics grphcs, Area area) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
