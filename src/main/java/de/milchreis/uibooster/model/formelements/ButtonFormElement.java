package de.milchreis.uibooster.model.formelements;

import de.milchreis.uibooster.model.ButtonClickListener;
import de.milchreis.uibooster.model.FormElement;
import de.milchreis.uibooster.model.FormElementChangeListener;

import javax.swing.*;
import java.awt.*;

public class ButtonFormElement extends FormElement<String> {

    private String buttonLabel;
    private final ButtonClickListener onClick;
    private Color backgroundColor;
    private Color textColor;
    private JButton button;

    public ButtonFormElement(String label, String buttonLabel, ButtonClickListener onClick) {
        this(label, buttonLabel, onClick, null, null);
    }

    public ButtonFormElement(String label, String buttonLabel, ButtonClickListener onClick, Color backgroundColor, Color textColor) {
        super(label);
        this.buttonLabel = buttonLabel;
        this.onClick = onClick;

        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {
        button = new JButton(buttonLabel);

        setBackgroundColor(backgroundColor);
        setTextColor(textColor);

        button.addActionListener(l -> {
            onClick.onClick(this, form);

            if (hasBinding())
                binding.set(getValue());

            if (onChange != null)
                onChange.onChange(this, buttonLabel, form);
        });

        return button;
    }

    @Override
    public void setEnabled(boolean enable) {
        button.setEnabled(enable);
    }

    @Override
    public String getValue() {
        return button.getName();
    }

    @Override
    public void setValue(String value) {
        buttonLabel = value;
        button.setText(buttonLabel);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        if (backgroundColor != null) {
            this.backgroundColor = backgroundColor;
            button.setBackground(backgroundColor);
        }
    }

    public void setTextColor(Color textColor) {
        if (textColor != null) {
            this.textColor = textColor;
            button.setForeground(textColor);
        }
    }
}
