package de.milchreis.uibooster.model;

import javax.swing.*;
import java.util.List;

public class FilledForm {

    private final JDialog window;
    private final List<FormElement> elements;

    public FilledForm(JDialog dialog, List<FormElement> elements) {
        window = dialog;
        this.elements = elements;
    }

    public FormElement getByIndex(int index) {
        return elements.get(index);
    }

    public FormElement getByLabel(String label) {
        return elements.stream().filter(element -> element.label.equals(label)).findFirst().get();
    }

    public List<FormElement> getElements() {
        return elements;
    }

    public JDialog getWindow() {
        return window;
    }
}
