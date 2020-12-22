package de.milchreis.uibooster.components;

import javax.swing.*;
import java.awt.*;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;

public class SimpleDialog extends JDialog {

    public SimpleDialog(String title, JPanel panel, WindowSetting setting, String iconImage) {
        super((Dialog) null, title, false);

        setTitle(title);
        setResizable(true);
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        applyWindowIcon(iconImage, this);

        if (setting != null) {
            setUndecorated(setting.isUndecorated());
        }

        setLayout(new BorderLayout());

        if (panel != null)
            add(panel, BorderLayout.CENTER);

        setResizable(true);
        pack();

        if (setting != null) {
            setSize(new Dimension(setting.getWidth(), setting.getHeight()));
            setLocation(setting.getPositionX(), setting.getPositionY());
            setVisible(setting.isVisible());

        } else {
            setLocationRelativeTo(null);
            setResizable(true);
            setVisible(true);
        }
    }

}
