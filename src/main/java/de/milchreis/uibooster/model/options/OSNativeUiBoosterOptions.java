package de.milchreis.uibooster.model.options;

import com.formdev.flatlaf.FlatDarculaLaf;
import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.lang.reflect.Constructor;

public class OSNativeUiBoosterOptions extends UiBoosterOptions {

    /**
     * Work around the UIManager to maintain options hierarchy and not
     * make a unique case for OS Native options directly in
     * the UiBooster constructor
     */
    public static BasicLookAndFeel OSNativeLookAndFeel() {
        try {
            String className = UIManager.getSystemLookAndFeelClassName();
            Class<?> klass = Class.forName(className);
            Constructor<?> constr = klass.getConstructor();
            return (BasicLookAndFeel) constr.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
            return new FlatDarculaLaf();
        }
    }

    public OSNativeUiBoosterOptions() {
        this(defaultIconPath);
    }

    public OSNativeUiBoosterOptions(String iconPath) {
        super(OSNativeLookAndFeel(), iconPath, defaultLoadingImage);
    }

}
