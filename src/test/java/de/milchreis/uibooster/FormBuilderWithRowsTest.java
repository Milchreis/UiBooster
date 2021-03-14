package de.milchreis.uibooster;

import de.milchreis.uibooster.model.Form;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class FormBuilderWithRowsTest {

    UiBooster booster = new UiBooster();

    @Test
    public void test_form_dialog_with_rows() {
        Form form = booster
                .createForm("Personal information")
                .addText("Whats your first name?")
                .startRow("Select one")
                .addButton(" ", "half full", () -> booster.showInfoDialog("Optimist")).setID("btn1")
                .addButton(" ", "half empty", () -> booster.showInfoDialog("Pessimist"))
                .addText("?").setID("my")
                .endRow()
                .addSlider("How many liters did you drink today?", 0, 5, 1, 5, 1)
                .addColorPicker("Favorite color?")
                .setChangeListener((element, value, filledForm) -> System.out.println(
                        "Component " + element.getLabel() +
                                " at position " + element.getIndex() +
                                " changed to " + value.toString()))
                .show();

        assert form.getByIndex(1).getId().equals("btn1");
        assert form.getById("my") != null;

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });
    }

    @Test
    public void calculator() {
        booster.createForm("Calculator")
                .addText("Result", "", true).setID("result")
                .startRow()
                .addButton("7", () -> {
                })
                .addButton("8", () -> {
                })
                .addButton("9", () -> {
                })
                .endRow()
                .startRow()
                .addButton("4", () -> {
                })
                .addButton("5", () -> {
                })
                .addButton("6", () -> {
                })
                .endRow()
                .startRow()
                .addButton("1", () -> {
                })
                .addButton("2", () -> {
                })
                .addButton("3", () -> {
                })
                .endRow()
                .startRow()
                .addButton("0", () -> {
                })
                .addButton("+", () -> {
                }).setID("plus")
                .addButton("-", () -> {
                }).setID("minus")
                .endRow()
                .addButton("=", () -> {
                })
                .setChangeListener((element, value, filledForm) -> {
                    String input = filledForm.getById("result").getValue() + "";

                    filledForm.getById("plus").setEnabled(!value.equals("+") && !value.equals("-"));
                    filledForm.getById("minus").setEnabled(!value.equals("-") && !value.equals("+"));

                    if (value.equals("=")) {
                        final Integer result = calculate(input);
                        input = result != null ? result.toString() : "ERROR";

                    } else {
                        input += value;
                    }

                    filledForm.getById("result").setValue(input);
                })
                .show();

    }

    private Integer calculate(String input) {
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("JavaScript");
            return Integer.parseInt(engine.eval(input).toString());
        } catch (ScriptException e) {
            e.printStackTrace();
            return null;
        }
    }

}