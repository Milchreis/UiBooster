package de.milchreis.uibooster.model;

public class UiBoosterOptions {

    public enum Theme {
        DARK_THEME, SWING, OS_NATIVE
    }

    private Theme theme = Theme.DARK_THEME;
    private String iconPath = "/icon.png";

    public UiBoosterOptions() {
    }

    public UiBoosterOptions(String iconPath) {
        this.iconPath = iconPath;
    }

    public UiBoosterOptions(Theme theme) {
        this.theme = theme;
    }

    public UiBoosterOptions(Theme theme, String iconPath) {
        this.theme = theme;
        this.iconPath = iconPath;
    }

    public String getIconPath() {
        return iconPath;
    }

    public Theme getTheme() {
        return theme;
    }
}
