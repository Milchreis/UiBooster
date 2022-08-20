package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.FontChooserDialog;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import java.awt.Font;
import javax.swing.JComponent;
import org.drjekyll.fontchooser.FontChooser;

public class FontChooserElement extends FormElement {

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
        if (changeListener != null) {
            fontChooser.addChangeListener(e -> changeListener.onChange(FontChooserElement.this, getValue(), form));
        }
        return fontChooser;
    }

    @Override
    public void setEnabled(boolean enable) {
        fontChooser.setEnabled(enable);
    }

    @Override
    public Font getValue() {
        return fontChooser.getFont();
    }

    @Override
    public void setValue(Object value) {
        if (!(value instanceof Font)) {
            throw new IllegalArgumentException("The given value has to be of type 'Font'");
        }
        fontChooser.setFont((Font) value);
    }
}
