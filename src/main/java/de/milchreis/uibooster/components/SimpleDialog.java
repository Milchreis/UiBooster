package de.milchreis.uibooster.components;

import javax.swing.*;
import java.awt.*;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;

public class SimpleDialog extends JDialog {

    public SimpleDialog(String title, JPanel panel, String iconImage) {

        super((Dialog) null, title, false);
        setTitle(title);
        setResizable(true);
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        applyWindowIcon(iconImage, this);

        setLayout(new BorderLayout());

        if (panel != null)
            add(panel, BorderLayout.CENTER);

        setResizable(true);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

}
