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

    public WindowSetting hide() {
        this.visible = false;
        return this;
    }

    public WindowSetting setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public WindowSetting setUndecorated() {
        this.undecorated = true;
        return this;
    }

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

    public FormBuilder save() {
        return formBuilder;
    }
}
