package de.milchreis.uibooster.model;

public class FormBuilderElementTyped<T> extends FormBuilder {

    public FormBuilderElementTyped(FormBuilder formBuilder) {
        super(formBuilder);
    }

    public FormBuilderElementTyped<T> bind(Data<T> dataReference) {
        final FormElement<T> latestElement = getLatestElement();
        latestElement.setBinding(dataReference);
        return this;
    }

    /**
     * Defines a custom ID or name to the lastly added element. It helps to identify this
     * element in a custom change listener.
     *
     * @param id expects an unique name or id for last added input element.
     */
    public FormBuilderElementTyped<T> setID(String id) {
        if (formElements.isEmpty())
            return this;

        getLatestElement().setId(id);
        return this;
    }

    /**
     * Sets a message for the user, which explains the input element. It appears on mouse-over.
     *
     * @param tooltip a message for the user
     */
    public FormBuilderElementTyped<T> setTooltip(String tooltip) {
        if (formElements.isEmpty())
            return this;

        getLatestElement().setTooltip(tooltip);

        return this;
    }

    /**
     * Disables the lastly added element, if it supports this state.
     */
    public FormBuilderElementTyped<T> setDisabled() {
        if (formElements.isEmpty())
            return this;

        initialElementsDisabled.add(getLatestElement().getId());
        return this;
    }
}
