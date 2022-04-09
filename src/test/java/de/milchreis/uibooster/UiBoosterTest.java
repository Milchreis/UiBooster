package de.milchreis.uibooster;

import de.milchreis.uibooster.components.ProgressDialog;
import de.milchreis.uibooster.components.Splashscreen;
import de.milchreis.uibooster.components.WaitingDialog;
import de.milchreis.uibooster.model.ListElement;
import de.milchreis.uibooster.model.LoginCredentials;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

class UiBoosterTest {

    UiBooster booster = new UiBooster();

    @Test
    public void test_info_dialog() {
        booster.showInfoDialog("Info message");
    }

    @Test
    public void test_warn_dialog() {
        booster.showWarningDialog("Warning message", "WARN");
    }

    @Test
    public void test_error_dialog() {
        booster.showErrorDialog("Error message", "ERROR");
    }

    @Test
    public void test_textinput_dialog() {
        String opinion = booster.showTextInputDialog("What do you think?");
        System.out.println(opinion);
    }

    @Test
    public void test_password_dialog() {
        String password = booster.showPasswordDialog("Whats your password?", "Password");
        System.out.println(password);
    }

    @Test
    public void test_confirm_dialog() {
        booster.showConfirmDialog(
                "Do you really want this action?",
                "Are you sure?",
                () -> System.out.println("Action accepted"),
                () -> System.out.println("Action declined"));
    }

    @Test
    public void test_selection_dialog() {
        String selection = booster.showSelectionDialog(
                "What's your favorite movie?",
                "Favorite Movie?",
                Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"));

        System.out.println(selection);
    }

    @Test
    public void test_colorpicker_dialog() {
        Color selectedColor = booster.showColorPicker("Choose your favorite color", "Color picking");
        System.out.println(selectedColor);
    }

    @Test
    public void test_colorpicker_initialcolor_dialog() {
        Color selectedColor = booster.showColorPicker("Choose your favorite color", "Color picking", new Color(210, 123, 43));
        System.out.println(selectedColor);
    }

    @Test
    void test_list_selection_dialog() {

        final ListElement selectedElement = booster.showList("What's your favorite movie",
                "Favorite movie",
                new ListElement("Pulp Fiction", "Director: Quentin Tarantino"),
                new ListElement("Bambi", "Directors: James Algar, Sam Armstrong"),
                new ListElement("The Godfather", "Director: Francis Ford Coppola"),
                new ListElement("Hangover", "Director: Todd Phillips")
        );

        System.out.println(selectedElement);
    }

    @Test
    public void test_fileselection_dialog() {
        File file = booster.showFileSelection();
        System.out.println(file);

        File pdfFiles = booster.showFileSelection("PDF files", "pdf");
        System.out.println(pdfFiles);

        File directory = booster.showDirectorySelection();
        System.out.println(directory);

        File fileOrDirectory = booster.showFileOrDirectorySelection();
        System.out.println(fileOrDirectory);

        File pdfFileOrDirectory = booster.showFileOrDirectorySelection("PDF files", "pdf");
        System.out.println(pdfFileOrDirectory);
    }

    @Test
    public void test_fileselection_with_path_dialog() {
        String currentPath = "./";

        File file = booster.showFileSelectionFromPath(currentPath);
        System.out.println(file);

        File pdfFiles = booster.showFileSelectionFromPath(currentPath, "PDF files", "pdf");
        System.out.println(pdfFiles);

        File directory = booster.showDirectorySelectionFromPath(currentPath);
        System.out.println(directory);

        File fileOrDirectory = booster.showFileOrDirectorySelectionFromPath(currentPath);
        System.out.println(fileOrDirectory);

        File pdfFileOrDirectory = booster.showFileOrDirectorySelectionFromPath(currentPath, "PDF files", "pdf");
        System.out.println(pdfFileOrDirectory);
    }

    @Test
    public void test_login_dialog() {
        LoginCredentials credentials = booster.showLogin(
                "Login",
                "Internal area",
                "Username",
                "Password",
                "Go",
                "Cancel");

        if (credentials != null)
            System.out.println(credentials.getUsername() + " - " + credentials.getPassword());
        else
            System.out.println("No credentials");
    }

    @Test
    public void test_waiting_dialog() {
        WaitingDialog dialog = booster.showWaitingDialog("Starting", "Please wait");
        sleep(1000);
        dialog.setMessage("Initializing");
        dialog.setLargeMessage("Some more information...\nMaybe from log files or other resources. \nBe transparent to the user to understand long processes...");
        sleep(2000);
        dialog.setMessage("Ready");
        dialog.addToLargeMessage("Ready!!!");
        sleep(500);
        dialog.close();
    }

    @Test
    public void test_waiting_dialog_with_titlebar() {
        WaitingDialog dialog = booster.showWaitingDialog("Starting", "Please wait", true);
        sleep(1000);
        dialog.setMessage("Initializing");
        dialog.setLargeMessage("Some more information...\nMaybe from log files or other resources. \nBe transparent to the user to understand long processes...");
        sleep(2000);
        dialog.setMessage("Ready");
        dialog.addToLargeMessage("Ready!!!");
        sleep(500);
        dialog.close();
    }

    @Test
    public void test_progress_dialog() {

        ProgressDialog dialog = booster.showProgressDialog("Please wait", "Waiting", 0, 120);

        for (int i = 0; i < 11; i++) {
            dialog.setProgress(i * 12);
            sleep(100);
        }

        dialog.setMessage("Ready");
        sleep(500);
        dialog.close();
    }

    @Test
    public void test_datepicker_dialog() {
        Date birthday = booster.showDatePicker("What's your birthday?", "Birthday");
        System.out.println(birthday);
    }

    @Test
    public void test_table_dialog() {

        String[][] modifiedData = booster.showTable(
                new String[][]{
                        {"Jimmy Johnson", "35", "Zombieland"},
                        {"Danny Durango", "23", "Hangover"},
                        {"Larry Berry", "54", ""}
                },
                Arrays.asList("Name", "Age", "Favorite movie"),
                "Favorite movies");

        Stream.of(modifiedData).forEach(row -> System.out.println(String.join(", ", row)));
    }

    @Test
    public void test_picture_dialog() {
        new UiBooster().showPictures("My picture", Arrays.asList(
                new File("screenshots/color.jpg"),
                new File("screenshots/dateselection.jpg"),
                new File("screenshots/table.jpg"),
                new File("screenshots/confirm.jpg")));
    }

    @Test
    public void test_tray_dialog() {
        booster.createTrayMenu("Food", "screenshots/color.jpg")
                .withPopupMenu()
                .addMenu("Hotdogs", () -> booster.showInfoDialog("Sausage in a roll"))
                .addMenu("Fries", () -> booster.showInfoDialog("Fried potatoes"))
                .addMenu("Pizza", () -> booster.showInfoDialog("Dough with tomato sauce"));
    }

    @Test
    public void test_notification() {
        booster.createNotification("Dinner is ready", "It's hot and delicious");
        sleep(5000);
    }


    @Test
    public void test_slider_dialog() {
        Integer numberOfHotDogs = booster.showSlider("Your order", "How many HotDogs do you want?",
                0, 10, 2, 5, 1);
        System.out.println(numberOfHotDogs);
    }

    @Test
    void test_splashscreen_dialog() {
        Splashscreen splash = booster.showSplashscreen("src/main/resources/uibooster-default-icon.png");
        sleep(5000);
        splash.hide();
    }

    @Test
    void test_stacktrace_dialog() {
        booster.showException("An error occurred", "Exception message", new Exception("Something went wrong ..."));
    }

    @Test
    void test_list_dialog() {
        ListElement selectedElement = booster.showList("Select a robot", "Avatars from RoboHash.org",
                element -> System.out.println("Selected: " + element.toString()),
                new ListElement("Robo 1", "Green and strong", "src/test/resources/avatar1.png"),
                new ListElement("Robo 2", "Shy without an avatar!"),
                new ListElement("Robo 3", "- Crazy\n- Fast\n- Funny", "src/test/resources/avatar2.png"),
                new ListElement("Robo 4", null, "src/test/resources/avatar3.png"));

        assert selectedElement != null;
        System.out.println(selectedElement);
    }

    @Test
    void test_checkbox_list_dialog() {
        List<String> selectedElement = booster.showMultipleSelection(
                "What are your favorite hobbies?",
                "Your hobbies",
                "Reading", "Traveling", "Fishing", "Music", "Gardening", "Sport", "Television",
                "Video Games", "Crafting", "Bird Watching", "Collecting");

        assert selectedElement != null;
        System.out.println(selectedElement);
    }


    private void sleep(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}