package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextAreaFormElement extends FormElement<String> {

    private final JTextArea area;

    public TextAreaFormElement(String label, int rows, String initialText, boolean readOnly) {
        super(label);
        area = new JTextArea(initialText);
        area.setEditable(!readOnly);
        area.setRows(rows);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {

        area.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if (hasBinding())
                    binding.set(getValue());

                if (changeListener != null)
                    changeListener.onChange(TextAreaFormElement.this, getValue(), form);
            }
        });
        return new JScrollPane(area);
    }

    @Override
    public void setEnabled(boolean enable) {
        area.setEnabled(enable);
    }

    @Override
    public String getValue() {
        return area.getText();
    }

    @Override
    public void setValue(String value) {
        area.setText(value);
    }
}
