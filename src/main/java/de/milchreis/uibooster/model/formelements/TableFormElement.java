package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.components.TablePanel;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import de.milchreis.uibooster.model.TableData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableFormElement extends FormElement<TableData> {

    private final TablePanel panel;

    public TableFormElement(String label, List<String> header, String[][] data, boolean isEditable) {
        super(label);
        panel = new TablePanel(data, header, isEditable);
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {

        panel.getTable().getModel().addTableModelListener(e -> {
            if (hasBinding())
                binding.set(getValue());

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
    public TableData getValue() {
        return new TableData(
            panel.getHeader(),
            panel.getData()
        );
    }

    @Override
    public void setValue(TableData value) {
        if (value == null)
            throw new IllegalArgumentException("The value is null and can not set");

        panel.setHeader(value.getHeader());
        panel.setData(value.getData());
    }

    public void addRow(String[] row) {
        final DefaultTableModel model = (DefaultTableModel) panel.getTable().getModel();
        model.addRow(row);
    }

}
