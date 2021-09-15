package de.milchreis.uibooster.utils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class FontHelper {


    public static void setFontInUIManager(Font font) {
        if(font == null) return;

        final FontUIResource fontUIResource = new FontUIResource(font);

        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, fontUIResource);
            }
        }
    }
}
