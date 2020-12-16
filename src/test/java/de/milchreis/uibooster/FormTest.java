package de.milchreis.uibooster;

import de.milchreis.uibooster.model.FilledForm;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class FormTest {

    UiBooster booster = new UiBooster();

    @Test
    public void test_form_dialog() {
        FilledForm form = booster
                .createForm("Personal information")
                .addText("Whats your first name?")
                .addDatePicker("Whats your birthday?")
                .addTextArea("Tell me something about you")
                .addSelection(
                        "Whats your favorite movie?",
                        Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"))
                .addLabel("Choose an action")
                .addButton("half full", () -> booster.showInfoDialog("Optimist"))
                .addButton("half empty", () -> booster.showInfoDialog("Pessimist"))
                .addSlider("How many liters did you drink today?", 0, 5, 1, 5, 1)
                .addColorPicker("Favorite color?")
                .setChangeListener((element, value) -> System.out.println(
                        "Component " + element.getLabel() +
                                " at position " + element.getIndex() +
                                " changed to " + value.toString()))
                .show();

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });
    }

    @Test
    public void test_form_dialog_with_list() {
        FilledForm form = booster
                .createForm("Personal information")
                .addText("Whats your first name?")
                .addMultipleSelection("What are your hobbies?", "Reading", "Traveling", "Fishing", "Music", "Gardening", "Sport", "Television",
                        "Video Games", "Crafting", "Bird Watching", "Collecting")
                .show();

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });
    }


}