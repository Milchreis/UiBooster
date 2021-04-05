package de.milchreis.uibooster.model.options;

import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.plaf.metal.MetalLookAndFeel;

public class SwingUiBoosterOptions extends UiBoosterOptions {

    public SwingUiBoosterOptions() {
        super(new MetalLookAndFeel(), defaultIconPath, defaultLoadingImage);
    }
}
