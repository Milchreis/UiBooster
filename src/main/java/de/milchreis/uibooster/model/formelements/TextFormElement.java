package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextFormElement extends FormElement {

    private JTextField textfield;

    public TextFormElement(String label, int formIndex) {
        super(label, Form.InputType.TEXT, formIndex);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        textfield = new JTextField();

        if (changeListener != null) {
            textfield.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    changeListener.onChange(TextFormElement.this, getValue());
                }
            });
        }

        return textfield;
    }

    @Override
    public String getValue() {
        return textfield.getText();
    }
}
