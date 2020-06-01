package de.milchreis.uibooster.model;

import de.milchreis.uibooster.components.Form;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FormElement {

    protected String label;
    protected final int formIndex;
    protected Form.InputType inputType;

    public FormElement(String label, Form.InputType inputType, int formIndex) {
        this.label = label;
        this.inputType = inputType;
        this.formIndex = formIndex;
    }

    public abstract JComponent createComponent(FormElementChangeListener onChange);

    public abstract Object getValue();

    public String getLabel() {
        return label;
    }

    public int getIndex() {
        return formIndex;
    }

    public Float asFloat() {
        return Float.parseFloat(getValue().toString());
    }

    public Integer asInt() {
        return Integer.parseInt(getValue().toString());
    }

    public String asString() {
        return getValue().toString();
    }

    public Date asDate() {
        return (Date) getValue();
    }

    public String asDateFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) getValue());
    }

    public Color asColor() {
        return (Color) getValue();
    }

    public String asColorHex() {
        Color color = (Color) getValue();
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
