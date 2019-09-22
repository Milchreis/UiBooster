package de.milchreis.uibooster.model;

import java.util.List;

public class FilledForm {

    private List<FormElement> elements;

    public FilledForm(List<FormElement> elements) {
        this.elements = elements;
    }

    public List<FormElement> getElements() {
        return elements;
    }
}
