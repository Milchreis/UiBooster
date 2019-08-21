package de.milchreis.uibooster;

import de.milchreis.uibooster.components.DatePickerDialog;
import de.milchreis.uibooster.components.WaitingDialog;
import de.milchreis.uibooster.model.LoginCredentials;
import de.milchreis.uibooster.model.UiBoosterOptions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

class UiBoosterTest {


    @Test
    public void test_info_dialog() {
        UiBooster booster = new UiBooster();
        booster.showInfoDialog("Info message");
    }

    @Test
    public void test_warn_dialog() {
        UiBooster booster = new UiBooster();
        booster.showWarningDialog("Warning message", "WARN");
    }

    @Test
    public void test_error_dialog() {
        UiBooster booster = new UiBooster();
        booster.showErrorDialog("Error message", "ERROR");
    }

    @Test
    public void test_textinput_dialog() {
        UiBooster booster = new UiBooster(new UiBoosterOptions(true));
        String opinion = booster.showTextInputDialog("What do you think?");
        System.out.println(opinion);
    }

    @Test
    public void test_confirm_dialog() {
        UiBooster booster = new UiBooster();
        booster.showConfirmDialog(
                "Do you really want this action?",
                "Are you sure?",
                () -> System.out.println("Action accepted"),
                () -> System.out.println("Action declined"));
    }

    @Test
    public void test_selection_dialog() {

        UiBooster booster = new UiBooster();

        String selection = booster.showSelectionDialog(
                "What's your favorite movie?",
                "Favorite Movie?",
                Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"));

        System.out.println(selection);
    }

    @Test
    public void test_colorpicker_dialog() {
        UiBooster booster = new UiBooster();
        Color selectedColor = booster.showColorPicker("Choose your favorite color", "Color picking");
        System.out.println(selectedColor);
    }

    @Test
    public void test_fileselection_dialog() {
        UiBooster booster = new UiBooster();
        File file = booster.showFileSelection();
        System.out.println(file);

        File directory = booster.showDirectorySelection();
        System.out.println(directory);

        File fileOrDirectory = booster.showFileOrDirectorySelection();
        System.out.println(fileOrDirectory);
    }

    @Test
    public void test_login_dialog() {
        UiBooster booster = new UiBooster();
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
        UiBooster booster = new UiBooster();
        WaitingDialog dialog = booster.showWaitingDialog("Starting", "Please wait");

        Thread.sleep(1000);
        dialog.setMessage("Initializing");
        Thread.sleep(2000);
        dialog.setMessage("Ready");
        Thread.sleep(500);
        dialog.close();
    }

    @Test
    public void test_datepicker_dialog() {
        UiBooster booster = new UiBooster();
        Date birthday = booster.showDatePicker("What's your birthday?", "Birthday");
        System.out.println(birthday);
    }
}