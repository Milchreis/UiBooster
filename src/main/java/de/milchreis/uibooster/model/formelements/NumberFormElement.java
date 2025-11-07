package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.*;

public class NumberFormElement extends FormElement<Double> {

    private JSpinner spinner;
    private final double initialValue;
    private final double min;
    private final double max;
    private final double stepSize;
    private final String innerLabel;

    public NumberFormElement(String label, double initialValue) {
        this(label, Long.MIN_VALUE, Long.MAX_VALUE, 1.0, initialValue);
    }

    public NumberFormElement(String label, double min, double max, double stepSize, double initialValue) {
        super("");
        this.initialValue = initialValue;
        this.stepSize = stepSize;
        this.innerLabel = label;
        this.min = min;
        this.max = max;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {

        JPanel box = new JPanel();
        box.setLayout(new BorderLayout());

        spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(initialValue, this.min, this.max, this.stepSize));
        spinner.setValue(initialValue);

        spinner.addChangeListener(e -> {
            if (hasBinding())
                binding.set(getValue());

            if (changeListener != null)
                changeListener.onChange(NumberFormElement.this, getValue(), form);
        });


        JLabel label = new JLabel(innerLabel);

        label.setPreferredSize(new Dimension(80, 30));
        label.setSize(new Dimension(80, 30));
        label.setMaximumSize(new Dimension(80, 30));

        box.add(label, BorderLayout.WEST);
        box.add(spinner, BorderLayout.CENTER);

        return box;
    }

    @Override
    public void setEnabled(boolean enable) {
        spinner.setEnabled(enable);
    }


    @Override
    public Double getValue() {
        Object value = spinner.getValue();
        Double result = null;
        if (value instanceof Float) {
            result = ((Float) value).doubleValue();
            return result;
        } else if (value instanceof Integer) {
            result = ((Integer) value).doubleValue();
            return result;
        } else {
            return (Double) spinner.getValue();
        }
    }

    @Override
    public void setValue(Double value) {
        spinner.setValue(value);
    }

}
