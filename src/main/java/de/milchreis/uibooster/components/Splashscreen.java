package de.milchreis.uibooster.components;

import de.milchreis.uibooster.utils.WindowIconHelper;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Splashscreen {

    private final JFrame window;
    private long startTime;
    private int minimumMilliseconds;
    private ScheduledExecutorService scheduledExecutorService;

    public Splashscreen(String imagePath, String windowIcon) {
        this(imagePath, -1, 1, windowIcon);
    }

    public Splashscreen(String imagePath, int lifeTime, float opacity, String windowIcon) {

        window = new JFrame();

        ImageIcon image = new ImageIcon(imagePath);
        window.getContentPane().add(new JLabel("", image, SwingConstants.CENTER));

        window.setUndecorated(true);
        window.setBackground(new Color(0, 0, 0, 0));
        window.setOpacity(opacity);

        WindowIconHelper.applyWindowIcon(windowIcon, window);

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds((int) ((screenSize.getWidth() - image.getIconWidth()) / 2),
                (int) ((screenSize.getHeight() - image.getIconHeight()) / 2),
                image.getIconWidth(), image.getIconHeight());

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            long elapsedTime = System.currentTimeMillis() - startTime;

            if (minimumMilliseconds > 0 && elapsedTime > minimumMilliseconds) {
                window.setVisible(false);
                scheduledExecutorService.shutdown();
            }

        }, 0, 100, TimeUnit.MILLISECONDS);

        show(lifeTime);
    }

    public void show(int minimumMilliseconds) {
        window.setVisible(true);
        this.minimumMilliseconds = minimumMilliseconds;
        startTime = System.currentTimeMillis();
    }

    public void hide() {
        window.setVisible(false);
        scheduledExecutorService.shutdown();
    }

}
