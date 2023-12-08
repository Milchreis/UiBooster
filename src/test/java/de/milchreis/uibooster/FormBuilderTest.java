package de.milchreis.uibooster;

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

class FormBuilderTest {

    UiBooster booster = new UiBooster();

    @Test
    public void test_form_close_itself() {

        booster.createForm("Title")
                .addButton("Close window", (button, form) -> {
                            System.out.println("Close this window");
                        }
                ).setID("close")
                .setChangeListener((element, value, form) -> {
                    if (element.getId().equals("close")) {
                        form.close();
                    }
                }).show();
    }


    @Test
    public void test_form_dialog() {
        Form form = booster
                .createForm("Personal information")
                .addText("Whats your first name?")
                .addDatePicker("Whats your birthday?").setTooltip("Choose the date of your birthday...")
                .addTextArea("Tell me something about you")
                .addSelection(
                        "Whats your favorite movie?",
                        Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"))
                .addSelectionWithCheckboxes(
                        "Whats your favorite genres?",
                        Arrays.asList("Action", "Drama", "Comedy", "Romance", "Thriller", "Animation", "Fantasy",
                                "Adventure", "Crime", "Mystery", "Sci-fi", "Horror"))
                .addLabel("Choose an action")
                .addButton("half full", () -> booster.showInfoDialog("Optimist"))
                .addButton("half empty", (button, form1) -> booster.showInfoDialog("Pessimist"))
                .addSlider("How many liters did you drink today?", 0, 5, 1, 5, 1)
                .addCheckbox("Are you fine?", "yes")
                .addColorPicker("Favorite color?", new Color(212, 32, 39))
                .addFontChooser("Favorite font?", new Font(Font.SANS_SERIF, Font.PLAIN, 12))
                .setChangeListener((element, value, filledForm) -> System.out.println(
                        "Component " + element.getLabel() +
                                " at position " + element.getIndex() +
                                " changed to " + value.toString()))
                .show();

        if (form.isClosedByUser())
            System.exit(-1);
        System.out.println("closed by user");

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });
    }

    @Test
    public void test_form_dialog_with_list() {
        Form form = booster
                .createForm("Personal information")
                .addText("Whats your first name?")
                .addMultipleSelection("What are your hobbies?", "Reading", "Traveling", "Fishing", "Music", "Gardening", "Sport", "Television",
                        "Video Games", "Crafting", "Bird Watching", "Collecting")
                .show();

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });
    }

    @Test
    public void test_form_dialog_with_html() {
        Form form = booster
                .createForm("information")
                .addHtmlText("Some formatted text", "<h2>headline</h2><b>Some</b> <i>text</i> 🎈")
                .show();

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });
    }

    @Test
    public void test_form_textarea_size() {
        Form form = booster
                .createForm("Personal information")
                .addTextArea("Text with 1 row", 1)
                .addTextArea("Text with 2 row", 2)
                .addTextArea("Text with 3 row", 3)
                .addTextArea("Text with 4 row", 4)
                .addTextArea("Text with 5 row", 5)
                .addTextArea("Text with 6 row", 6)
                .show();

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });
    }


    @Test
    public void test_form_dialog_access_elements() {
        Form form = booster
                .createForm("Personal information")
                .addText("Whats your first name?").setID("name")
                .addMultipleSelection("What are your hobbies?", "Reading", "Traveling")
                .show();

        final FormElement byId = form.getById("name");
        final FormElement byIndex = form.getByIndex(0);
        assert byId == byIndex;

        assert form.getByIndex(1) != form.getByIndex(0);
        assert form.getByIndex(1) == form.getByLabel("What are your hobbies?");
    }

    @Test
    public void test_form_dialog_with_exit_action() throws InterruptedException {
        Form form = booster
                .createForm("Personal information")
                .addText("Whats your first name?").setID("name")
                .addMultipleSelection("What are your hobbies?", "Reading", "Traveling")
                .setCloseListener((f) -> {
                    System.out.println("Is form closed by user? " + f.isClosedByUser());
                })
                .run();

        sleep(15000);

        final FormElement byId = form.getById("name");
        final FormElement byIndex = form.getByIndex(0);
        assert byId == byIndex;

        assert form.getByIndex(1) != form.getByIndex(0);
        assert form.getByIndex(1) == form.getByLabel("What are your hobbies?");
    }

    @Test
    public void test_form_window_settings() throws InterruptedException {
        Form form = booster
                .createForm("Personal information")
                .addText("Whats your first name?").setID("name")
                .addMultipleSelection("What are your hobbies?", "Reading", "Traveling")
                .andWindow()
                .setSize(300, 800)
                .setUndecorated()
                .setPosition(300, 20)
                .save()
                .run();

        sleep(5000);
    }

    @Test
    public void test_picture_element() throws InterruptedException {
        Form form = booster
                .createForm("Images")
                .addImage("src/main/resources/uibooster-default-icon.png")
                .addImageCentered("src/main/resources/uibooster-default-icon.png")
                .andWindow()
                .setSize(500, 500).save()
                .run();

        sleep(5000);
    }

    @Test
    public void test_form_disable_elements() throws InterruptedException {

        Form form = booster
                .createForm("Personal information")
                .addText("Whats your first name?").setDisabled()
                .run();

        sleep(1000);
        form.getByIndex(0).setEnabled(true);

        sleep(1000);
        form.getByIndex(0).setEnabled(false);

        sleep(3000);
    }

    @Test
    public void test_disable_button_dialog() throws InterruptedException {
        Form form = booster
                .createForm("Test")
                .addButton("Prev", (button, form1) -> {}).setDisabled().setID("test")
                .run();

        sleep(1000);
        form.getByIndex(0).setEnabled(true);
    }

    @Test
    public void test_disable_button_in_row_dialog() throws InterruptedException {
        Form form = booster
                .createForm("Test")
                .startRow()
                .addButton("On", (button, form1) -> {}).setID("on1")
                .addButton("Off", (button, form1) -> {}).setDisabled().setID("off1")
                .addButton("On", (button, form1) -> {}).setID("on2")
                .endRow()
                .show();

        sleep(1000);
        form.getByIndex(0).setEnabled(true);
    }

    @Test
    public void test_form_list() {
        booster.createForm("Personal information")
                .addText("Whats your first name?").setID("name")
                .addList("What are your favorite hobby?",
                        new ListElement("Reading", "Books, Blogs, Magazines or comics"),
                        new ListElement("Traveling", "I love it to be not at home."),
                        new ListElement("Music", "Making, hearing or dancing to music ... all is allowed")
                ).setID("hobby")
                .setChangeListener((element, value, form) -> {
                    if (element.getId().equals("hobby")) {
                        System.out.println(element.getValue() + " was selected");
                    }
                })
                .show();
    }

    @Test
    public void test_form_table() {
        booster.createForm("Personal information")
                .addText("Favorite movies?")
                .addTable("My best friends", Arrays.asList("Name", "Age", "Favorite movie"), new String[][]{
                        {"Jimmy Johnson", "35", "Zombieland"},
                        {"Danny Durango", "23", "Hangover"},
                        {"Larry Berry", "54", ""}
                })
                .show();
    }

    @Test
    public void test_form_single_checkboxes() {
        final Form form = booster.createForm("Personal information")
                .addLabel("What are your favorite snacks?")
                .addCheckbox("potato chips", "crispy fried potato slices")
                .addCheckbox("pretzels")
                .addCheckbox("chocolate")
                .addCheckbox("I hate snacks").setDisabled()
                .show();

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });
    }

    @Test
    public void test_form_change_possibilities() throws InterruptedException {
        final Form form = booster.createForm("Personal information")
                .addSelection("What are your favorite snacks?", Arrays.asList(
                        "potato chips", "pretzels", "chocolate", "I hate snacks"))
                .run();

        form.getByIndex(0)
                .toSelection()
                .setPossibilities(Arrays.asList("apples", "bananas", "grapes"));

        sleep(10000);
    }

    @Test
    public void test_form_change_possibilities2() throws InterruptedException {
        final Form form = booster.createForm("Personal information")
                .addSelection("What are your favorite snacks?", elements)
                .run();

        sleep(3000);

        refresh();

        form.getByIndex(0)
                .toSelection()
                .setPossibilities(elements);

        sleep(10000);
    }

    @Test
    void test_form_with_changed_margins() {
        booster.createForm("Margin testing")
            .setMargin(0,0,0,0)
            .addSelection("Choose an option:", Arrays.asList("Option 1", "Option 2"))
            .addButton("Click me", () -> {}).setMargin(10,0,10,0)
            .show();
    }

    private List<String> elements = new ArrayList<>();

    public void refresh() {
        elements = new ArrayList<>();

        for (int i = 0; i < 10000; i++)
            elements.add("some string " + (i + 1));
    }

    @Test
    void test_form_with_init_listener() {
        booster.createForm("small form")
            .addText("Some text here ... ", ".......")
            .setInitListener(f -> System.out.println("This only called once"))
            .show();
    }

    @Test
    void test_custom_color_button() {
        booster.createForm("small form")
            .addButton("Default button", "click me", (e, f) -> {})
            .addButton("Colored button here", "click me", (e, f) -> {}, new Color(183, 128, 0), Color.WHITE)
            .addButton( "click me", (e, f) -> {}, new Color(71, 118, 65), Color.WHITE)
            .show();
    }

    @Test
    void test_change_custom_color_button() {
        booster.createForm("small form")
            .addButton("darker", (e, f) -> {
                    final ButtonFormElement button = e.toButton();
                    button.setBackgroundColor(button.getBackgroundColor().darker());
                },
                new Color(71, 118, 65), Color.WHITE)
            .show();
    }

}
