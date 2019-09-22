package de.milchreis.uibooster.components;

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
    private List<FormElement> formularStructure;

    public Form(String title) {
        this.title = title;
        this.formularStructure = new ArrayList<>();
    }

    public Form addText(String label) {
        formularStructure.add(new FormElement(label, InputType.TEXT, null));
        return this;
    }

    public Form addTextArea(String label) {
        formularStructure.add(new FormElement(label, InputType.TEXT_AREA, null));
        return this;
    }

    public Form addSelection(String label, List<String> possibilities) {
        formularStructure.add(new FormElement(label, InputType.SELECTION, possibilities));
        return this;
    }

    public FilledForm show() {

        DialogClosingState closingState = new DialogClosingState();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for(int i=0; i < formularStructure.size(); i++) {

            JPanel elementPanel = new JPanel(new BorderLayout());

            FormElement element = formularStructure.get(i);

            JLabel label = new JLabel(element.getLabel());
            label.setBorder(new EmptyBorder(0, 0, 5, 0));
            panel.add(label);

            JComponent component = null;

            if(element.getInputType() == InputType.SELECTION) {
                component = new JComboBox(((List<String>)element.getPlaceholder()).toArray());
                element.setComponent(component);

            } else if(element.getInputType() == InputType.TEXT_AREA) {
                JTextArea area = new JTextArea();
                element.setComponent(area);
                area.setRows(3);
                component = new JScrollPane(area);

            } else {
                component = new JTextField();
                element.setComponent(component);
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

        return new FilledForm(formularStructure);
    }

    public enum InputType {
        TEXT, TEXT_AREA, SELECTION
    }

}
