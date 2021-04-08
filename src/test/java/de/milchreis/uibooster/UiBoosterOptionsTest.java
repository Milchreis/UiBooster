package de.milchreis.uibooster;

import de.milchreis.uibooster.model.UiBoosterOptions;
import de.milchreis.uibooster.model.options.DarkUiBoosterOptions;
import org.junit.jupiter.api.Test;

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

}