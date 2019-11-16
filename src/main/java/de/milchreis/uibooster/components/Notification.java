package de.milchreis.uibooster.components;

import java.awt.*;

public class Notification {

    public Notification() {
        if (!SystemTray.isSupported())
            throw new IllegalStateException("System tray not supported for your OS!");
    }

    public void display(String title, String message) {

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Notification");
        trayIcon.setImageAutoSize(true);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        trayIcon.displayMessage(title, message, TrayIcon.MessageType.NONE);
    }

}
