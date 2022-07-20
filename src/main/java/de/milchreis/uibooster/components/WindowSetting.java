package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.FormBuilder;

public class WindowSetting {

    private boolean visible = true;

    private int width = 400;
    private int height = 200;

    private boolean undecorated = false;

    private int positionX = -1;
    private int positionY = -1;

    private final FormBuilder formBuilder;

    public WindowSetting(FormBuilder formBuilder) {
        this.formBuilder = formBuilder;
    }

    /**
     * Hides the window on start. It runs in the background.
     */
    public WindowSetting hide() {
        this.visible = false;
        return this;
    }

    /**
     * Sets the preferred window size in pixel.
     *
     * @param width  expects the width in pixel
     * @param height expects the height in pixel
     */
    public WindowSetting setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Hides the window titlebar if it's supported by the OS
     */
    public WindowSetting setUndecorated() {
        this.undecorated = true;
        return this;
    }

    /**
     * Sets the preferred position of the window on startup. The default position is centered on screen.
     *
     * @param positionX expects the pixels for the middle of the form on the x-axis (width-axis)
     * @param positionY expects the pixels for the middle of the form on the y-axis (height-axis)
     */
    public WindowSetting setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        return this;
    }

    boolean isVisible() {
        return visible;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    boolean isUndecorated() {
        return undecorated;
    }

    Integer getPositionX() {
        return positionX <= -1 ? null : positionX;
    }

    Integer getPositionY() {
        return positionY <= -1 ? null : positionY;
    }

    /**
     * Saves the changes and ends the window-settings.
     */
    public FormBuilder save() {
        return formBuilder;
    }
}
