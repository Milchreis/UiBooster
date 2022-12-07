package de.milchreis.uibooster.model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;

import static de.milchreis.uibooster.utils.FormPanel.createPanel;
import static java.util.Collections.singletonList;

public class RowFormElement extends FormElement {

    private final List<FormElement> elements;

    public RowFormElement(String label, List<FormElement> elements, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        super(label);
        this.elements = elements;
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {

        Box hBox = Box.createHorizontalBox();
        hBox.setAlignmentY(Box.TOP_ALIGNMENT);
        hBox.setBorder(new EmptyBorder(marginTop, marginLeft, marginBottom, marginRight));

        for (FormElement element : elements) {
            JPanel panel = createPanel(singletonList(element), onChange, 0);
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
