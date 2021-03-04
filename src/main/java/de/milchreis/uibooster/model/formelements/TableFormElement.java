package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.TablePanel;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableFormElement extends FormElement {

    private final TablePanel panel;

    public TableFormElement(String label, List<String> header, String[][] data, boolean isEditable) {
        super(label);
        panel = new TablePanel(data, header, isEditable);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {

        panel.getTable().getModel().addTableModelListener(e -> {
            if (changeListener != null)
                changeListener.onChange(this, panel.getData(), form);
        });

        return panel;
    }

    @Override
    public void setEnabled(boolean enable) {
        panel.setEditable(enable);
    }

    @Override
    public String[][] getValue() {
        return panel.getData();
    }

    /**
     * Sets the data, header or isEditable flag.
     * Use different types to change the value:
     * - 'String[][]' for data
     * - 'List<String>' for headers
     * - boolean for isEditable
     *
     * @param value
     */
    @Override
    public void setValue(Object value) {
        if (value instanceof String[][]) {
            panel.setData((String[][]) value);

        } else if (value instanceof List) {
            panel.setHeader((List<String>) value);

        } else if (value instanceof Boolean) {
            panel.setEditable((Boolean) value);

        } else
            throw new IllegalArgumentException(
                    "The given value has to be of type 'String[][]' for data, " +
                            "'List<String>' for headers or boolean for isEditable");
    }

    public void addRow(String[] row) {
        final DefaultTableModel model = (DefaultTableModel) panel.getTable().getModel();
        model.addRow(row);
    }

}
