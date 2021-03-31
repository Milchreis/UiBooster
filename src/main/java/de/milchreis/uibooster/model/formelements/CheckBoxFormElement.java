package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;
import javax.swing.*;

public class CheckBoxFormElement extends FormElement {
  private boolean ticked;
  private final JCheckBox button;

  /**
   * Button label is propably more important than the component label.
   */
  public CheckBoxFormElement(String buttonLabel) {
    this("", buttonLabel);
  }

  public CheckBoxFormElement(String label, String buttonLabel) {
    super(label);
    ticked = false;
    button = new JCheckBox(buttonLabel);
    button.addActionListener(l -> {
      toogleTick();
    });
  }

  /**
   * Not exactly clear on the inner workings of this function
   */
  @Override
  public JComponent createComponent(FormElementChangeListener onChange) {
    return button;
  }

  @Override
  public void setEnabled(boolean enable) {
    button.setEnabled(enable);
  }

  @Override
  public Object getValue() {
    return ticked;
  }

  /**
   * Conscious choice to be strict about the value.
   */
  @Override
  public void setValue(Object value) {
    // no-op
  }

  private void toogleTick() {
    ticked ^= true;
  }
}
