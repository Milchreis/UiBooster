package de.milchreis.uibooster.model;

public class RowFormBuilder extends FormBuilder {

    private int vGap = 0;
    private int hGap = 0;
    private final double[] columnWeights;

    public RowFormBuilder(String title,
                          UiBoosterOptions options,
                          FormBuilder parent,
                          int marginLeft,
                          int marginTop,
                          int marginRight,
                          int marginBottom,
                          double[] columnWeights) {

        super(title, options);
        this.columnWeights = columnWeights;
        super.parent = parent;
        this.defaultMarginLeft = marginLeft;
        this.defaultMarginTop = marginTop;
        this.defaultMarginRight = marginRight;
        this.defaultMarginBottom = marginBottom;
    }

    public RowFormBuilder(String title, UiBoosterOptions options, FormBuilder parent, int hGap, int vGap) {
        this(title, options, parent, hGap, vGap, null);
    }

    public RowFormBuilder(String title, UiBoosterOptions options, FormBuilder parent, int hGap, int vGap, double[] columnWeights) {
        super(title, options);
        this.hGap = hGap;
        this.vGap = vGap;
        this.parent = parent;
        this.defaultMarginBottom = 0;
        this.columnWeights = columnWeights;
    }

    public FormElement<Object> getRowElement() {
        return new RowFormElement(
            title,
            formElements,
            defaultMarginLeft,
            defaultMarginTop,
            defaultMarginRight,
            defaultMarginBottom,
            hGap,
            vGap,
            columnWeights
        );
    }

    @Override
    public FormBuilder endRow() {
        parent.initialElementsDisabled.addAll(this.initialElementsDisabled);
        parent.addElement(getRowElement());
        return parent;
    }
}
