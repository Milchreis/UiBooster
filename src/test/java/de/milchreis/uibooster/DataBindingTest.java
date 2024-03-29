package de.milchreis.uibooster;

import de.milchreis.uibooster.model.Data;
import de.milchreis.uibooster.model.Form;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.ListElement;
import de.milchreis.uibooster.model.formelements.ButtonFormElement;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

class DataBindingTest {

    Data<String> name = new Data<>("Peter");
    Data<String> movie = new Data<>("Bambi");

    @Test
    public void test_simple_databinding() {
        new UiBooster()
            .createForm("Data binding")
            .addText("Whats your first name?").bind(name)
            .addButton("reset name", () -> name.set("Nick"))
            .show();
    }

    @Test
    public void test_databinding_dialog() {
        new UiBooster()
            .createForm("Personal information")
            .addText("Whats your first name?").bind(name)
            .addDatePicker("Whats your birthday?").setTooltip("Choose the date of your birthday...")
            .addTextArea("Tell me something about you")
            .addSelection(
                "Whats your favorite movie?",
                Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover")).bind(movie)
            .addSelectionWithCheckboxes(
                "Whats your favorite genres?",
                Arrays.asList("Action", "Drama", "Comedy", "Romance", "Thriller", "Animation", "Fantasy",
                    "Adventure", "Crime", "Mystery", "Sci-fi", "Horror"))
            .addLabel("Choose an action")
            .addButton("reset name", () -> name.set("Nick"))
            .addButton("reset movie", () -> movie.set("Bambi"))
            .addSlider("How many liters did you drink today?", 0, 5, 1, 5, 1)
            .addCheckbox("Are you fine?", "yes")
            .addColorPicker("Favorite color?", new Color(212, 32, 39))
            .addFontChooser("Favorite font?", new Font(Font.SANS_SERIF, Font.PLAIN, 12))
            .setChangeListener((element, value, filledForm) -> System.out.println("Movie: " + movie.getValue()))
            .show();
    }

}
