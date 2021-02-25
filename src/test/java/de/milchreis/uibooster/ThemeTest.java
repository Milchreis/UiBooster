package de.milchreis.uibooster;

import de.milchreis.uibooster.model.UiBoosterOptions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

class ThemeTest {

    @Test
    public void test_look_and_feel() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        UiBooster booster = new UiBooster(new UiBoosterOptions(UiBoosterOptions.Theme.DEFAULT));
        booster.showInfoDialog("Info message");

        UiBooster boosterDark = new UiBooster(new UiBoosterOptions(UiBoosterOptions.Theme.DARK_THEME));
        boosterDark.showInfoDialog("Info message");
    }

}