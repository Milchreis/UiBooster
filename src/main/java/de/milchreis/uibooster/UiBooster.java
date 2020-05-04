package de.milchreis.uibooster;

import com.bulenkov.darcula.DarculaLaf;
import de.milchreis.uibooster.components.*;
import de.milchreis.uibooster.model.LoginCredentials;
import de.milchreis.uibooster.model.UiBoosterOptions;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static de.milchreis.uibooster.utils.ParameterValidator.nonNull;

/**
 * The UiBooster class it the main entry point to create a new dialog.
 * It provides all implemented dialogs.
 */
public class UiBooster {

    private UiBoosterOptions options;

    public UiBooster() {
        this(new UiBoosterOptions(UiBoosterOptions.Theme.DARK_THEME));
    }

    public UiBooster(UiBoosterOptions options) {
        this.options = options == null ? new UiBoosterOptions() : options;

        if (options.getTheme() != null) {

            try {
                if (options.getTheme() == UiBoosterOptions.Theme.DARK_THEME) {
                    UIManager.setLookAndFeel(new DarculaLaf());

                } else if (options.getTheme() == UiBoosterOptions.Theme.OS_NATIVE) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * Shows an info dialog and blocks until the ok button was clicked.
     * '\n' is allowed as newline character for the info message.
     *
     * @param infoMessage expects the info message
     */
    public void showInfoDialog(String infoMessage) {
        nonNull(infoMessage);
        JOptionPane.showMessageDialog(null, infoMessage);
    }

    /**
     * Shows a warning dialog and blocks until the ok button was clicked.
     * '\n' is allowed as newline character for the warn message.
     *
     * @param warningMessage expects the warn message
     * @param title          expects the title for the window
     */
    public void showWarningDialog(String warningMessage, String title) {
        nonNull(warningMessage);
        nonNull(title);
        JOptionPane.showMessageDialog(null, warningMessage, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Shows an error dialog and blocks until the ok button was clicked.
     * '\n' is allowed as newline character for the error message.
     *
     * @param errorMessage expects the error message
     * @param title        expects the title for the window
     */
    public void showErrorDialog(String errorMessage, String title) {
        nonNull(errorMessage);
        nonNull(title);
        JOptionPane.showMessageDialog(null, errorMessage, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows a dialog with text input field.
     *
     * @param message expects a message or question for the user.
     * @return the user input or null on cancel
     */
    public String showTextInputDialog(String message) {
        nonNull(message);
        return JOptionPane.showInputDialog(null, message);
    }

    /**
     * Shows a confirm dialog and blocks until a decision is selected.
     * '\n' is allowed as newline character for the message.
     *
     * @param message   expects the message
     * @param title     expects the window title
     * @param onConfirm expects a behaviour for a conformation
     * @param onDecline expects a behaviour for a declination
     */
    public void showConfirmDialog(String message, String title, Runnable onConfirm, Runnable onDecline) {
        nonNull(message);
        nonNull(title);
        int n = JOptionPane.showConfirmDialog(null,
                message,
                title,
                JOptionPane.YES_NO_OPTION);

        if (n == 0 && onConfirm != null)
            onConfirm.run();

        if (n == 1 && onDecline != null)
            onDecline.run();
    }

    /**
     * Shows an info dialog and blocks until the ok button was clicked.
     * '\n' is allowed as newline character for the info message.
     *
     * @param message       expects the message
     * @param title         expects the window title
     * @param possibilities expects a list with possibilities
     * @return the selection element form possibilities list or null on close
     */
    public String showSelectionDialog(String message, String title, List<String> possibilities) {
        return showSelectionDialog(message, title, possibilities.toArray(new String[]{}));
    }

    /**
     * Shows an info dialog and blocks until the ok button was clicked.
     * '\n' is allowed as newline character for the info message.
     *
     * @param message       expects the message
     * @param title         expects the window title
     * @param possibilities expects a list with possibilities
     * @return the selection element form possibilities list or null on close
     */
    public String showSelectionDialog(String message, String title, String... possibilities) {
        nonNull(message);
        nonNull(title);

        String selection = (String) JOptionPane.showInputDialog(
                null,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                possibilities[0]);

        return selection;
    }

    /**
     * Shows a simple color picker to select an RGB value.
     *
     * @param message optional message above the color picker
     * @param title   expects a window title
     * @return the selected color, on close it returns null.
     */
    public Color showColorPicker(String message, String title) {
        return ColorPickerDialog.showColorPicker(message, title, options.getIconPath());
    }

    /**
     * Shows a file selection dialog. Only files are shown and selectable
     *
     * @return returns the selection file or null on cancel
     */
    public File showFileSelection() {
        return FilesystemDialog.showFileSelectionDialog();
    }

    /**
     * Shows a directory selection dialog. Only directories are shown and selectable
     *
     * @return returns the selection directory or null on cancel
     */
    public File showDirectorySelection() {
        return FilesystemDialog.showDirectorySelectionDialog();
    }

    /**
     * Shows a selection dialog for files and directories.
     *
     * @return returns the selection or null on cancel
     */
    public File showFileOrDirectorySelection() {
        return FilesystemDialog.showFileOrDirectorySelectionDialog();
    }

    /**
     * Shows a login dialog with username and password.
     *
     * @return returns the login data or null on cancel.
     */
    public LoginCredentials showLogin(String message, String title, String usernameLabel, String passwordLabel,
                                      String loginButtonLabel, String cancelButtonLabel) {
        return new LoginDialog(
                title,
                message,
                usernameLabel,
                passwordLabel,
                loginButtonLabel,
                cancelButtonLabel,
                options.getIconPath()).showDialog();
    }

    /**
     * Shows a waiting dialog with a changeable message.
     * This dialog does not wait for any user input. Its decoupled from the rest of the application.
     *
     * @param message optional message to the user
     * @param title   expects a title for the window
     * @return returns the dialog object to change the message and hide the dialog as needed.
     */
    public WaitingDialog showWaitingDialog(String message, String title) {
        return WaitingDialog.showDialog(message, title, options);
    }

    /**
     * Shows a waiting dialog with a changeable message and a large (scrollable) message area.
     * This dialog does not wait for any user input. Its decoupled from the rest of the application.
     *
     * @param message   optional message to the user
     * @param title     expects a title for the window
     * @param largeText optional large message
     * @return returns the dialog object to change the message and hide the dialog as needed.
     */
    public WaitingDialog showWaitingDialog(String message, String title, String largeText) {
        return WaitingDialog.showDialog(message, title, largeText, options);
    }

    /**
     * Shows dialog with progressbar and changeable message.
     * The dialog does not wait for any user input. Its decoupled form the rest of the application.
     *
     * @param message optional message to the user
     * @param title   expects a title for the window
     * @param min     expects the minimal value for the progress
     * @param max     expects the maximal value for the progress
     * @return returns the dialog object to change the message and the progress and hide the dialog as needed.
     */
    public ProgressDialog showProgressDialog(String message, String title, int min, int max) {
        return ProgressDialog.showDialog(message, title, min, max);
    }

    /**
     * Shows a date picker dialog to choose a specific date.
     *
     * @param message expects a message
     * @param title   expects a title for the window
     * @return the selected date or null on cancel
     */
    public Date showDatePicker(String message, String title) {
        return DatePickerDialog.showDatePicker(message, title, options.getIconPath());
    }

    /**
     * Shows a dialog with a table view. It allows to change cell values, add rows and remove rows.
     * The dialogs wait for user input.
     *
     * @param data   expects the values in rows of columns -> [row_n][column_n]
     * @param header expects the labels for the columns
     * @param title  expects a title for the window
     * @return the accepted or changed data or null on cancel.
     */
    public String[][] showTable(String[][] data, List<String> header, String title) {
        return TableDialog.showTable(data, header, title, options.getIconPath(), true);
    }

    /**
     * Shows a dialog with a table view. The table values are not changeable.
     * The dialogs wait for user input.
     *
     * @param data   expects the values in rows of columns -> [row_n][column_n]
     * @param header expects the labels for the columns
     * @param title  expects a title for the window
     * @return the accepted or changed data or null on cancel.
     */
    public String[][] showTableImmutable(String[][] data, List<String> header, String title) {
        return TableDialog.showTable(data, header, title, options.getIconPath(), false);
    }

    /**
     * Creates a resizable window whichs shows the given image. The image is scaled with the
     * correct ratio. The dialogs blocks the process until it's closed.
     *
     * @param title expects a title for the window
     * @param image expects an image (JPG, PNG and BMP are supported).
     * @return the object to control the picture gallery dialog
     */
    public PictureGalleryDialog showPicture(String title, File image) {
        return PictureGalleryDialog.showDialog(title, options.getIconPath(), Arrays.asList(image));
    }

    /**
     * Creates a resizable window which shows the given images as a gallery. The images are scaled with the
     * correct ratio. The user can step forward and backwards thru the gallery.
     * The dialogs blocks the process until it's closed.
     *
     * @param title  expects a title for the window
     * @param images expects a list of images (JPG, PNG and BMP are supported).
     * @return the object to control the picture gallery dialog
     */
    public PictureGalleryDialog showPictures(String title, List<File> images) {
        return PictureGalleryDialog.showDialog(title, options.getIconPath(), images);
    }

    /**
     * Creates an object for building a form with selections, textfields and textareas.
     * The dialogs blocks the process until it's closed.
     *
     * @param title expects a title for the window
     * @return the object to create and control the form dialog
     */
    public Form createForm(String title) {
        return new Form(title, options);
    }


    /**
     * Creates an object for building a system tray menu.
     *
     * @param title    expects a title for the tray symbole
     * @param iconPath expects a path for the tray icon
     * @return the object to create and control the system tray
     */
    public TrayMenu createTrayMenu(String title, String iconPath) {
        return new TrayMenu(title, iconPath, null);
    }

    /**
     * Creates an object for building a system tray menu.
     *
     * @param title         expects a title for the tray symbole
     * @param iconPath      expects a path for the tray icon
     * @param onIconClicked expects an action which starts on clicking the tray icon
     * @return the object to create and control the system tray
     */
    public TrayMenu createTrayMenu(String title, String iconPath, Runnable onIconClicked) {
        return new TrayMenu(title, iconPath, onIconClicked);
    }

    /**
     * Creates a notification window with title and message. The appearance and the support depends
     * on the used OS.
     *
     * @param message expects a message for the notificaiton
     * @param title   expects a title for the notification
     */
    public void createNotification(String message, String title) {
        new Notification().display(title, message);
    }

    /**
     * Shows a dialog with a customizable slider, to ask the user for amounts.
     * The dialogs blocks the process until it's closed.
     *
     * @param message expects a message for the meaning of this selection
     * @param title   expects a title for the window
     * @param min     expects the lowest allowed value
     * @param max     expects the highest allowed value
     * @param init    expects the number to start with
     * @return the selected value or null if the dialog was closed
     */
    public Integer showSlider(String message, String title, int min, int max, int init) {
        return showSlider(message, title, min, max, init, 10, 1);
    }

    /**
     * Shows a dialog with a customizable slider, to ask the user for amounts.
     * The dialogs blocks the process until it's closed.
     *
     * @param message   expects a message for the meaning of this selection
     * @param title     expects a title for the window
     * @param min       expects the lowest allowed value
     * @param max       expects the highest allowed value
     * @param init      expects the number to start with
     * @param majorTick expects the number of steps for the labeled ticks
     * @param minorTick expects the number of steps for each tick
     * @return the selected value or null if the dialog was closed
     */
    public Integer showSlider(String message, String title, int min, int max, int init, int majorTick, int minorTick) {
        return SliderDialog.showDialog(message, title, min, max, init, majorTick, minorTick, options.getIconPath());
    }

    /**
     * Shows an undecorated window with a given image as splash screen. PNG files with
     * transparency are supported.
     *
     * @param imagePath expects the path to splash screen image
     * @return a splash screen object to hide or show the screen.
     */
    public Splashscreen showSplashscreen(String imagePath) {
        return new Splashscreen(imagePath, -1, 1, options.getIconPath());
    }

    /**
     * Shows an undecorated window with a given image as splash screen. PNG files with
     * transparency are supported.
     *
     * @param imagePath        expects the path to splash screen image
     * @param lifeTimeInMillis defines the time for showing the splash screen in milli seconds, after that the screen closes automatically
     * @return a splash screen object to hide or show the screen.
     */
    public Splashscreen showSplashscreen(String imagePath, int lifeTimeInMillis) {
        return new Splashscreen(imagePath, lifeTimeInMillis, 1, options.getIconPath());
    }
}
