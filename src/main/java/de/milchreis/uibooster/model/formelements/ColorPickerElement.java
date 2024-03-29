package de.milchreis.uibooster.model.formelements;

import com.bric.colorpicker.ColorPicker;
import de.milchreis.uibooster.components.ColorPickerDialog;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.*;

public class ColorPickerElement extends FormElement<Color> {

    private final ColorPicker picker;

    public ColorPickerElement(String label) {
        this(label, null);
    }

    public ColorPickerElement(String label, Color initialColor) {
        super(label);
        picker = ColorPickerDialog.createColorPicker(initialColor);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        picker.addColorListener(e -> {

            if (hasBinding())
                binding.set(getValue());

            if (changeListener != null)
                changeListener.onChange(ColorPickerElement.this, getValue(), form);
        });
        return picker;
    }

    @Override
    public void setEnabled(boolean enable) {
        picker.setEnabled(enable);
    }

    @Override
    public Color getValue() {
        return picker.getColor();
    }

    @Override
    public void setValue(Color value) {
        if (value == null)
            throw new IllegalArgumentException("The value is null and can not set");

        picker.setColor(value);
    }

    public void setValue(int value) {
        picker.setColor(new Color(value));
    }
}
