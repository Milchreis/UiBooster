package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SimpleBlockingDialog {

    private JComponent[] components;
    private DialogClosingState closingState = new DialogClosingState();

    public SimpleBlockingDialog(JComponent... component) {
        this.components = component;
    }

    public DialogClosingState showDialog(String message, String title) {

        JOptionPane optionPane = new JOptionPane();

        if(message != null && !message.isEmpty())
            optionPane.setMessage(new Object[]{message, components});
        else
            optionPane.setMessage(new Object[]{components});

        JDialog dialog = optionPane.createDialog(null, title);
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

}
