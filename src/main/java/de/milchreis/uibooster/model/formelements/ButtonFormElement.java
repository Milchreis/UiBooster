package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;

public class ButtonFormElement extends FormElement {

    private String buttonLabel;
    private final Runnable onClick;
    private JButton button;

    public ButtonFormElement(String label, String buttonLabel, Runnable onClick) {
        super(label);

        this.buttonLabel = buttonLabel;
        this.onClick = onClick;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {
        button = new JButton(buttonLabel);
        button.addActionListener(l -> {
            onClick.run();

            if (onChange != null)
                onChange.onChange(this, buttonLabel);
        });

        return button;
    }

    @Override
    public void setEnabled(boolean enable) {
        button.setEnabled(enable);
    }

    @Override
    public Object getValue() {
        return button.getName();
    }

    @Override
    public void setValue(Object value) {
        buttonLabel = value.toString();
        button.setText(buttonLabel);
    }
}
