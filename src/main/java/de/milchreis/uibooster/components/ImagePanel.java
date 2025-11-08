package de.milchreis.uibooster.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    private BufferedImage image;
    private final boolean centered;

    public ImagePanel(BufferedImage image) {
        this(image, false);
    }

    public ImagePanel(BufferedImage image, boolean centered) {
        this.image = image;
        this.centered = centered;
        this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = this.getWidth();
        int h = this.getHeight();

        if (!centered) {
            g.drawImage(image, 0, 0, w, h, this);

        } else {
            int x = (w / 2) - (image.getWidth() / 2);
            int y = (h / 2) - (image.getHeight() / 2);
            g.drawImage(image, x, y, this);
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        revalidate();
        repaint();
    }

}
