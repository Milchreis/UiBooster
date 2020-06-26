package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;

public class TableDialog {

    public static String[][] showTable(String[][] data, List<String> header, String title, String iconPath, boolean isEditable) {

        DialogClosingState closingState = new DialogClosingState();

        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(data, header.toArray());

        final JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        table.setEnabled(isEditable);

        JButton addRow = new JButton("+");
        addRow.setEnabled(isEditable);
        addRow.addActionListener(a -> model.addRow(new String[]{}));

        JButton removeRow = new JButton("-");
        removeRow.setEnabled(isEditable);
        removeRow.addActionListener(a -> model.removeRow(table.getSelectedRow()));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addRow);
        buttonPanel.add(removeRow);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

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

        return closingState.isClosedByUser() ? null : getDataFromTableModel(table.getModel());
    }

    private static String[][] getDataFromTableModel(TableModel model) {

        String[][] data = new String[model.getRowCount()][model.getColumnCount()];

        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
                data[rowIndex][columnIndex] = String.valueOf(model.getValueAt(rowIndex, columnIndex));
            }
        }

        return data;
    }
}