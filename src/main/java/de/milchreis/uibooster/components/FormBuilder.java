package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.*;
import de.milchreis.uibooster.model.formelements.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormBuilder {

    private final String title;
    private final List<FormElement> formElements;
    private final List<Integer> initialElementsDisabled;
    private final UiBoosterOptions options;
    private FormElementChangeListener changeListener;
    private WindowSetting windowSetting;

    public FormBuilder(String title, UiBoosterOptions options) {
        this.title = title;
        this.options = options;
        this.formElements = new ArrayList<>();
        this.initialElementsDisabled = new ArrayList<>();
    }

    public FormBuilder addText(String label) {
        addElement(new TextFormElement(label, null));
        return this;
    }

    public FormBuilder addText(String label, String initialText) {
        addElement(new TextFormElement(label, initialText));
        return this;
    }

    public FormBuilder addTextArea(String label) {
        addElement(new TextAreaFormElement(label, 3, "", false));
        return this;
    }

    public FormBuilder addTextArea(String label, int rows) {
        addElement(new TextAreaFormElement(label, rows, "", false));
        return this;
    }

    public FormBuilder addTextArea(String label, String initialText) {
        addElement(new TextAreaFormElement(label, 3, initialText, false));
        return this;
    }

    public FormBuilder addTextArea(String label, int rows, String initialText) {
        addElement(new TextAreaFormElement(label, rows, initialText, false));
        return this;
    }

    public FormBuilder addTextArea(String label, int rows, String initialText, boolean readonly) {
        addElement(new TextAreaFormElement(label, rows, initialText, readonly));
        return this;
    }

    public FormBuilder addSelection(String label, List<String> possibilities) {
        addElement(new SelectionFormElement(label, possibilities));
        return this;
    }

    public FormBuilder addSelection(String label, String... possibilities) {
        addSelection(label, Arrays.asList(possibilities));
        return this;
    }

    public FormBuilder addCustomElement(FormElement element) {
        addElement(element);
        return this;
    }

    public FormBuilder addLabel(String label) {
        addElement(new LabelFormElement(label));
        return this;
    }

    public FormBuilder addButton(String buttonLabel, Runnable onClick) {
        return addButton(null, buttonLabel, onClick);
    }

    public FormBuilder addButton(String label, String buttonLabel, Runnable onClick) {
        addElement(new ButtonFormElement(label, buttonLabel, onClick));
        return this;
    }

    public FormBuilder addProgress(String label, int min, int max, int initial) {
        addElement(new ProgressElement(label, min, max, initial));
        return this;
    }

    public FormBuilder addSlider(String label, int min, int max, int init, int majorTick, int minorTick) {
        addElement(new SliderFormElement(label, min, max, init, majorTick, minorTick));
        return this;
    }

    public FormBuilder addDatePicker(String label) {
        addElement(new DatePickerElement(label));
        return this;
    }

    public FormBuilder addColorPicker(String label) {
        addElement(new ColorPickerElement(label));
        return this;
    }

    public FormBuilder addMultipleSelection(String label, String... elements) {
        addElement(new FilterableCheckboxListFormElement(label, false, Arrays.asList(elements)));
        return this;
    }

    public FormBuilder addMultipleSelection(String label, boolean hideFilter, String... elements) {
        addElement(new FilterableCheckboxListFormElement(label, hideFilter, Arrays.asList(elements)));
        return this;
    }

    public FormBuilder addMultipleSelection(String label, List<String> elements) {
        addElement(new FilterableCheckboxListFormElement(label, false, elements));
        return this;
    }

    public FormBuilder addMultipleSelection(String label, boolean hideFilter, List<String> elements) {
        addElement(new FilterableCheckboxListFormElement(label, hideFilter, elements));
        return this;
    }

    public FormBuilder addList(String label, ListElement... elements) {
        addElement(new ListFormElement(label, elements));
        return this;
    }

    public FormBuilder addTable(String label, List<String> header, String[][] data, boolean isEditable) {
        addElement(new TableFormElement(label, header, data, isEditable));
        return this;
    }

    public FormBuilder addTable(String label, List<String> header, String[][] data) {
        addElement(new TableFormElement(label, header, data, true));
        return this;
    }

    public FormBuilder setChangeListener(FormElementChangeListener onChange) {
        this.changeListener = onChange;
        return this;
    }

    public FormBuilder setID(String id) {
        if (formElements.size() == 0)
            return this;

        final FormElement lastElement = formElements.get(formElements.size() - 1);
        lastElement.setId(id);
        return this;
    }

    public FormBuilder setDisabled() {
        if (formElements.size() == 0)
            return this;

        int index = formElements.size() - 1;
        initialElementsDisabled.add(index);
        return this;
    }

    public WindowSetting andWindow() {
        windowSetting = new WindowSetting(this);
        return windowSetting;
    }

    public Form show() {

        JPanel panel = createPanel();
        setInitialDisabledFormElements();

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(panel);
        final Form form = new Form(dialog.getDialog(), formElements);
        dialog.showDialog(null, title, windowSetting, options.getIconPath(), true);
        return form;
    }

    public Form run() {
        JPanel panel = createPanel();
        setInitialDisabledFormElements();

        SimpleDialog dialog = new SimpleDialog(title, panel, windowSetting, options.getIconPath());
        return new Form(dialog, formElements);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (FormElement formElement : formElements) {

            JPanel elementPanel = new JPanel(new BorderLayout());
            JComponent component = formElement.createComponent(changeListener);

            if (formElement.getLabel() != null) {
                JLabel label = new JLabel(formElement.getLabel());
                label.setBorder(new EmptyBorder(0, 0, 5, 0));
                panel.add(label);
                elementPanel.add(label, BorderLayout.NORTH);
            }

            if (component != null) {
                elementPanel.add(component, BorderLayout.CENTER);
                elementPanel.add(new JLabel(" "), BorderLayout.SOUTH);
            }

            panel.add(elementPanel);
        }
        return panel;
    }

    private void setInitialDisabledFormElements() {
        for (Integer index : initialElementsDisabled) {
            formElements.get(index).setEnabled(false);
        }
    }

    private void addElement(FormElement e) {
        e.setFormIndex(formElements.size());
        formElements.add(e);
    }

}
