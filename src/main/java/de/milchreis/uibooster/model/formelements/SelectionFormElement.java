package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;

import javax.swing.*;
import java.util.List;

public class SelectionFormElement extends FormElement {

    private List<String> possibilities;
    private JComboBox<String> box;

    public SelectionFormElement(String label, List<String> possibilities) {
        super(label, Form.InputType.SELECTION);
        this.possibilities = possibilities;
    }

    @Override
    public JComponent createComponent() {
        box = new JComboBox<>((possibilities).toArray(new String[]{}));
        return box;
    }

    @Override
    public String getValue() {
        Object text = box.getSelectedItem();
        return text != null ? text.toString() : "";
    }
}
