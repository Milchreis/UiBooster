package de.milchreis.uibooster.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;
import java.util.List;

public class TablePanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;
    private JButton addRow;
    private JButton removeRow;
    private List<String> header;

    public TablePanel(String[][] data, List<String> header, boolean isEditable, double[] columnWeights) {
        super(new BorderLayout());
        this.header = header;

        model = new DefaultTableModel(data, header.toArray());

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        table.setEnabled(isEditable);

        if (columnWeights != null && columnWeights.length > 0 && columnWeights.length == header.size()) {
            setColumnWidthByWeights(table, columnWeights);

            table.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    setColumnWidthByWeights(table, columnWeights);
                }
            });
        }

        if (isEditable) {
            addRow = new JButton("+");
            addRow.setEnabled(true);
            addRow.addActionListener(a -> model.addRow(new String[]{}));

            removeRow = new JButton("-");
            removeRow.setEnabled(true);
            removeRow.addActionListener(a -> model.removeRow(table.getSelectedRow()));

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            buttonPanel.add(addRow);
            buttonPanel.add(removeRow);

            add(buttonPanel, BorderLayout.SOUTH);
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void setColumnWidthByWeights(JTable table, double[] columnWeights) {
        TableColumnModel columnModel = table.getColumnModel();
        int tableWidth = table.getWidth();

        double totalWeight = Arrays.stream(columnWeights).sum();

        for (int i = 0; i < columnWeights.length; i++) {
            int width = (int) (tableWidth * columnWeights[i] / totalWeight);
            columnModel.getColumn(i).setPreferredWidth(width);
        }
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
