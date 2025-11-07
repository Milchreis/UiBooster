package de.milchreis.uibooster.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {

    private final JTable table;
    private DefaultTableModel model;
    private final JButton addRow;
    private final JButton removeRow;
    private List<String> header;

    public TablePanel(String[][] data, List<String> header, boolean isEditable) {
        super(new BorderLayout());
        this.header = header;

        model = new DefaultTableModel(data, header.toArray());

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        table.setEnabled(isEditable);

        addRow = new JButton("+");
        addRow.setEnabled(isEditable);
        addRow.addActionListener(a -> model.addRow(new String[]{}));

        removeRow = new JButton("-");
        removeRow.setEnabled(isEditable);
        removeRow.addActionListener(a -> model.removeRow(table.getSelectedRow()));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addRow);
        buttonPanel.add(removeRow);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public List<String> getHeader() {
        return header;
    }

    public String[][] getData() {

        final TableModel model = table.getModel();

        String[][] data = new String[model.getRowCount()][model.getColumnCount()];

        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
                data[rowIndex][columnIndex] = String.valueOf(model.getValueAt(rowIndex, columnIndex));
            }
        }

        return data;
    }

    public void setData(String[][] data) {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
        }

        for (int i = 0; i < data.length; i++) {
            final String[] row = data[i];
            model.addRow(row);
        }
    }

    public void setHeader(List<String> header) {
        this.header = header;
        model.setColumnIdentifiers(header.toArray());
    }

    public void setEditable(boolean isEditable) {
        table.setEnabled(isEditable);
        addRow.setEnabled(isEditable);
        removeRow.setEnabled(isEditable);
    }

    public JTable getTable() {
        return table;
    }
}
