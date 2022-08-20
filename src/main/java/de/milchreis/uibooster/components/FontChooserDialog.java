package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;
import java.awt.Font;
import org.drjekyll.fontchooser.FontChooser;

public class FontChooserDialog {

    public static Font showFontChooser(String message, String title, String iconPath) {
        return showFontChooser(message, title, null, iconPath);
    }

    public static Font showFontChooser(String message, String title, Font color, String iconPath) {

        FontChooser fontChooser = createFontChooser(color);

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(fontChooser);
        DialogClosingState closingState = dialog.showDialog(message, title, iconPath);

        return closingState.isClosedByUser() ? null : fontChooser.getFont();
    }

    public static FontChooser createFontChooser(Font initialFont) {
        FontChooser fontChooser;
        if (initialFont == null) {
            fontChooser = new FontChooser();
        } else {
            fontChooser = new FontChooser(initialFont);
        }
        fontChooser.setVisible(true);
        return fontChooser;
    }

}
