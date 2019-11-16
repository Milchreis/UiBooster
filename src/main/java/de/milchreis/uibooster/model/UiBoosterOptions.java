package de.milchreis.uibooster.model;

public class UiBoosterOptions {

    public enum Theme {
        DARK_THEME, SWING, OS_NATIVE
    }

    private Theme theme;

    public UiBoosterOptions(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
