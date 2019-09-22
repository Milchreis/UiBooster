package de.milchreis.uibooster.model;

import de.milchreis.uibooster.components.Form;

import javax.swing.*;
import java.awt.*;

public class FormElement {

    private String label;
    private Form.InputType inputType;
    private Object placeholder;
    private Component component;

    public FormElement(String label, Form.InputType inputType, Object placeholder) {
        this.label = label;
        this.inputType = inputType;
        this.placeholder = placeholder;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public String getLabel() {
        return label;
    }

    public Form.InputType getInputType() {
        return inputType;
    }

    public Object getPlaceholder() {
        return placeholder;
    }

    public String getValue() {
        if(inputType == Form.InputType.SELECTION) {
            Object text = ((JComboBox) component).getSelectedItem();
            return text != null ? text.toString() : "";

        } else if(inputType == Form.InputType.TEXT_AREA) {
            return ((JTextArea) component).getText();

        } else {
            return ((JTextField) component).getText();
        }
    }
}
