package de.milchreis.uibooster.model;

public class RowFormBuilder extends FormBuilder {

    private final FormBuilder parent;

    public RowFormBuilder(String title, UiBoosterOptions options, FormBuilder parent, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        super(title, options);
        this.parent = parent;
        this.defaultMarginLeft = marginLeft;
        this.defaultMarginTop = marginTop;
        this.defaultMarginRight = marginRight;
        this.defaultMarginBottom = marginBottom;
    }

    public RowFormBuilder(String title, UiBoosterOptions options, FormBuilder parent, int hGap, int vGap) {
        super(title, options);
        this.parent = parent;

        this.defaultMarginLeft = hGap > 0 ? hGap / 2 : 0;
        this.defaultMarginRight = hGap > 0 ? hGap / 2 : 0;
        this.defaultMarginTop = vGap > 0 ? vGap / 2 : 0;
        this.defaultMarginBottom = vGap > 0 ? vGap / 2 : 0;
    }

    public FormElement getRowElement() {
        return new RowFormElement(title, formElements,
            defaultMarginLeft, defaultMarginTop, defaultMarginRight, defaultMarginBottom
        );
    }

    @Override
    public FormBuilder endRow() {
        parent.initialElementsDisabled.addAll(this.initialElementsDisabled);
        parent.addElement(getRowElement());
        return parent;
    }
}
