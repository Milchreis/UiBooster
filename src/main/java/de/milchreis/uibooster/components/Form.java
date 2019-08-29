package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;
import de.milchreis.uibooster.model.FilledForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Form {

    private String title;
    private List<FormularElement> formularStructure;

    public Form(String title) {
        this.title = title;
        this.formularStructure = new ArrayList<>();
    }

    public Form addText(String label) {
        formularStructure.add(new FormularElement(label, InputType.TEXT, null));
        return this;
    }

    public Form addTextArea(String label) {
        formularStructure.add(new FormularElement(label, InputType.TEXT_AREA, null));
        return this;
    }

    public Form addSelection(String label, List<String> possibilities) {
        formularStructure.add(new FormularElement(label, InputType.SELECTION, possibilities));
        return this;
    }

    public FilledForm show() {

        DialogClosingState closingState = new DialogClosingState();

        JPanel panel = new JPanel(new GridLayout(0, 1));

        for(int i=0; i < formularStructure.size(); i++) {

            JPanel elementPanel = new JPanel(new BorderLayout());

            FormularElement element = formularStructure.get(i);

            JLabel label = new JLabel(element.label);
            label.setBorder(new EmptyBorder(0, 0, 5, 0));
            panel.add(label);

            JComponent component = null;

            if(element.inputType == InputType.SELECTION) {
                component = new JComboBox(((List<String>)element.placeholder).toArray());

            } else if(element.inputType == InputType.TEXT_AREA) {
                component = new JTextArea();

            } else {
                component = new JTextField();
            }

            elementPanel.add(label, BorderLayout.NORTH);
            elementPanel.add(component, BorderLayout.CENTER);
            elementPanel.add(new JLabel(" "), BorderLayout.SOUTH);

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

        // TODO: read filled elements and return filled formular

        return null;
    }

    public enum InputType {
        TEXT, TEXT_AREA, SELECTION
    }

    private class FormularElement {

        private String label;
        private InputType inputType;
        private Object placeholder;

        public FormularElement(String label, InputType inputType, Object placeholder) {
            this.label = label;
            this.inputType = inputType;
            this.placeholder = placeholder;
        }
    }

}
