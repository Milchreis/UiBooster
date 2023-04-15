package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HtmlTextFormElement extends FormElement {

    private final JEditorPane area;

    public HtmlTextFormElement(String label, String htmlContent) {
        super(label);
        area = new JEditorPane("text/html", htmlContent);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {

        if (changeListener != null) {
            area.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    changeListener.onChange(HtmlTextFormElement.this, getValue(), form);
                }
            });
        }
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
    public void setValue(Object value) {
        area.setText(value.toString());
    }
}
