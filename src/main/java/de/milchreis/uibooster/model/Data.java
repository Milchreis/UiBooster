package de.milchreis.uibooster.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Data<T> {

    private T value;
    private List<Consumer<T>> listener = new ArrayList<>();

    public Data(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void set(T value) {
        this.value = value;
        listener.forEach(l -> l.accept(value));
    }

    /**
     * This method will be deleted in the next version.
     * Please use addChangeListener()
     */
    @Deprecated
    public void setChangeListener(Consumer<T> listener) {
        this.listener.add(listener);
    }

    public void addChangeListener(Consumer<T> listener) {
        this.listener.add(listener);
    }
}

