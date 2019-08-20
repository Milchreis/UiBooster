package de.milchreis.uibooster;

import de.milchreis.uibooster.components.LoginDialog;
import de.milchreis.uibooster.model.LoginCredentials;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UiBoosterTest {


    @Test
    public void test_info_dialog() {
        UiBooster booster = new UiBooster();
        booster.showInfoDialog("Info message");
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

        System.out.println(credentials);
    }
}