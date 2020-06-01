package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;

import javax.swing.*;

public class PasswordDialog {

    public static String show(String message, String title, String iconPath) {

        JPasswordField passwordField = new JPasswordField();

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(passwordField);
        DialogClosingState closingState = dialog.showDialog(message, title, iconPath);

        return closingState.isClosedByUser() ? null : new String(passwordField.getPassword());
    }

}
