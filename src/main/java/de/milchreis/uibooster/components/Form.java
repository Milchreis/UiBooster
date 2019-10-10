package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.formelements.*;
import de.milchreis.uibooster.model.DialogClosingState;
import de.milchreis.uibooster.model.FilledForm;
import de.milchreis.uibooster.model.FormElement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Form {

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

    public FilledForm show() {

        DialogClosingState closingState = new DialogClosingState();

        JPanel panel = new JPanel();
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

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new Object[]{panel});

        JDialog dialog = optionPane.createDialog(null, title);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setResizable(true);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closingState.setClosedByUser(true);
            }
        });

        dialog.setVisible(true);
        dialog.dispose();

        return new FilledForm(formElements);
    }

    public enum InputType {
        TEXT, TEXT_AREA, SELECTION, LABEL, BUTTON;
    }

}
