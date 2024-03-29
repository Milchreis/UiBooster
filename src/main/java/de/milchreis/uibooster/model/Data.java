package de.milchreis.uibooster.model;

import java.util.function.Consumer;

public class Data<T> {

    private T value;
    private Consumer<T> listener;

    public Data(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void set(T value) {
        this.value = value;
        listener.accept(value);
    }

    public void setChangeListener(Consumer<T> listener) {
        this.listener = listener;
    }
}

