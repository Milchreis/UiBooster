package de.milchreis.uibooster;

import de.milchreis.uibooster.model.UiBoosterOptions;
import de.milchreis.uibooster.model.options.DarkUiBoosterOptions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class UiBoosterOptionsTest {

    @Test
    public void test_plain() {
        new UiBooster();
    }

    @Test
    public void test_theme() {
        new UiBooster(
                UiBoosterOptions.Theme.DARK_THEME
        );
    }

    @Test
    public void test_theme_and_icon() {
        new UiBooster(
                UiBoosterOptions.Theme.DARK_THEME,
                "/path/to/your/custom-window-icon.png"
        );
    }

    @Test
    public void test_options() {
        new UiBooster(new DarkUiBoosterOptions());
    }

    @Test
    public void test_different_font() {
        final UiBooster booster = new UiBooster(new Font("Comic Sans MS", Font.BOLD, 24));
        booster.showInfoDialog("Some fancy information with crazy font");
    }

}