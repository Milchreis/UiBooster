package de.milchreis.uibooster.model;

import de.milchreis.uibooster.components.Form;

import javax.swing.*;

public abstract class FormElement {

    protected String label;
    protected Form.InputType inputType;

    public FormElement(String label, Form.InputType inputType) {
        this.label = label;
        this.inputType = inputType;
    }

    public abstract JComponent createComponent();

    public abstract String getValue();

    public String getLabel() {
        return label;
    }

    public Float asFloat() {
        return Float.parseFloat(getValue());
    }

    public Integer asInt() {
        return Integer.parseInt(getValue());
    }

    public String asString() {
        return getValue();
    }
}
