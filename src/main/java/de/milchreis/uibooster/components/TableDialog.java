package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;

public class TableDialog {

    public static String[][] showTable(String[][] data, List<String> header, String title, String iconPath, boolean isEditable) {

        DialogClosingState closingState = new DialogClosingState();

        TablePanel panel = new TablePanel(data, header, isEditable);

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new Object[]{panel});

        JDialog dialog = optionPane.createDialog(null, title);
        dialog.setResizable(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closingState.setClosedByUser(true);
            }
        });

        applyWindowIcon(iconPath, dialog);

        dialog.setVisible(true);
        dialog.dispose();

        final JTable table = (JTable) Arrays.stream(panel.getComponents())
                .filter(c -> c instanceof JTable)
                .findFirst()
                .orElse(null);

        return closingState.isClosedByUser() ? null : panel.getData();
    }

}