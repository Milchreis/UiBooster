package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.util.List;

public class SelectionFormElement extends FormElement {

    private List<String> possibilities;
    private JComboBox<String> box;

    public SelectionFormElement(String label, int formIndex, List<String> possibilities) {
        super(label, Form.InputType.SELECTION, formIndex);
        this.possibilities = possibilities;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        box = new JComboBox<>((possibilities).toArray(new String[]{}));

        if (changeListener != null) {
            box.addActionListener(e -> changeListener.onChange(SelectionFormElement.this, getValue()));
        }

        return box;
    }

    @Override
    public String getValue() {
        Object text = box.getSelectedItem();
        return text != null ? text.toString() : "";
    }
}
