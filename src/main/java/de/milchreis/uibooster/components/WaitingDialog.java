package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;


public class WaitingDialog {

    private final JFrame dialog;
    private final JLabel messageLabel;
    private JTextArea largeMessageText;

    public WaitingDialog(JFrame dialog, JLabel messageLabel, JTextArea largeMessageText) {
        this.dialog = dialog;
        this.messageLabel = messageLabel;
        this.largeMessageText = largeMessageText;
    }

    public void addToLargeMessage(String largeMessage) {
        if (largeMessageText.getParent() == null)
            setLargeMessage(largeMessage);
        else
            SwingUtilities.invokeLater(() -> largeMessageText.setText(largeMessageText.getText() + "\n" + largeMessage));
    }

    public void setLargeMessage(String largeMessage) {
        SwingUtilities.invokeLater(() -> {
            if (messageLabel.getParent().getComponents().length == 1) {
                largeMessageText = new JTextArea(largeMessage);
                addLargeMessage(dialog, (JComponent) messageLabel.getParent(), largeMessageText);
            }

            largeMessageText.setText(largeMessage);
        });
    }

    public void setMessage(String message) {
        SwingUtilities.invokeLater(() -> messageLabel.setText(message));
    }

    public void close() {
        this.dialog.dispose();
    }

    public static WaitingDialog showDialog(String message, String title, String largeMessage, UiBoosterOptions options, boolean decorated) {

        String loadingImage = options.getLoadingImage();

        JLabel loading = new JLabel(new ImageIcon(WaitingDialog.class.getResource(loadingImage)));

        JFrame window = new JFrame();
        window.setTitle(title);
        window.setSize(largeMessage != null ? 500 : 300, largeMessage != null ? 500 : 120);
        window.setUndecorated(!decorated);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
        applyWindowIcon(options.getIconPath(), window);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(loading, BorderLayout.NORTH);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(messageLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(largeMessage);

        if (largeMessage != null) {
            addLargeMessage(window, textPanel, textArea);
        }

        panel.add(textPanel, BorderLayout.CENTER);
        window.add(panel);

        WaitingDialog waitingDialog = new WaitingDialog(window, messageLabel, textArea);
        window.setVisible(true);
        return waitingDialog;
    }

    private static void addLargeMessage(JFrame window, JComponent panel, JTextArea textArea) {

        JScrollPane scollpane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scollpane, BorderLayout.CENTER);

        window.setSize(500, 500);
        window.setLocationRelativeTo(null);
    }

}
