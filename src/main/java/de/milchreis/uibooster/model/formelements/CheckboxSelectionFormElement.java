package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import de.milchreis.uibooster.utils.WrapLayout;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class CheckboxSelectionFormElement extends FormElement<List<String>> {

    private final List<JCheckBox> checkboxes;

    public CheckboxSelectionFormElement(String label, List<String> options, List<String> initialSelectedOptions) {
        super(label);

        checkboxes = options.stream()
            .map(JCheckBox::new)
            .collect(Collectors.toList());

        if (initialSelectedOptions != null && !initialSelectedOptions.isEmpty())
            checkboxes.stream().filter(c -> initialSelectedOptions.contains(c.getText())).forEach(c -> c.setSelected(true));

    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {
        JPanel panel = new JPanel();
        panel.setLayout(new WrapLayout());

        for (JCheckBox checkbox : checkboxes) {
            panel.add(checkbox);

            checkbox.addActionListener((l) -> {
                if (hasBinding())
                    binding.set(getValue());

                if (onChange != null)
                    onChange.onChange(this, checkbox, form);
            });
        }

        return panel;
    }

    @Override
    public void setEnabled(boolean enable) {
        checkboxes.forEach(c -> c.setEnabled(enable));
    }

    @Override
    public List<String> getValue() {
        return checkboxes.stream()
            .filter(AbstractButton::isSelected)
            .map(AbstractButton::getText)
            .collect(Collectors.toList());
    }

    @Override
    public void setValue(List<String> value) {
        if (value != null) {
            List<String> valuesToSet = value;

            checkboxes.stream()
                .filter(c -> valuesToSet.contains(c.getText()))
                .forEach(c -> c.setSelected(true));

        } else {
            throw new IllegalArgumentException("The value is null and can not set");
        }
    }

}
