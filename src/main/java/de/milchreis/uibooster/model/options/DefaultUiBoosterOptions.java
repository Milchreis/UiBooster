package de.milchreis.uibooster.model.options;

import com.bulenkov.darcula.DarculaLaf;
import de.milchreis.uibooster.model.UiBoosterOptions;

/**
 * Should the user call UiBooster() (default constructor)
 * the DefaultOptions will be used.
 */
public class DefaultUiBoosterOptions extends UiBoosterOptions {
  public static final String defaultIconPath = "/uibooster-default-icon.png";
  public static final String defaultLoadingImage = "/loading-75.gif";

  public DefaultUiBoosterOptions() {
    super(new DarculaLaf(), defaultIconPath, defaultLoadingImage);
  }
}
