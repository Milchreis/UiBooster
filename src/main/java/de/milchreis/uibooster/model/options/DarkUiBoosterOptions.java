package de.milchreis.uibooster.model.options;

import com.bulenkov.darcula.DarculaLaf;
import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;

public class DarkUiBoosterOptions extends UiBoosterOptions {
    public static final String defaultLoadingImage = "/loading-75_darcula.gif";

    public DarkUiBoosterOptions() {
        super(new DarculaLaf(), defaultIconPath, defaultLoadingImage);

        // Little hack to start working on linux
        UIManager.getFont("Label.font");
    }
}
