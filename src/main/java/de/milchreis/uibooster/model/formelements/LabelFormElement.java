package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;

public class LabelFormElement extends FormElement {

    private final String label;

    public LabelFormElement(String label, int formIndex) {
        super(null, Form.InputType.LABEL, formIndex);
        this.label = label;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {
        return new JLabel(label);
    }

    @Override
    public String getValue() {
        return label;
    }
}
