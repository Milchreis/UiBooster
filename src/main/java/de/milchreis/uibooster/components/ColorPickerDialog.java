package de.milchreis.uibooster.components;

import com.bric.colorpicker.ColorPicker;
import de.milchreis.uibooster.model.DialogClosingState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ColorPickerDialog {

    public static Color showColorPicker(String message, String title) {

        DialogClosingState closingState = new DialogClosingState();

        ColorPicker picker = new ColorPicker(false,false);
        picker.setColor(new Color(219, 185, 47));
        picker.setRGBControlsVisible(false);
        picker.setHexControlsVisible(false);
        picker.setPreviewSwatchVisible(false);
        picker.setHSBControlsVisible(false);
        picker.setVisible(true);

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new Object[]{message, picker});

        JDialog dialog = optionPane.createDialog(null, title);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closingState.setClosedByUser(true);
            }
        });

        dialog.setVisible(true);
        dialog.dispose();

        return closingState.isClosedByUser() ? null : picker.getColor();
    }

}
