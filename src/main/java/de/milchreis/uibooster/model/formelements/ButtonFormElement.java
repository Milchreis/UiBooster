package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;

import javax.swing.*;

public class ButtonFormElement extends FormElement {

    private String buttonLabel;
    private Runnable onClick;
    private JButton button;

    public ButtonFormElement(String label, String buttonLabel, Runnable onClick) {
        super(label, Form.InputType.BUTTON);

        this.buttonLabel = buttonLabel;
        this.onClick = onClick;
    }

    @Override
    public JComponent createComponent() {
        button = new JButton(buttonLabel);
        button.addActionListener(l -> onClick.run());
        return button;
    }

    @Override
    public Object getValue() {
        return button.getName();
    }
}
