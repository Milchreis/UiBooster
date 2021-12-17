package de.milchreis.uibooster.components;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import static javax.swing.JFileChooser.FILES_AND_DIRECTORIES;
import static javax.swing.JFileChooser.FILES_ONLY;

public class FilesystemDialog {

    public static File showFileSelectionDialog() {
        return showFileSelectionDialog(null);
    }

    public static File showFileSelectionDialog(String currentDirectory) {
        return showFsSelectionDialog(currentDirectory, FILES_ONLY, null, null);
    }

    public static File showFileSelectionDialog(String currentDirectory, String description, String... extensions) {
        return showFsSelectionDialog(currentDirectory, FILES_ONLY, description, extensions);
    }

    public static File showDirectorySelectionDialog() {
        return showDirectorySelectionDialog(null);
    }

    public static File showDirectorySelectionDialog(String currentDirectory) {
        return showFsSelectionDialog(currentDirectory, JFileChooser.DIRECTORIES_ONLY, null, null);
    }

    public static File showFileOrDirectorySelectionDialog() {
        return showFileOrDirectorySelectionDialog(null);
    }

    public static File showFileOrDirectorySelectionDialog(String currentDirectory) {
        return showFsSelectionDialog(currentDirectory, FILES_AND_DIRECTORIES, null, null);
    }

    public static File showFileOrDirectorySelectionDialog(String currentDirectory, String description, String... extensions) {
        return showFsSelectionDialog(currentDirectory, FILES_AND_DIRECTORIES, description, extensions);
    }

    private static File showFsSelectionDialog(String currentDirectoryPath, int type, String description, String... extensions) {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(type);

        if (currentDirectoryPath != null)
            chooser.setCurrentDirectory(new File(currentDirectoryPath));

        if ((type == FILES_ONLY || type == FILES_AND_DIRECTORIES) && extensions != null && extensions.length > 0) {
            chooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
        }

        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }

        return null;
    }

}
