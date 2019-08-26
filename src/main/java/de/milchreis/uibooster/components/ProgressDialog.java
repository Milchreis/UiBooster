package de.milchreis.uibooster.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProgressDialog {

    private JFrame dialog;
    private JLabel messageLabel;
    private JProgressBar progressBar;

    public ProgressDialog(JFrame dialog, JLabel messageLabel, JProgressBar progressBar) {
        this.dialog = dialog;
        this.messageLabel = messageLabel;
        this.progressBar = progressBar;
    }

    public void setProgress(int current) {
        progressBar.setValue(current);
    }

    public void setMessage(String message) {
        SwingUtilities.invokeLater(() -> messageLabel.setText(message));
    }

    public void close() {
        this.dialog.dispose();
    }

    public static ProgressDialog showDialog(String message, String title, int min, int max) {

        JProgressBar progressBar = new JProgressBar(min, max);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        JFrame window = new JFrame();
        window.setTitle(title);
        window.setSize(500, 100);
        window.setUndecorated(true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(messageLabel, BorderLayout.SOUTH);

        window.add(panel);

        ProgressDialog waitingDialog = new ProgressDialog(window, messageLabel, progressBar);
        window.setVisible(true);
        return waitingDialog;
    }

}
