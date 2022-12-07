package de.milchreis.uibooster.model;

import de.milchreis.uibooster.model.formelements.ListFormElement;
import de.milchreis.uibooster.model.formelements.SelectionFormElement;
import de.milchreis.uibooster.model.formelements.TableFormElement;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FormElement {

    protected String id;
    protected String label;
    protected int formIndex;
    protected Form form;

    protected int marginLeft = 0;
    protected int marginTop = 0;
    protected int marginRight = 0;
    protected int marginBottom = 15;

    public FormElement(String label) {
        this.label = label;
    }

    public void setFormIndex(int formIndex) {
        this.formIndex = formIndex;
    }

    public abstract JComponent createComponent(FormElementChangeListener onChange);

    public abstract void setEnabled(boolean enable);

    public abstract Object getValue();

    public abstract void setValue(Object value);

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return formIndex;
    }

    public Float asFloat() {
        return Float.parseFloat(getValue().toString());
    }

    public Integer asInt() {
        return Integer.parseInt(getValue().toString());
    }

    public String asString() {
        return getValue().toString();
    }

    public Date asDate() {
        return (Date) getValue();
    }

    public String asDateFormatted() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) getValue());
    }

    public Color asColor() {
        return (Color) getValue();
    }

    public String asColorHex() {
        Color color = (Color) getValue();
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    void setForm(Form form) {
        this.form = form;
    }

    public TableFormElement toTable() {
        return (TableFormElement) this;
    }

    public ListFormElement toList() {
        return (ListFormElement) this;
    }

    public RowFormElement toRow() {
        return (RowFormElement) this;
    }

    public SelectionFormElement toSelection() {
        return (SelectionFormElement) this;
    }

    public void setMargin(int marginLeft, int marginTop, int marginRight, int marginBottom) {
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public int getMarginBottom() {
        return marginBottom;
    }
}
