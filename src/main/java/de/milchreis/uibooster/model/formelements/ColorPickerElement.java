package de.milchreis.uibooster.model.formelements;

import com.bric.colorpicker.ColorPicker;
import de.milchreis.uibooster.components.ColorPickerDialog;
import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;

import javax.swing.*;
import java.awt.*;

public class ColorPickerElement extends FormElement {

    private ColorPicker picker;

    public ColorPickerElement(String label) {
        super(label, Form.InputType.COLOR_PICKER);
        picker = ColorPickerDialog.createColorPicker();
    }

    @Override
    public JComponent createComponent() {
        return picker;
    }

    @Override
    public Color getValue() {
        return picker.getColor();
    }
}
