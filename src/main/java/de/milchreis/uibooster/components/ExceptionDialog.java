package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.UiBoosterOptions;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDialog {

    public static void showDialog(String message, String title, UiBoosterOptions options, Exception exception) {
        JPanel panel = createExceptionPanel(message, exception);

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(panel);
        dialog.showDialog(message, title, options.getIconPath());
    }

    @NotNull
    private static JPanel createExceptionPanel(String message, Exception exception) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel messageLabel = new JLabel(message);
        messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(messageLabel, BorderLayout.NORTH);

        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        exception.printStackTrace(printWriter);

        JTextArea textArea = new JTextArea(writer.toString(), 30, 80);

        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }

}
