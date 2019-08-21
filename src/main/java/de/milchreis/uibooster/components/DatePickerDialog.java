package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class DatePickerDialog {

    public static Date showDatePicker(String message, String title) {

        DialogClosingState closingState = new DialogClosingState();

        JXDatePicker datePicker = new JXDatePicker(new Date(System.currentTimeMillis()));
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new Object[]{message, datePicker});

        JDialog dialog = optionPane.createDialog(null, title);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closingState.setClosedByUser(true);
            }
        });

        dialog.setVisible(true);
        dialog.dispose();

        return closingState.isClosedByUser() ? null : datePicker.getDate();
    }

}
