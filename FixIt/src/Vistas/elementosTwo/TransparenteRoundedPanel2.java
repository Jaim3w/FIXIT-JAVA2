/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas.elementosTwo;

/**
 *
 * @author Jaimew
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TransparenteRoundedPanel2 extends JPanel {
    private BufferedImage backgroundImage;

    public TransparenteRoundedPanel2() {
        setOpaque(false); // Hace que el panel no sea opaco
        try {
            // Carga la imagen de fondo
            backgroundImage = ImageIO.read(getClass().getResource("/Vistas/Imagenes/card.png")); // Cambia esto por la ruta de tu imagen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Dibuja la imagen
        }
    }
}
