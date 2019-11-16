package de.milchreis.uibooster.components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrayMenu {

    private TrayIcon tray;

    public TrayMenu(String title, String iconPath, Runnable onIconClicked) {

        if (SystemTray.isSupported()) {
            Image image = Toolkit.getDefaultToolkit().getImage(iconPath);

            tray = new TrayIcon(image, title);
            tray.setImageAutoSize(true);

            tray.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getButton() == MouseEvent.BUTTON1) {
                        if(onIconClicked != null) {
                            onIconClicked.run();
                        }
                    }
                }
            });

            try {
                SystemTray.getSystemTray().add(tray);
            } catch (AWTException e) {
                System.err.println(e);
            }
        }
    }

    public TrayMenu withPopupMenu() {
        PopupMenu popup = new PopupMenu();
        tray.setPopupMenu(popup);
        return this;
    }

    public TrayMenu addMenu(String title, Runnable onItemClicked) {

        if(tray.getPopupMenu() == null) {
            withPopupMenu();
        }

        MenuItem item = new MenuItem(title);
        item.addActionListener(e -> onItemClicked.run());
        tray.getPopupMenu().add(item);

        return this;
    }

}
