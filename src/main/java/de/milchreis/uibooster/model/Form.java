package de.milchreis.uibooster.model;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Form {

    private JDialog window;
    private final List<FormElement> elements;
    private boolean closed = false;

    public Form(JDialog dialog, List<FormElement> elements) {
        this(dialog, elements, null);
    }

    public Form(JDialog dialog, List<FormElement> elements, DialogClosingState closingState) {
        window = dialog;
        this.elements = elements;
        getAllFormElements().forEach(e -> e.setForm(this));

        if (window != null && closingState == null) {
            window.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    closed = true;
                }
            });
        } else if (closingState != null) {
            closed = closingState.isClosedByUser();
        }
    }

    public FormElement getById(String id) {
        return getAllFormElements().stream()
                .filter(element -> Objects.nonNull(element.id))
                .filter(element -> element.id.equals(id))
                .findFirst().get();
    }

    public FormElement getByIndex(int index) {
        return getAllFormElements().get(index);
    }

    public FormElement getByLabel(String label) {
        return getAllFormElements().stream()
                .filter(element -> Objects.nonNull(element.label))
                .filter(element -> element.label.equals(label))
                .findFirst().get();
    }

    public List<FormElement> getElements() {
        return elements;
    }

    void setClosedByUser(boolean closed) {
        this.closed = closed;
    }

    void setWindow(JDialog window) {
        this.window = window;
        this.window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closed = true;
            }
        });
    }

    public boolean isClosedByUser() {
        return closed;
    }

    /**
     * Hides the form window, but keeps the form data.
     *
     * @return the current FilledForm object for method chaining
     */
    public Form hide() {
        if (window != null)
            window.setVisible(false);

        return this;
    }

    /**
     * Shows the window again, if it's hidden
     *
     * @return
     */
    public Form show() {
        if (window != null && !window.isVisible())
            window.setVisible(true);
        return this;
    }

    /**
     * Closes the form window and removes the form data.
     */
    public void close() {
        if (window != null)
            window.dispose();
    }

    public JDialog getWindow() {
        return window;
    }

    private List<FormElement> getAllFormElements() {
        List<FormElement> allElements = new ArrayList<>();

        for (FormElement element : elements) {

            if (element instanceof RowFormElement) {
                allElements.addAll(((RowFormElement) element).getElements());
            } else {
                allElements.add(element);
            }
        }

        return allElements;
    }

}
