package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextAreaDialog {

    public static void showDialog(String label, String title, String message, int rows, int columns, boolean editable, UiBoosterOptions options) {
        JPanel panel = createPanel(message, rows, columns, editable);

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(panel);
        dialog.showDialog(label, title, options.getIconPath());
    }

    protected static JPanel createPanel(String message, int rows, int columns, boolean editable) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JTextArea textArea = new JTextArea(message, rows, columns);
        textArea.setEditable(editable);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }

}
