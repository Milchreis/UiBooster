package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.FontChooserDialog;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import org.drjekyll.fontchooser.FontChooser;

import javax.swing.*;
import java.awt.*;

public class FontChooserElement extends FormElement<Font> {

    private final FontChooser fontChooser;

    public FontChooserElement(String label) {
        this(label, null);
    }

    public FontChooserElement(String label, Font initialFont) {
        super(label);
        fontChooser = FontChooserDialog.createFontChooser(initialFont);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        fontChooser.addChangeListener(e -> {

            if (hasBinding())
                binding.set(getValue());

            if (changeListener != null)
                changeListener.onChange(FontChooserElement.this, getValue(), form);
        });
        return fontChooser;
    }

    @Override
    public void setEnabled(boolean enable) {
        fontChooser.setEnabled(enable);
    }

    @Override
    public Font getValue() {
        return fontChooser.getSelectedFont();
    }

    @Override
    public void setValue(Font value) {
        if (value == null) {
            throw new IllegalArgumentException("The value is null and can not set");
        }
        fontChooser.setFont(value);
    }
}
