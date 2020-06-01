package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.Form;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.util.Date;

public class DatePickerElement extends FormElement {

    private JXDatePicker picker;

    public DatePickerElement(String label, int formIndex) {
        super(label, Form.InputType.DATE_PICKER, formIndex);
        picker = new JXDatePicker(new Date(System.currentTimeMillis()));
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        if (changeListener != null) {
            picker.addActionListener(e -> changeListener.onChange(DatePickerElement.this, getValue()));
        }

        return picker;
    }

    @Override
    public Date getValue() {
        return picker.getDate();
    }
}
