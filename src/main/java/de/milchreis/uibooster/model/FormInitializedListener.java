package de.milchreis.uibooster.model;

public interface FormInitializedListener {

    /**
     * This method is called, when the form is initialized by UiBooster. The listener
     * is called only once.
     *
     * @param form The created form is given.
     */
    void onInit(Form form);

}
