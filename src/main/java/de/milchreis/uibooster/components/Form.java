package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;
import de.milchreis.uibooster.model.FilledForm;

import javax.swing.*;
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

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        for(int i=0; i < formularStructure.size(); i++) {

            FormularElement element = formularStructure.get(i);

            JLabel label = new JLabel(element.label);
            cs.gridx = 0;
            cs.gridy = i;
            cs.gridwidth = 1;
            cs.ipadx = 5;
            cs.ipady = 5;
            panel.add(label, cs);

            JComponent component = null;

            if(element.inputType == InputType.SELECTION) {
                component = new JComboBox(((List<String>)element.placeholder).toArray());

            } else if(element.inputType == InputType.TEXT_AREA) {
                component = new JTextArea();

            } else {
                component = new JTextField();
            }

            cs.gridx = 1;
            cs.gridy = i;
            cs.ipadx = 5;
            cs.ipady = 5;
            cs.gridwidth = 2;
            panel.add(component, cs);
        }

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new Object[]{panel});

        JDialog dialog = optionPane.createDialog(null, title);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.pack();
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
