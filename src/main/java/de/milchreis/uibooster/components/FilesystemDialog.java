package de.milchreis.uibooster.components;

import javax.swing.*;
import java.io.File;

public class FilesystemDialog {

    public static File showFileSelectionDialog() {
        return showFsSelectionDialog(JFileChooser.FILES_ONLY);
    }

    public static File showDirectorySelectionDialog() {
        return showFsSelectionDialog(JFileChooser.DIRECTORIES_ONLY);
    }

    public static File showFileOrDirectorySelectionDialog() {
        return showFsSelectionDialog(JFileChooser.FILES_AND_DIRECTORIES);
    }

    private static File showFsSelectionDialog(int type) {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(type);

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }

        return null;
    }

}
