package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.*;

import static de.milchreis.uibooster.components.SliderDialog.createSlider;

public class SliderFormElement extends FormElement<Integer> {

    private JSlider slider;
    private final int min, max, init, majorTick, minorTick;

    public SliderFormElement(String label, int min, int max, int init, int majorTick, int minorTick) {
        super(label);
        this.min = min;
        this.max = max;
        this.init = init;
        this.majorTick = majorTick;
        this.minorTick = minorTick;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {

        JLabel current = new JLabel("1");
        current.setHorizontalAlignment(JLabel.CENTER);

        slider = createSlider(min, max, init, majorTick, minorTick, current);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(current, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.SOUTH);

        slider.addChangeListener(e -> {
            if (hasBinding())
                binding.set(getValue());

            if (changeListener != null)
                changeListener.onChange(SliderFormElement.this, getValue(), form);
        });

        return panel;
    }

    @Override
    public void setEnabled(boolean enable) {
        slider.setEnabled(enable);
    }

    @Override
    public Integer getValue() {
        return slider.getValue();
    }

    @Override
    public void setValue(Integer value) {
        if (value == null)
            throw new IllegalArgumentException("The value is null and can not set");

        slider.setValue(value);
    }
}
