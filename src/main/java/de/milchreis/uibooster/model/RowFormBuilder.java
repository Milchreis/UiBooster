package de.milchreis.uibooster.model;

public class RowFormBuilder extends FormBuilder {

    private final FormBuilder parent;

    public RowFormBuilder(String title, UiBoosterOptions options, FormBuilder parent) {
        super(title, options);
        this.parent = parent;
    }

    public FormElement getRowElement() {
        return new RowFormElement(title, formElements);
    }

    @Override
    public FormBuilder endRow() {

        final int rowElementOffset = parent.formElements.size();
        for (Integer index : this.initialElementsDisabled) {
            parent.addIndexToInitialElementsDisabled(index + rowElementOffset);
        }

        parent.addElement(getRowElement());
        return parent;
    }
}
