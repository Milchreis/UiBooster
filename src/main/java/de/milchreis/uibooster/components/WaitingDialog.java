package de.milchreis.uibooster.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WaitingDialog {

    private JFrame dialog;
    private JLabel messageLabel;

    public WaitingDialog(JFrame dialog, JLabel messageLabel) {
        this.dialog = dialog;
        this.messageLabel = messageLabel;
    }

    public void setMessage(String message) {
        SwingUtilities.invokeLater(() -> messageLabel.setText(message));
    }

    public void close() {
        this.dialog.dispose();
    }

    public static WaitingDialog showWaitingDialog(String message, String title) {

        JLabel loading = new JLabel(new ImageIcon(WaitingDialog.class.getResource("/loading-75.gif")));
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        JFrame window = new JFrame();
        window.setTitle(title);
        window.setSize(300, 100);
        window.setUndecorated(true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(loading, BorderLayout.NORTH);
        panel.add(messageLabel, BorderLayout.SOUTH);

        window.add(panel);

        WaitingDialog waitingDialog = new WaitingDialog(window, messageLabel);
        window.setVisible(true);
        return waitingDialog;
    }

}
