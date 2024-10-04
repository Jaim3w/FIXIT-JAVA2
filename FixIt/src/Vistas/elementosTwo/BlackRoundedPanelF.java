package Vistas.elementosTwo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class BlackRoundedPanelF extends JPanel {

    public BlackRoundedPanelF() {
        // Hacer el panel no opaco para manejar la transparencia en los bordes
        setOpaque(false); // Permite el dibujo personalizado sin un fondo predeterminado
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtener el tamaño del panel
        int width = getWidth();
        int height = getHeight();

        // Establecer el color de fondo negro
        g2d.setColor(Color.BLACK);

        // Crear una forma redondeada (ovalada)
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width, height, 50, 50);

        // Establecer la forma recortada para el panel
        g2d.clip(roundedRectangle);

        // Dibujar el fondo del panel con la forma redondeada en negro
        g2d.fillRect(0, 0, width, height);
    }
}
