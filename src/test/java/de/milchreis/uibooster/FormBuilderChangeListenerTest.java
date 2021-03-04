package de.milchreis.uibooster;

import de.milchreis.uibooster.model.formelements.TableFormElement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

class FormBuilderChangeListenerTest {

    UiBooster booster = new UiBooster();

    @Test
    public void test_form_dialog() {
        booster.createForm("Input")
                .addText("First name of your best friend").setID("first")
                .addText("Last name of your best friend").setID("last")
                .addDatePicker("Birthday of your best friend").setID("birthday")
                .addButton("Submit", () -> {
                }).setID("submit")
                .addTable("Your friends", Arrays.asList("first name", "last name", "birthday"), new String[][]{}, false).setID("table")
                .setChangeListener((element, value, form) -> {

                    if (element.getId().equals("submit")) {
                        String firstName = form.getById("first").asString();
                        String lastName = form.getById("last").asString();
                        Date birthday = form.getById("birthday").asDate();

                        final TableFormElement table = (TableFormElement) form.getById("table");
                        table.addRow(new String[]{firstName, lastName, birthday.toString()});

                        form.getById("first").setValue("");
                        form.getById("last").setValue("");
                    }
                })
                .show();

    }
}