package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.ImagePanel;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageFormElement extends FormElement<String> {

    private final ImagePanel imagePanel;
    private String imagePath;

    public ImageFormElement(String label, String imagePath, boolean centered) {
        super(label);
        this.imagePath = imagePath;
        imagePanel = createImagePanel(imagePath, centered);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener formElementChangeListener) {
        return imagePanel;
    }

    @Override
    public void setEnabled(boolean b) {
        imagePanel.setEnabled(b);
    }

    @Override
    public String getValue() {
        return imagePath;
    }

    @Override
    public void setValue(String imagePath) {

        if (imagePath != null) {
            this.imagePath = imagePath;
            imagePanel.setImage(getBufferedImage(imagePath));
        } else
            throw new IllegalArgumentException("The value is null and can not set");
    }

    public void setValue(File imagePath) {

        if (imagePath == null)
            throw new IllegalArgumentException("The value is null and can not set");

        this.imagePath = imagePath.getAbsolutePath();
        imagePanel.setImage(getBufferedImage(imagePath.getAbsolutePath()));
    }

    private ImagePanel createImagePanel(String path, boolean centered) {
        return new ImagePanel(getBufferedImage(path), centered);
    }

    private BufferedImage getBufferedImage(String path) {
        final Path imagePath = Paths.get(path);

        try {
            if (Files.exists(imagePath)) {
                try (InputStream inputStream = Files.newInputStream(imagePath)) {
                    return ImageIO.read(inputStream);
                }
            } else {
                URL resource = ImageFormElement.class.getResource(path);
                ImageIcon icon = resource == null ? new ImageIcon(path) : new ImageIcon(resource);
                return getBufferedImageFromIcon(icon);

            }
        } catch (Exception e) {
            throw new IllegalArgumentException("The given file could not found or load", e);
        }
    }

    private static BufferedImage getBufferedImageFromIcon(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return bi;
    }

}
