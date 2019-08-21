package de.milchreis.uibooster.model;

public class UiBoosterOptions {

    private boolean useNativeLookAndFeel;

    public UiBoosterOptions(boolean useNativeLookAndFeel) {
        this.useNativeLookAndFeel = useNativeLookAndFeel;
    }

    public boolean isUseNativeLookAndFeel() {
        return useNativeLookAndFeel;
    }

    public void setUseNativeLookAndFeel(boolean useNativeLookAndFeel) {
        this.useNativeLookAndFeel = useNativeLookAndFeel;
    }
}
