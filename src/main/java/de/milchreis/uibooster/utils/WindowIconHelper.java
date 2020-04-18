package de.milchreis.uibooster.utils;

import de.milchreis.uibooster.components.WaitingDialog;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WindowIconHelper {

    public static ImageIcon getIcon(String iconPath) {
        URL resource = WaitingDialog.class.getResource(iconPath);
        if (resource == null)
            return new ImageIcon(iconPath);
        else
            return new ImageIcon(resource);
    }

    public static void applyWindowIcon(String iconPath, Window component) {
        try {
            component.setIconImage(getIcon(iconPath).getImage());
        } catch (Exception e) {
            System.out.println("Warn: could not load " + iconPath);
        }
    }
}
