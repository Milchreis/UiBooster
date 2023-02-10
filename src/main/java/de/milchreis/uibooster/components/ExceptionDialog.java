package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDialog {

    public static void showDialog(String message, String title, UiBoosterOptions options, Exception exception) {
        JPanel panel = createExceptionPanel(exception);

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(panel);
        dialog.showDialog(message, title, options.getIconPath());
    }

    private static JPanel createExceptionPanel(Exception exception) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 0, 0, 0));

        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        exception.printStackTrace(printWriter);

        JTextArea textArea = new JTextArea(writer.toString(), 30, 80);
        textArea.setEditable(false);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }

}
