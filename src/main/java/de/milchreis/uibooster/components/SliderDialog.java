package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderDialog {

    public static Integer showDialog(String message, String title, int min, int max, int init, int majorTick, int minorTick, String iconPath) {

        JLabel current = new JLabel(init + "");
        current.setHorizontalAlignment(JLabel.CENTER);

        JSlider slider = createSlider(min, max, init, majorTick, minorTick, current);

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(current, slider);
        DialogClosingState closingState = dialog.showDialog(message, title, iconPath);

        return closingState.isClosedByUser() ? null : slider.getValue();
    }

    @NotNull
    public static JSlider createSlider(int min, int max, int init, int majorTick, int minorTick, JLabel current) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
        slider.setMajorTickSpacing(majorTick);
        slider.setMinorTickSpacing(minorTick);
        slider.setLabelTable(slider.createStandardLabels(majorTick));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                current.setText(slider.getValue()+"");
            }
        });
        return slider;
    }

}
