package de.milchreis.uibooster.model;

import de.milchreis.uibooster.utils.WindowIconHelper;

import java.awt.*;

public class ListElement {

    private final String title;
    private final String message;
    private final Image image;

    public ListElement(String title, String message) {
        this.title = title;
        this.message = message;
        this.image = null;
    }

    public ListElement(String title, String message, Image image) {
        this.title = title;
        this.message = message;
        this.image = image;
    }

    public ListElement(String title, String message, String imagePath) {
        this.title = title;
        this.message = message;
        this.image = WindowIconHelper.getIcon(imagePath).getImage();
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "ListElement{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", image=" + image +
                '}';
    }
}
