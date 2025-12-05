package de.milchreis.uibooster;

import de.milchreis.uibooster.model.Form;
import de.milchreis.uibooster.model.Weights;
import org.junit.jupiter.api.Test;

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
                    .addText("?").setID("?")
                .endRow()
                .addSlider("How many liters did you drink today?", 0, 5, 1, 5, 1)
                .addColorPicker("Favorite color?")
                .addFontChooser("Favorite font?")
                .setChangeListener((element, value, filledForm) -> System.out.println(
                        "Component " + element.getLabel() +
                                " at position " + element.getIndex() +
                                " changed to " + value.toString()))
                .show();

        assert form.getByIndex(1).getId().equals("btn1");
        assert form.getById("?") != null;

        form.getElements().forEach(e -> System.out.println(e.getLabel() + " -> " + e.getValue()));
    }

    @Test
    public void calculator() {
        booster.createForm("Calculator")
                .setMargin(0,0,0,0)
                .addText("Result", "", true).setID("result").setMargin(0,0,0,5)
                .startRow()
                    .addButton("7", () -> {})
                    .addButton("8", () -> {})
                    .addButton("9", () -> {})
                .endRow()
                .startRow()
                    .addButton("4", () -> {})
                    .addButton("5", () -> {})
                    .addButton("6", () -> {})
                .endRow()
                .startRow()
                    .addButton("1", () -> {})
                    .addButton("2", () -> {})
                    .addButton("3", () -> {})
                .endRow()
                .startRow(0,0, 0, 10)
                    .addButton("0", () -> {})
                    .addButton("+", () -> {}).setID("plus")
                    .addButton("-", () -> {}).setID("minus")
                .endRow()
                .addButton("=", () -> {})
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

    @Test
    public void test_form_dialog_with_rows_and_disable() {
        Form form = booster
            .createForm("Personal information")
            .addText("Whats your first name?")
            .startRow("Select one")
                .addButton(" ", "half full", () -> booster.showInfoDialog("Optimist")).setID("btn1")
                .addButton(" ", "half empty", () -> booster.showInfoDialog("Pessimist")).setDisabled()
            .endRow()
            .addButton("Favorite font?", (Runnable) null).setDisabled()
            .startRow("Select one")
                .addText("?").setID("?")
                .addButton("Favorite font 1?", (Runnable) null).setDisabled()
            .endRow()
            .addButton("Favorite font 2?", (Runnable) null)
            .addButton("Favorite font 3?", (Runnable) null).setDisabled()
            .setChangeListener((element, value, filledForm) -> System.out.println(
                "Component " + element.getLabel() +
                    " at position " + element.getIndex() +
                    " changed to " + value.toString()))
            .show();

        assert form.getByIndex(1).getId().equals("btn1");
        assert form.getById("?") != null;

        form.getElements().forEach(e -> System.out.println(e.getLabel() + " -> " + e.getValue()));
    }

    @Test
    public void test_form_dialog_with_rows_and_weights() {
        booster.createForm("Weighted rows")
            .startRow("half", Weights.HALF)
                .addText("1")
                .addText("2")
            .endRow()
            .startRow("thirds", Weights.THIRDS)
                .addText("1")
                .addText("2")
                .addText("3")
            .endRow()
            .startRow("quarter", Weights.QUARTER)
                .addText("1")
                .addText("2")
                .addText("3")
                .addText("4")
            .endRow()
            .startRow("custom", new double[]{0.2, 0.3, 0.5})
                .addText("1")
                .addText("2")
                .addText("3")
            .endRow()
            .show();
    }

    private Integer calculate(String input) {
        return (int) MathEvaluator.eval(input);
    }

}
