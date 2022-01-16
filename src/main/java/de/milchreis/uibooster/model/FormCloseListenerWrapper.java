package de.milchreis.uibooster.model;

public class FormCloseListenerWrapper {

    private final Form form;
    private final FormCloseListener closeListener;

    public FormCloseListenerWrapper(Form form, FormCloseListener closeListener) {
        this.form = form;
        this.closeListener = closeListener;
    }

    public void executeListener() {
        if (closeListener != null) {
            closeListener.onClose(form);
        }
    }

}
