package de.milchreis.uibooster.model.options;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.lang.reflect.Constructor;
import com.bulenkov.darcula.DarculaLaf;
import de.milchreis.uibooster.model.UiBoosterOptions;

public class OSNativeUiBoosterOptions extends UiBoosterOptions {

  /**
   * Work around the UIManager to maintain Options heirarchy and not
   * make a unique case for OS Native options directly in
   * the UiBooster constructor
   */
  public static BasicLookAndFeel OSNativeLookAndFeel() {
    try {
      String className = UIManager.getSystemLookAndFeelClassName();
      Class<?> klass = Class.forName(className);
      Constructor<?> constr = klass.getConstructor();
      return (BasicLookAndFeel) constr.newInstance();
    } catch (Exception e) {  // How to handle this case?
      e.printStackTrace();
      return new DarculaLaf();
    }
  }

  public OSNativeUiBoosterOptions() {
    super(OSNativeLookAndFeel(), DefaultUiBoosterOptions.defaultIconPath, DefaultUiBoosterOptions.defaultLoadingImage);
  }
}
