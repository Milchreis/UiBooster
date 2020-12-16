package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;
import de.milchreis.uibooster.model.formelements.FilterableCheckboxListFormElement;

import java.util.List;

public class FilterableCheckboxListDialog {


    public static List<String> show(String message, String title, List<String> items, String iconPath) {

        FilterableCheckboxListFormElement element = new FilterableCheckboxListFormElement(message, items);

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(element.createComponent(null));
        DialogClosingState closingState = dialog.showDialog(message, title, iconPath);

        return closingState.isClosedByUser() ? null : (List<String>) element.getValue();
    }
}
