package de.milchreis.uibooster;

import de.milchreis.uibooster.model.UiBoosterOptions;
import de.milchreis.uibooster.model.options.DarkUiBoosterOptions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static java.lang.Thread.sleep;

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
    public void test_theme_and_icon() throws InterruptedException {
        final UiBooster uiBooster = new UiBooster(
                UiBoosterOptions.Theme.SWING,
                "src/test/resources/avatar1.png"
        );

        uiBooster.showInfoDialog("some title");
        uiBooster.showWarningDialog("warning text", "some title");
        uiBooster.showErrorDialog("warning text", "some title");

        String res = uiBooster.showTextInputDialog("some title");
        System.out.println(res);

        final boolean isConfirmed = uiBooster.showConfirmDialog("message", "some title");
        System.out.println("Is confirmed? -> " + isConfirmed);

        final String selectedOption = uiBooster
                .showSelectionDialog("some message", "some title", "Option 1", "Option 2");
        System.out.println(selectedOption);
        uiBooster.showFileSelection();
        uiBooster.showDirectorySelection();

        uiBooster.showWaitingDialog("message", "title", true);
        sleep(2000);
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

    @Test
    public void test_info_with_title() {
        new UiBooster().showInfoDialog("some info", "My title");
    }

}
