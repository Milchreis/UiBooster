package de.milchreis.uibooster.model.options;

import com.formdev.flatlaf.FlatLightLaf;
import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;

public class LightUiBoosterOptions extends UiBoosterOptions {
    public static final String defaultLoadingImage = "/loading-75.gif";

    public LightUiBoosterOptions() {
        this(defaultIconPath);
    }

    public LightUiBoosterOptions(String iconPath) {
        super(new FlatLightLaf(), iconPath, defaultLoadingImage);

        // Little hack to start working on linux
        UIManager.getFont("Label.font");
    }
}
