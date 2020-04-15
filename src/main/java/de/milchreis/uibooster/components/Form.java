package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.FilledForm;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.formelements.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Form {

    public Form addColorPicker(String label) {
        formElements.add(new ColorPickerElement(label));
        return this;
    }

    private String title;
    private List<FormElement> formElements;

    public Form(String title) {
        this.title = title;
        this.formElements = new ArrayList<>();
    }

    public Form addText(String label) {
        formElements.add(new TextFormElement(label));
        return this;
    }

    public Form addTextArea(String label) {
        formElements.add(new TextAreaFormElement(label));
        return this;
    }

    public Form addSelection(String label, List<String> possibilities) {
        formElements.add(new SelectionFormElement(label, possibilities));
        return this;
    }

    public Form addSelection(String label, String... possibilities) {
        addSelection(label, Arrays.asList(possibilities));
        return this;
    }

    public Form addLabel(String label) {
        formElements.add(new LabelFormElement(label));
        return this;
    }

    public Form addButton(String buttonLabel, Runnable onClick) {
        return addButton(null, buttonLabel, onClick);
    }

    public Form addButton(String label, String buttonLabel, Runnable onClick) {
        formElements.add(new ButtonFormElement(label, buttonLabel, onClick));
        return this;
    }

    public Form addSlider(String label, int min, int max, int init, int majorTick, int minorTick) {
        formElements.add(new SliderFormElement(label, min, max, init, majorTick, minorTick));
        return this;
    }

    public Form addDatePicker(String label) {
        formElements.add(new DatePickerElement(label));
        return this;
    }

    public enum InputType {
        TEXT, TEXT_AREA, SELECTION, LABEL, BUTTON, SLIDER, COLOR_PICKER, DATE_PICKER
    }

    public FilledForm show() {

        JPanel panel = createPanel();

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(panel);
        dialog.showDialog(null, title);

        return new FilledForm(dialog.getDialog(), formElements);
    }

    public FilledForm run() {
        JPanel panel = createPanel();

        SimpleDialog dialog = new SimpleDialog(title, panel);

        return new FilledForm(dialog, formElements);
    }

    @NotNull
    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for(int i = 0; i < formElements.size(); i++) {

            JPanel elementPanel = new JPanel(new BorderLayout());

            FormElement element = formElements.get(i);

            JComponent component = element.createComponent();

            if(element.getLabel() != null) {
                JLabel label = new JLabel(element.getLabel());
                label.setBorder(new EmptyBorder(0, 0, 5, 0));
                panel.add(label);
                elementPanel.add(label, BorderLayout.NORTH);
            }

            if(component != null) {
                elementPanel.add(component, BorderLayout.CENTER);
                elementPanel.add(new JLabel(" "), BorderLayout.SOUTH);
            }

            panel.add(elementPanel);
        }
        return panel;
    }
}
