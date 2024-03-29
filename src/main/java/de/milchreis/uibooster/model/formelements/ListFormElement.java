package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import de.milchreis.uibooster.model.ListElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.milchreis.uibooster.components.ListDialog.createList;
import static de.milchreis.uibooster.components.ListDialog.createListModel;

public class ListFormElement extends FormElement<ListElement> {

    private ListElement[] elements;
    private JList<ListElement> list;

    public ListFormElement(String label, ListElement[] elements) {
        super(label);
        this.elements = elements;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener changeListener) {
        list = createList(element -> {

            if (hasBinding())
                binding.set(getValue());

            if (changeListener != null)
                changeListener.onChange(ListFormElement.this, getValue(), form);

        }, elements);

        return new JScrollPane(
            list,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void setEnabled(boolean enable) {
        list.setEnabled(enable);
    }

    @Override
    public ListElement getValue() {
        return list.getSelectedValue();
    }

    @Override
    public void setValue(ListElement value) {
        if (value == null)
            throw new IllegalArgumentException("The value is null and can not set");

        list.setSelectedValue(value, true);
    }

    public void setElements(ListElement[] elements) {
        if (elements == null)
            throw new IllegalArgumentException("The value is null and can not set");

        this.elements = elements;
        list.setModel(createListModel(this.elements));
    }

    public void setElements(List<ListElement> elements) {
        if (elements == null)
            throw new IllegalArgumentException("The value is null and can not set");

        this.elements = elements.stream().toArray(ListElement[]::new);
        list.setModel(createListModel(this.elements));
    }

    public void addElement(ListElement element) {
        ArrayList<ListElement> arrayList = new ArrayList<>(Arrays.asList(elements));
        arrayList.add(element);
        elements = arrayList.toArray(elements);
        list.setModel(createListModel(elements));
    }

    public void clearAll() {
        elements = new ListElement[]{};
        list.setModel(createListModel(elements));
    }

    public void removeElement(ListElement element) {
        elements = getAllElements().stream()
            .filter(e -> !e.equals(element))
            .toArray(ListElement[]::new);

        list.setModel(createListModel(elements));
    }

    public List<ListElement> getAllElements() {
        return Stream.of(elements).collect(Collectors.toList());
    }
}
