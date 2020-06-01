package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.*;

import static de.milchreis.uibooster.components.SliderDialog.createSlider;

public class SliderFormElement extends FormElement {

    private JSlider slider;
    private final int min, max, init, majorTick, minorTick;

    public SliderFormElement(String label, int min, int max, int init, int majorTick, int minorTick, int formIndex) {
        super(label, Form.InputType.SLIDER, formIndex);
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

        if (changeListener != null) {
            slider.addChangeListener(e -> changeListener.onChange(SliderFormElement.this, getValue()));
        }

        return panel;
    }

    @Override
    public Integer getValue() {
        return slider.getValue();
    }
}
