package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;

public class ButtonFormElement extends FormElement {

    private final String buttonLabel;
    private final Runnable onClick;
    private JButton button;

    public ButtonFormElement(String label, String buttonLabel, int formIndex, Runnable onClick) {
        super(label, Form.InputType.BUTTON, formIndex);

        this.buttonLabel = buttonLabel;
        this.onClick = onClick;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {
        button = new JButton(buttonLabel);
        button.addActionListener(l -> onClick.run());
        return button;
    }

    @Override
    public Object getValue() {
        return button.getName();
    }
}
