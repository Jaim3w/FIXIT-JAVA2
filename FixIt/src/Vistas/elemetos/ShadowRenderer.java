
package Vistas.elemetos;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShadowRenderer {

    private int size;
    private float opacity;
    private Color color;

    public ShadowRenderer(int size, float opacity, Color color) {
        this.size = size;
        this.opacity = opacity;
        this.color = color;
    }

    public BufferedImage createShadow(BufferedImage img) {
        int shadowSize = size * 2;
        int srcWidth = img.getWidth();
        int srcHeight = img.getHeight();
        int dstWidth = srcWidth + shadowSize;
        int dstHeight = srcHeight + shadowSize;

        BufferedImage shadow = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = shadow.createGraphics();

        g2.drawImage(img, size, size, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, opacity));
        g2.setColor(color);
        g2.fillRect(0, 0, dstWidth, dstHeight);
        g2.dispose();

        return shadow;
    }
}