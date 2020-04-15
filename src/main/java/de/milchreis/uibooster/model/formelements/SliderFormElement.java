package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;

import javax.swing.*;
import java.awt.*;

import static de.milchreis.uibooster.components.SliderDialog.createSlider;

public class SliderFormElement extends FormElement {

    private JLabel current;
    private JSlider slider;

    private int min, max, init, majorTick, minorTick;

    public SliderFormElement(String label, int min, int max, int init, int majorTick, int minorTick) {
        super(label, Form.InputType.SLIDER);
        this.min = min;
        this.max = max;
        this.init = init;
        this.majorTick = majorTick;
        this.minorTick = minorTick;
    }

    @Override
    public JComponent createComponent() {

        current = new JLabel("1");
        current.setHorizontalAlignment(JLabel.CENTER);

        slider = createSlider(min, max, init, majorTick, minorTick, current);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(current, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public Integer getValue() {
        return slider.getValue();
    }
}
