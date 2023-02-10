package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDialog extends TextAreaDialog {

    public static void showDialog(String message, String title, UiBoosterOptions options, Exception exception) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        exception.printStackTrace(printWriter);

        JPanel panel = createPanel(writer.toString(), 30, 80, false);

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(panel);
        dialog.showDialog(message, title, options.getIconPath());
    }

}
