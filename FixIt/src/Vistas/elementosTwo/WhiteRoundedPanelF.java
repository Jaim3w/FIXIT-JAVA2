package Vistas.elementosTwo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WhiteRoundedPanelF extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtén el tamaño del panel
        int width = getWidth();
        int height = getHeight();

        // Establece el color de fondo como blanco
        g2d.setColor(Color.WHITE);

        // Crea una forma redondeada (ovalada)
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

        // Crea una instancia del panel redondeado blanco
        WhiteRoundedPanelF panel = new WhiteRoundedPanelF();
        frame.add(panel);

        frame.setVisible(true);
    }
}
