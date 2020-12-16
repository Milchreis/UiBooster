package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterableCheckboxListFormElement extends FormElement {

    private final JTextField search;
    private final JList<JCheckBox> list;
    private List<String> allItems;
    private final List<String> selected = new ArrayList<>();
    private FormElementChangeListener onChange;

    public FilterableCheckboxListFormElement(String label, List<String> possibleValues) {
        super(label);

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
                    JCheckBox checkbox = list.getModel().getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());

                    if (checkbox.isSelected()) {
                        selected.add(checkbox.getText());
                    } else {
                        selected.remove(checkbox.getText());
                    }

                    list.repaint();

                    if (onChange != null)
                        onChange.onChange(FilterableCheckboxListFormElement.this, selected);
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
        vbox.add(search);
        vbox.add(new JScrollPane(
                list,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

        return vbox;
    }

    @Override
    public Object getValue() {
        return selected;
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

    @NotNull
    private DefaultListModel<JCheckBox> createModel(List<String> possibleValues) {
        DefaultListModel<JCheckBox> model = new DefaultListModel<>();
        possibleValues.forEach(v -> model.addElement(new JCheckBox(v, selected.contains(v))));
        return model;
    }
}
