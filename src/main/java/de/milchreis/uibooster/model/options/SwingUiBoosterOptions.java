package de.milchreis.uibooster.model.options;


import javax.swing.plaf.metal.MetalLookAndFeel;
import de.milchreis.uibooster.model.UiBoosterOptions;

public class SwingUiBoosterOptions extends UiBoosterOptions {
  public SwingUiBoosterOptions() {
    super(new MetalLookAndFeel(), DefaultUiBoosterOptions.defaultIconPath, DefaultUiBoosterOptions.defaultLoadingImage);
  }
}
