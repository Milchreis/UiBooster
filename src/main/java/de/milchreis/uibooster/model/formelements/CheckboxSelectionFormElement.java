package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import de.milchreis.uibooster.utils.WrapLayout;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class CheckboxSelectionFormElement extends FormElement {

    private final List<JCheckBox> checkboxes;

    public CheckboxSelectionFormElement(String label, List<String> options) {
        super(label);

        checkboxes = options.stream()
                .map(JCheckBox::new)
                .collect(Collectors.toList());
    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {
        JPanel panel = new JPanel();
        panel.setLayout(new WrapLayout());
        checkboxes.forEach(panel::add);
        return panel;
    }

    @Override
    public void setEnabled(boolean enable) {
        checkboxes.forEach(c -> c.setEnabled(enable));
    }

    @Override
    public Object getValue() {
        return checkboxes.stream()
                .filter(AbstractButton::isSelected)
                .map(AbstractButton::getText)
                .collect(Collectors.toList());
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof List) {
            List<String> valuesToSet = (List<String>) value;

            checkboxes.stream()
                    .filter(c -> valuesToSet.contains(c.getText()))
                    .forEach(c -> c.setSelected(true));

        } else {
            throw new IllegalArgumentException("The value has to be a list of strings");
        }
    }

}
