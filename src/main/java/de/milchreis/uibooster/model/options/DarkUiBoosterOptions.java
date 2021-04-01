package de.milchreis.uibooster.model.options;

import com.bulenkov.darcula.DarculaLaf;
import de.milchreis.uibooster.model.UiBoosterOptions;

public class DarkUiBoosterOptions extends UiBoosterOptions {
  public DarkUiBoosterOptions() {
    super(new DarculaLaf(), "/uibooster-default-icon.png", DefaultUiBoosterOptions.defaultLoadingImage);
  }
}
