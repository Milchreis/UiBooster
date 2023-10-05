package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;

public class ProgressElement extends FormElement {

    private final JProgressBar progressBar;

    public ProgressElement(String label, int min, int max, int initial) {
        super(label);

        progressBar = new JProgressBar(min, max);
        progressBar.setValue(initial);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        if (changeListener != null) {
            progressBar.addChangeListener(e -> changeListener.onChange(ProgressElement.this, getValue(), form));
        }

        return progressBar;
    }

    @Override
    public void setEnabled(boolean enable) {
        progressBar.setEnabled(enable);
    }

    @Override
    public Integer getValue() {
        return progressBar.getValue();
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Integer)
            progressBar.setValue((Integer) value);
        else
            throw new IllegalArgumentException("The given value has to be of type 'int'");
    }

    public void setMax(int max) {
        progressBar.setMaximum(max);
    }

    public void setMin(int min) {
        progressBar.setMinimum(min);
    }
}
