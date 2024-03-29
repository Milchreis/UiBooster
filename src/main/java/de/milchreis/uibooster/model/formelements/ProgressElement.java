package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;

public class ProgressElement extends FormElement<Integer> {

    private final JProgressBar progressBar;

    public ProgressElement(String label, int min, int max, int initial) {
        super(label);

        progressBar = new JProgressBar(min, max);
        progressBar.setValue(initial);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        progressBar.addChangeListener(e -> {

            if (hasBinding())
                binding.set(getValue());

            if (changeListener != null)
                changeListener.onChange(ProgressElement.this, getValue(), form);
        });

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
    public void setValue(Integer value) {
        if (value != null)
            progressBar.setValue(value);
        else
            throw new IllegalArgumentException("The value is null and can not set\"");
    }

    public void setMax(int max) {
        progressBar.setMaximum(max);
    }

    public void setMin(int min) {
        progressBar.setMinimum(min);
    }
}
