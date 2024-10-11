package Vistas.elementosTwo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class TransparenteRoundedPanel extends JPanel {

    public TransparenteRoundedPanel() {
        // Hacer el panel no opaco para permitir la transparencia
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtener el tamaño del panel
        int width = getWidth();
        int height = getHeight();

        // Crear una forma redondeada (ovalada)
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width, height, 50, 50);

        // Establecer la forma recortada para el panel
        g2d.clip(roundedRectangle);
        
        // No dibujar el fondo para permitir que sea completamente transparente
    }
}
