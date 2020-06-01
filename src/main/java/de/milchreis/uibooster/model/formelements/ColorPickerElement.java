package de.milchreis.uibooster.model.formelements;

import com.bric.colorpicker.ColorPicker;
import de.milchreis.uibooster.components.ColorPickerDialog;
import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.*;

public class ColorPickerElement extends FormElement {

    private final ColorPicker picker;

    public ColorPickerElement(String label, int formIndex) {
        super(label, Form.InputType.COLOR_PICKER, formIndex);
        picker = ColorPickerDialog.createColorPicker();
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        if (changeListener != null) {
            picker.addColorListener(e -> changeListener.onChange(ColorPickerElement.this, getValue()));
        }
        return picker;
    }

    @Override
    public Color getValue() {
        return picker.getColor();
    }
}
