package Vistas.elementosTwo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class WhiteRoundedPanelF extends JPanel {

    public WhiteRoundedPanelF() {
        // Establecer el panel como no opaco para que los bordes sean redondeados
        setOpaque(false); // Importante para permitir transparencia en los bordes
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtén el tamaño del panel
        int width = getWidth();
        int height = getHeight();

        // Establecer el color de fondo a blanco
        g2d.setColor(Color.WHITE);

        // Crear una forma redondeada (ovalada)
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width, height, 50, 50);

        // Establecer la forma recortada para el panel
        g2d.clip(roundedRectangle);

        // Dibuja el fondo del panel con la forma redondeada
        g2d.fillRect(0, 0, width, height);
    }
}
