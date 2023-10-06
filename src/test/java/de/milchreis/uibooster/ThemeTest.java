package de.milchreis.uibooster;

import de.milchreis.uibooster.model.UiBoosterOptions;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

class ThemeTest {

    @Test
    public void test_look_and_feel() {

        UiBooster booster = new UiBooster(new UiBoosterOptions(new NimbusLookAndFeel(), null, null));
        booster.showInfoDialog("Info Message Empty Constructor");

        UiBooster boosterDark = new UiBooster(UiBoosterOptions.Theme.DARK_THEME);
        boosterDark.showInfoDialog("Info message Theme.DARK_THEME");

        UiBooster boosterLight = new UiBooster(UiBoosterOptions.Theme.LIGHT_THEME);
        boosterLight.showInfoDialog("Info message Theme.LIGHT_THEME");

        UiBooster boosterSwing = new UiBooster(UiBoosterOptions.Theme.SWING);
        boosterSwing.showInfoDialog("Info message Theme.SWING");

        UiBooster boosterOSNative = new UiBooster(UiBoosterOptions.Theme.OS_NATIVE);
        boosterOSNative.showInfoDialog("Info message Theme.OS_NATIVE");

    }

}
