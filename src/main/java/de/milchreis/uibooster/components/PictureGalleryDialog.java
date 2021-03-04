package de.milchreis.uibooster.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;

public class PictureGalleryDialog {

    public static PictureGalleryDialog showDialog(String title, String iconPath, List<File> images) {
        return new PictureGalleryDialog(title, iconPath, images);
    }

    private final JDialog window;
    private final ImagePanel imagePanel;
    private final List<File> images;
    private int imageIndex = -1;

    public PictureGalleryDialog(String title, String iconPath, List<File> images) {
        this.images = images;

        window = new JDialog((Dialog) null, title, true);
        window.setTitle(title);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        applyWindowIcon(iconPath, window);

        window.setLayout(new BorderLayout());

        imagePanel = new ImagePanel(null);
        window.add(imagePanel, BorderLayout.CENTER);

        if (images.size() > 1) {
            JPanel buttonPanel = new JPanel(new FlowLayout());

            JButton previous = new JButton(" < ");
            JButton next = new JButton(" > ");
            updateButtonState(previous, next, hasNextImage(), hasPreviousImage());

            next.addActionListener(l -> {
                showNextImage();
                updateButtonState(previous, next, hasNextImage(), hasPreviousImage());
            });

            previous.addActionListener(l -> {
                showPreviousImage();
                updateButtonState(next, previous, hasPreviousImage(), hasNextImage());
            });

            buttonPanel.add(previous);
            buttonPanel.add(next);
            window.add(buttonPanel, BorderLayout.SOUTH);
        }

        showNextImage();
        window.setVisible(true);
    }

    private void updateButtonState(JButton previous, JButton next, boolean b, boolean b2) {
        next.setEnabled(b);
        previous.setEnabled(b2);
    }

    public void showPreviousImage() {
        if (!hasPreviousImage())
            return;

        imageIndex--;
        showImage();
    }


    public void showNextImage() {
        if (!hasNextImage())
            return;

        imageIndex++;
        showImage();
    }

    public void showImage() {
        try {
            BufferedImage image = ImageIO.read(images.get(imageIndex));

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int defaultWidth = (int) ((double) screenSize.width * 0.5);
            int defaultHeight = (int) ((double) screenSize.height * 0.5);

            defaultWidth = Math.min(image.getWidth(), defaultWidth);
            defaultHeight = Math.min(image.getHeight(), defaultHeight);

            imagePanel.setImage(image);

            Dimension panelSize = scaleWithRatio(image.getWidth(), image.getHeight(), defaultWidth, defaultHeight);
            imagePanel.setPreferredSize(panelSize);

            window.pack();
            window.setLocationRelativeTo(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean hasNextImage() {
        return imageIndex < images.size() - 1;
    }

    private boolean hasPreviousImage() {
        return imageIndex > 0;
    }

    private Dimension scaleWithRatio(int width, int height, int targetWidth, int targetHeight) {

        float ratio = ((float) height / (float) width);

        if (ratio <= 1) {
            return new Dimension(targetWidth, Math.round((float) targetWidth * ratio));
        } else {
            return new Dimension(Math.round((float) targetHeight / ratio), targetHeight);
        }
    }

}
