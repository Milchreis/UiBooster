package de.milchreis.uibooster;

import de.milchreis.uibooster.model.Form;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class CustomFormBuilderElementTest {

    UiBooster booster = new UiBooster();

    /**
     * Example custom form element
     */
    class CustomRobotFormElement extends FormElement<Integer> {

        JButton button;
        int count = 1;

        public CustomRobotFormElement(String label) {
            super(label);
            button = new JButton("Speak");
            button.addActionListener(l -> booster.showInfoDialog(
                "Hi, I'm Robo !!!\n " +
                    "You called me for the " + count++ + " time"));
        }

        @Override
        public JComponent createComponent(FormElementChangeListener onChange) {
            Box box = Box.createVerticalBox();
            box.add(new JLabel(new ImageIcon("src/test/resources/avatar1.png")));
            box.add(button);
            return box;
        }

        @Override
        public void setEnabled(boolean enable) {
            button.setEnabled(enable);
        }

        @Override
        public Integer getValue() {
            return count;
        }

        @Override
        public void setValue(Integer value) {
            button.setText(value.toString());
        }
    }


    @Test
    public void test_custom_form_dialog() {
        Form form = booster
            .createForm("Custom elements")
            .addCustomElement(new CustomRobotFormElement("Your custom robot"))
            .show();

        form.getElements().forEach(e -> {
            System.out.println(e.getLabel() + " -> " + e.getValue());
        });
    }

}
