package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.util.Date;

public class DatePickerElement extends FormElement<Date> {

    private final JXDatePicker picker;

    public DatePickerElement(String label) {
        super(label);
        picker = new JXDatePicker(new Date(System.currentTimeMillis()));
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        picker.addActionListener(e -> {

            if (hasBinding())
                binding.set(getValue());

            if (changeListener != null)
                changeListener.onChange(DatePickerElement.this, getValue(), form);
        });

        return picker;
    }

    @Override
    public void setEnabled(boolean enable) {
        picker.setEnabled(enable);
    }

    @Override
    public Date getValue() {
        return picker.getDate();
    }

    @Override
    public void setValue(Date value) {
        if (value == null)
            throw new IllegalArgumentException("The value is null and can not set");

        picker.setDate(value);
    }
}
