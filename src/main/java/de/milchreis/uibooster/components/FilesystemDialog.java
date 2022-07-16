package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.UiBoosterOptions;
import de.milchreis.uibooster.utils.WindowIconHelper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import static javax.swing.JFileChooser.FILES_AND_DIRECTORIES;
import static javax.swing.JFileChooser.FILES_ONLY;

public class FilesystemDialog {


    public static File showFileSelectionDialog(UiBoosterOptions options) {
        return showFileSelectionDialog(null, options);
    }

    public static File showFileSelectionDialog(String currentDirectory, UiBoosterOptions options) {
        return showFsSelectionDialog(currentDirectory, FILES_ONLY, null, options, (String) null);
    }

    public static File showFileSelectionDialog(String currentDirectory, String description, UiBoosterOptions options, String... extensions) {
        return showFsSelectionDialog(currentDirectory, FILES_ONLY, description, options, extensions);
    }

    public static File showDirectorySelectionDialog(UiBoosterOptions options) {
        return showDirectorySelectionDialog(null, options);
    }

    public static File showDirectorySelectionDialog(String currentDirectory, UiBoosterOptions options) {
        return showFsSelectionDialog(currentDirectory, JFileChooser.DIRECTORIES_ONLY, null, options, (String) null);
    }

    public static File showFileOrDirectorySelectionDialog(UiBoosterOptions options) {
        return showFileOrDirectorySelectionDialog(null, options);
    }

    public static File showFileOrDirectorySelectionDialog(String currentDirectory, UiBoosterOptions options) {
        return showFsSelectionDialog(currentDirectory, FILES_AND_DIRECTORIES, null, options, (String) null);
    }

    public static File showFileOrDirectorySelectionDialog(String currentDirectory, String description, UiBoosterOptions options, String... extensions) {
        return showFsSelectionDialog(currentDirectory, FILES_AND_DIRECTORIES, description, options, extensions);
    }

    private static File showFsSelectionDialog(String currentDirectoryPath, int type, String description, UiBoosterOptions options, String... extensions) {

        JFrame frameWithIcon = new JFrame();
        frameWithIcon.setIconImage(WindowIconHelper.getIcon(options.getIconPath()).getImage());

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(type);

        if (currentDirectoryPath != null)
            chooser.setCurrentDirectory(new File(currentDirectoryPath));

        if ((type == FILES_ONLY || type == FILES_AND_DIRECTORIES) && extensions != null && extensions.length > 0) {
            chooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
        }

        int returnVal = chooser.showOpenDialog(frameWithIcon);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }

        return null;
    }

}
