package de.milchreis.uibooster.components;

import javax.swing.*;
import java.awt.*;

public class SimpleDialog extends JDialog {

    public SimpleDialog(String title, JPanel panel) {

        super((Dialog) null, title, false);
        setTitle(title);
        setResizable(true);
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(panel, BorderLayout.CENTER);
        setResizable(true);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

}
