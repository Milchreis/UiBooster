package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.stream.Collectors;

public class FilterableCheckboxListFormElement extends FormElement {

    private final JTextField search;
    private final JList<JCheckBox> list;
    private final boolean hideFilter;
    private List<String> allItems;
    private final Set<String> selected = new LinkedHashSet<>();
    private FormElementChangeListener onChange;

    public FilterableCheckboxListFormElement(String label, boolean hideFilter, List<String> possibleValues) {
        super(label);
        this.hideFilter = hideFilter;

        allItems = possibleValues;

        search = new JTextField();
        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                final String searchString = search.getText().toLowerCase();

                list.setModel(createModel(allItems.stream()
                        .filter(s -> s.toLowerCase().contains(searchString))
                        .collect(Collectors.toList())));
            }
        });

        list = new JList<>(createModel(allItems));
        list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = list.locationToIndex(e.getPoint());
                if (index != -1) {
                    toggleCheckbox(index);
                    updateUiAndListener();
                }
            }
        });

        list.setCellRenderer((list, checkbox, index, isSelected, cellHasFocus) -> {

            checkbox.setComponentOrientation(list.getComponentOrientation());
            checkbox.setFont(list.getFont());

            checkbox.setBackground(list.getBackground());
            checkbox.setForeground(list.getForeground());

            checkbox.setFocusPainted(false);
            checkbox.setBorderPainted(true);

            checkbox.setEnabled(list.isEnabled());
            checkbox.setText(checkbox.getText());

            return checkbox;
        });
    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {
        this.onChange = onChange;

        Box vbox = Box.createVerticalBox();

        if (!hideFilter)
            vbox.add(search);

        vbox.add(new JScrollPane(
                list,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

        return vbox;
    }

    @Override
    public void setEnabled(boolean enable) {
        search.setEnabled(enable);
        list.setEnabled(enable);
    }

    @Override
    public Object getValue() {
        return new ArrayList<>(this.selected);
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof List) {
            allItems = (List<String>) value;
            list.setModel(createModel(allItems));

        } else if (value instanceof String[]) {
            allItems = Arrays.asList((String[]) value);
            list.setModel(createModel(allItems));

        } else
            throw new IllegalArgumentException("The given value has to be List<String> or String[]");
    }

    public List<String> getVisibleItems() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < list.getModel().getSize(); i++) {
            final JCheckBox checkBox = list.getModel().getElementAt(i);
            items.add(checkBox.getText());
        }
        return items;
    }

    public void select(String item) {
        for (int i = 0; i < list.getModel().getSize(); i++) {
            final JCheckBox checkBox = list.getModel().getElementAt(i);
            if (checkBox.getText().equals(item)) {
                selectCheckbox(i, true);
            }
        }
        updateUiAndListener();
    }

    public void unselect(String item) {
        for (int i = 0; i < list.getModel().getSize(); i++) {
            final JCheckBox checkBox = list.getModel().getElementAt(i);
            if (checkBox.getText().equals(item)) {
                selectCheckbox(i, false);
            }
        }
        updateUiAndListener();
    }

    private DefaultListModel<JCheckBox> createModel(List<String> possibleValues) {
        DefaultListModel<JCheckBox> model = new DefaultListModel<>();
        possibleValues.forEach(v -> model.addElement(new JCheckBox(v, selected.contains(v))));
        return model;
    }

    private void toggleCheckbox(int index) {
        JCheckBox checkbox = list.getModel().getElementAt(index);
        checkbox.setSelected(!checkbox.isSelected());

        if (checkbox.isSelected()) {
            selected.add(checkbox.getText());
        } else {
            selected.remove(checkbox.getText());
        }
    }

    private void selectCheckbox(int index, boolean select) {
        JCheckBox checkbox = list.getModel().getElementAt(index);
        checkbox.setSelected(select);
        if (select) {
            selected.add(checkbox.getText());
        } else {
            selected.remove(checkbox.getText());
        }
    }

    private void updateUiAndListener() {
        list.repaint();

        if (onChange != null)
            onChange.onChange(this, selected, form);
    }
}
