package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.util.Date;

public class DatePickerElement extends FormElement {

    private JXDatePicker picker;

    public DatePickerElement(String label) {
        super(label, Form.InputType.DATE_PICKER);
        picker = new JXDatePicker(new Date(System.currentTimeMillis()));
    }

    @Override
    public JComponent createComponent() {
        return picker;
    }

    @Override
    public Date getValue() {
        return picker.getDate();
    }
}
