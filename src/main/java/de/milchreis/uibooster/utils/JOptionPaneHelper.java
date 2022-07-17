package de.milchreis.uibooster.utils;

import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;
import java.awt.*;

import static de.milchreis.uibooster.utils.ParameterValidator.nonNull;
import static javax.swing.JOptionPane.*;

public class JOptionPaneHelper {

    public static int showConfirmDialog(String message, String title, UiBoosterOptions options) {

        JOptionPane jp = new JOptionPane(message, QUESTION_MESSAGE, YES_NO_OPTION, null, null, null);

        jp.setInitialValue(null);
        jp.setComponentOrientation(getRootFrame().getComponentOrientation());

        createDialog(jp, getDefaultTitleIfNull(title), options);

        Object selectedValue = jp.getValue();

        if (selectedValue == null)
            return CLOSED_OPTION;
        if (selectedValue instanceof Integer)
            return (Integer) selectedValue;
        return CLOSED_OPTION;
    }

    public static String showInputDialog(String message, UiBoosterOptions options) {
        nonNull(message);
        JOptionPane jp = new JOptionPane(message, QUESTION_MESSAGE, OK_CANCEL_OPTION, null, null, null);

        jp.setWantsInput(true);
        jp.setSelectionValues(null);
        jp.setInitialSelectionValue(null);
        jp.setComponentOrientation(getRootFrame().getComponentOrientation());

        createDialog(jp, UIManager.getString("OptionPane.inputDialogTitle", null), options);

        Object value = jp.getInputValue();

        if (value == UNINITIALIZED_VALUE) {
            return null;
        }

        return value.toString();
    }

    public static String showSelectionDialog(String message, String title, UiBoosterOptions options, String[] possibilities) {

        JOptionPane jp = new JOptionPane(message, PLAIN_MESSAGE, OK_CANCEL_OPTION, null, null, null);

        jp.setInitialValue(null);
        jp.setComponentOrientation(getRootFrame().getComponentOrientation());
        jp.setSelectionValues(possibilities);
        jp.setInitialSelectionValue(possibilities[0]);

        createDialog(jp, getDefaultTitleIfNull(title), options);

        Object value = jp.getInputValue();

        if (value == UNINITIALIZED_VALUE) {
            return null;
        }
        return value.toString();
    }

    private static void createDialog(JOptionPane jp, String String, UiBoosterOptions options) {
        JDialog dialog = jp.createDialog(null, String);
        ((Frame) dialog.getParent()).setIconImage(WindowIconHelper.getIcon(options.getIconPath()).getImage());
        dialog.setIconImage(WindowIconHelper.getIcon(options.getIconPath()).getImage());
        dialog.setVisible(true);
    }

    private static String getDefaultTitleIfNull(String title) {
        return title == null
                ? UIManager.getString("OptionPane.titleText")
                : title;
    }
}
