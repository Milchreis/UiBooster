package de.milchreis.uibooster.utils;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class FormPanel {

    public static JPanel createPanel(List<FormElement> formElements, FormElementChangeListener changeListener, int panelBorder) {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(panelBorder, panelBorder, panelBorder, panelBorder));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (FormElement formElement : formElements) {

            JPanel elementPanel = new JPanel(new BorderLayout());
            JComponent component = formElement.createComponent(changeListener);
            component.setToolTipText(formElement.getTooltip());

            if (formElement.getLabel() != null) {
                JLabel label = new JLabel(formElement.getLabel());
                label.setBorder(new EmptyBorder(0, 0, 5, 0));
                panel.add(label);
                elementPanel.add(label, BorderLayout.NORTH);
            }

            if (component != null) {
                elementPanel.add(component, BorderLayout.CENTER);
            }

            if (formElement.hasBinding()) {
                formElement.setValueFromBinding();
            }

            elementPanel.setBorder(new EmptyBorder(
                formElement.getMarginTop(), formElement.getMarginLeft(),
                formElement.getMarginBottom(), formElement.getMarginRight())
            );

            panel.add(elementPanel);
        }
        return panel;
    }
}
