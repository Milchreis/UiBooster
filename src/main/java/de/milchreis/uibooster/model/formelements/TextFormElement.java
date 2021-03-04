package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextFormElement extends FormElement {

    private final JTextField textfield;

    public TextFormElement(String label, String initialText) {
        super(label);
        textfield = new JTextField(initialText);

        if (initialText != null) {
            textfield.setCaretPosition(initialText.length());
        }
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {

        if (changeListener != null) {
            textfield.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    changeListener.onChange(TextFormElement.this, getValue(), form);
                }
            });
        }

        return textfield;
    }

    @Override
    public void setEnabled(boolean enable) {
        textfield.setEnabled(enable);
    }

    @Override
    public String getValue() {
        return textfield.getText();
    }

    @Override
    public void setValue(Object value) {
        textfield.setText(value.toString());
    }
}
