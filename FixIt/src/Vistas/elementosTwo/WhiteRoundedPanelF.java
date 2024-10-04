package Vistas.elementosTwo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class WhiteRoundedPanelF extends JPanel {

    public WhiteRoundedPanelF() {
        // Establecer el color de fondo del panel según el LookAndFeel activo
        setOpaque(false); // Importante para que el color de fondo sea transparente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtén el tamaño del panel
        int width = getWidth();
        int height = getHeight();

        // Obtén el color de fondo dinámicamente en lugar de usar un color fijo
        Color backgroundColor = getBackground(); // Obtiene el color de fondo según el LookAndFeel
        g2d.setColor(backgroundColor);

        // Crea una forma redondeada (ovalada)
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width, height, 50, 50);

        // Establece la forma recortada para el panel
        g2d.clip(roundedRectangle);

        // Dibuja el fondo del panel con la forma redondeada
        g2d.fillRect(0, 0, width, height);
    }
}