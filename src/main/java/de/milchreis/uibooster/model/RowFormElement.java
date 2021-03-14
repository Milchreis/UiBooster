package de.milchreis.uibooster.model;

import javax.swing.*;
import java.util.List;

import static de.milchreis.uibooster.utils.FormPanel.createPanel;

public class RowFormElement extends FormElement {

    private final List<FormElement> elements;

    public RowFormElement(String label, List<FormElement> elements) {
        super(label);
        this.elements = elements;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {

        Box hBox = Box.createHorizontalBox();
        hBox.setAlignmentY(Box.TOP_ALIGNMENT);

        for (FormElement element : elements) {
            JPanel panel = createPanel(element, onChange, 0);
            hBox.add(panel);
        }

        return hBox;
    }

    @Override
    public void setEnabled(boolean enable) {
        for (FormElement element : elements) {
            element.setEnabled(enable);
        }
    }

    @Override
    public List<FormElement> getValue() {
        return elements;
    }

    @Override
    public void setValue(Object value) {
    }

    public List<FormElement> getElements() {
        return elements;
    }
}
