package de.milchreis.uibooster;

import de.milchreis.uibooster.components.ProgressDialog;
import de.milchreis.uibooster.components.WaitingDialog;
import de.milchreis.uibooster.model.FilledForm;
import de.milchreis.uibooster.model.LoginCredentials;
import de.milchreis.uibooster.model.UiBoosterOptions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

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
    public void test_fileselection_dialog() {
        File file = booster.showFileSelection();
        System.out.println(file);

        File directory = booster.showDirectorySelection();
        System.out.println(directory);

        File fileOrDirectory = booster.showFileOrDirectorySelection();
        System.out.println(fileOrDirectory);
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

        if(credentials != null)
            System.out.println(credentials.getUsername() + " - " + credentials.getPassword());
        else
            System.out.println("No credentials");
    }

    @Test
    public void test_waiting_dialog() throws InterruptedException {
        WaitingDialog dialog = booster.showWaitingDialog("Starting", "Please wait");
        Thread.sleep(1000);
        dialog.setMessage("Initializing");
        dialog.setLargeMessage("Some more information...\nMaybe from log files or other resources. \nBe transparent to the user to understand long processes...");
        Thread.sleep(2000);
        dialog.setMessage("Ready");
        dialog.addToLargeMessage("Ready!!!");
        Thread.sleep(500);
        dialog.close();
    }

    @Test
    public void test_progress_dialog() throws InterruptedException {

        ProgressDialog dialog = booster.showProgressDialog("Please wait", "Waiting", 0, 120);

        for(int i=0; i< 11; i++) {
            dialog.setProgress(i * 12);
            Thread.sleep(100);
        }

        dialog.setMessage("Ready");
        Thread.sleep(500);
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

        System.out.println(modifiedData);

    }

    @Test
    public void test_picture_dialog() throws IOException, InterruptedException {
        new UiBooster().showPictures("My picture",  Arrays.asList(
                new File("screenshots/color.jpg"),
                new File("screenshots/dateselection.jpg"),
                new File("screenshots/table.jpg"),
                new File("screenshots/confirm.jpg")));
    }

    @Test
    public void test_form_dialog() {
        FilledForm form = booster
                .createForm("Personal information")
                .addText("Whats your first name?")
                .addTextArea("Tell me something about you")
                .addSelection(
                        "Whats your favorite movie?",
                        Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"))
                .addLabel("Choose an action")
                .addButton("half full", () -> booster.showInfoDialog("Optimist"))
                .addButton("half empty", () -> booster.showInfoDialog("Pessimist"))
                .show();

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });

    }

    @Test
    public void test_tray_dialog() {
        booster.createTrayMenu("Food", "screenshots/color.jpg")
                .withPopupMenu()
                .addMenu("Hotdogs", () -> booster.showInfoDialog("Sausage in a roll"))
                .addMenu("Fries", () -> booster.showInfoDialog("Fried potatoes"))
                .addMenu("Pizza", () -> booster.showInfoDialog("Dough with tomato sauce"));
    }
}