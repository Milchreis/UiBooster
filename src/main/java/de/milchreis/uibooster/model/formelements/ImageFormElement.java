package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.ImagePanel;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
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
        try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {
            return ImageIO.read(inputStream);

        } catch (IOException e) {
            throw new IllegalArgumentException("The given file is not ok", e);
        }
    }

}
