package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;

public class SimpleBlockingDialog {

    private JDialog dialog;
    private JComponent[] components;
    private DialogClosingState closingState = new DialogClosingState();

    public SimpleBlockingDialog(JComponent... component) {
        this.components = component;
    }

    public DialogClosingState showDialog(String message, String title, String iconPath) {

        JOptionPane optionPane = new JOptionPane();

        if (message != null && !message.isEmpty())
            optionPane.setMessage(new Object[]{message, components});
        else
            optionPane.setMessage(new Object[]{components});

        dialog = optionPane.createDialog(null, title);
        applyWindowIcon(iconPath, dialog);

        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closingState.setClosedByUser(true);
            }
        });

        dialog.setVisible(true);
        dialog.dispose();

        return closingState;
    }

    public JDialog getDialog() {
        return dialog;
    }
}
