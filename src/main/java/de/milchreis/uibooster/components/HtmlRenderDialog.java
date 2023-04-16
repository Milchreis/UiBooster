package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HtmlRenderDialog {

    public static void showDialog(String label, String title, String content, int width, int height, UiBoosterOptions options) {
        JPanel panel = createPanel(content, width, height);
        SimpleBlockingDialog dialog = new SimpleBlockingDialog(panel);
        dialog.showDialog(label, title, options.getIconPath());
    }

    protected static JPanel createPanel(String content, int width, int height) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JEditorPane editorPane = new JEditorPane("text/html", content);
        editorPane.setPreferredSize(new Dimension(width, height));
        panel.add(new JScrollPane(editorPane), BorderLayout.CENTER);
        return panel;
    }

}
