package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;

import javax.swing.*;

public class LabelFormElement extends FormElement {

    private String label;

    public LabelFormElement(String label) {
        super(null, Form.InputType.LABEL);
        this.label = label;
    }

    @Override
    public JComponent createComponent() {
        return new JLabel(label);
    }

    @Override
    public String getValue() {
        return label;
    }
}
