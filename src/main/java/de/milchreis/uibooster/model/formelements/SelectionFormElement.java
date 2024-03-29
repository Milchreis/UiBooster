package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SelectionFormElement extends FormElement<String> {

    private List<String> possibilities;
    private JComboBox<String> box;

    public SelectionFormElement(String label, List<String> possibilities) {
        super(label);
        this.possibilities = possibilities;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        box = new JComboBox<>((possibilities).toArray(new String[]{}));

        box.addActionListener(e -> {

            if (hasBinding())
                binding.set(getValue());

            if (changeListener != null)
                changeListener.onChange(SelectionFormElement.this, getValue(), form);
        });

        return box;
    }

    @Override
    public void setEnabled(boolean enable) {
        box.setEnabled(enable);
    }

    @Override
    public String getValue() {
        Object text = box.getSelectedItem();
        return text != null ? text.toString() : "";
    }

    @Override
    public void setValue(String value) {
        if (value == null)
            throw new IllegalArgumentException("The value is null and can not set");

        if (!possibilities.contains(value))
            throw new IllegalArgumentException("The given value has to be an element of the supported possibilities list");

        box.setSelectedItem(value);
    }

    public void addSelection(String element) {
        possibilities = new ArrayList<>(possibilities);
        possibilities.add(element);
        box.addItem(element);
    }

    public List<String> getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(List<String> possibilities) {
        this.possibilities = possibilities;
        box.removeAllItems();
        possibilities.forEach(box::addItem);
    }
}
