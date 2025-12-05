package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;

public class SeparatorFormElement extends FormElement<String> {

    private final JSeparator separator;

    public SeparatorFormElement(String label) {
        super(null);
        separator = new JSeparator();
        separator.setName(label);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        return separator;
    }

    @Override
    public void setEnabled(boolean enable) {
        separator.setEnabled(enable);
    }

    @Override
    public String getValue() {
        return separator.getName();
    }

    @Override
    public void setValue(String value) {
        separator.setName(value);
    }
}
