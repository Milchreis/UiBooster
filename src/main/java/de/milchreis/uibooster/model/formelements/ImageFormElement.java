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

public class ImageFormElement extends FormElement {

    private final ImagePanel imagePanel;

    public ImageFormElement(String label, String imagePath, boolean centered) {
        super(label);
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
    public Object getValue() {
        return imagePanel;
    }

    @Override
    public void setValue(Object o) {

        if (o instanceof String)
            imagePanel.setImage(getBufferedImage(o.toString()));

        else if (o instanceof File)
            imagePanel.setImage(getBufferedImage(((File) o).getAbsolutePath()));

        else
            throw new IllegalArgumentException("Argument has to be the type String or File");
    }

    private ImagePanel createImagePanel(String path, boolean centered) {
        return new ImagePanel(getBufferedImage(path), centered);
    }

    private BufferedImage getBufferedImage(String path) {
        try (InputStream inputStream = new FileInputStream(path)) {
            return ImageIO.read(inputStream);

        } catch (IOException e) {
            throw new IllegalArgumentException("The given file is not ok", e);
        }
    }

}