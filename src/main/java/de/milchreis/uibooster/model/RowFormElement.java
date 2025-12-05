package de.milchreis.uibooster.model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import static de.milchreis.uibooster.utils.FormPanel.createPanel;
import static java.util.Collections.singletonList;

public class RowFormElement extends FormElement<Object> {

    private final List<FormElement> elements;
    private final int hGap;
    private final int vGap;
    private final double[] columnWeights;

    public RowFormElement(String label, List<FormElement> elements, int marginLeft, int marginTop, int marginRight, int marginBottom, int hGap, int vGap, double[] columnWeights) {
        super(label);
        this.elements = elements;
        this.hGap = hGap;
        this.vGap = vGap;
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
        this.columnWeights = columnWeights;
    }

    @Override
    public JComponent createComponent(FormElementChangeListener onChange) {

        Box hBox = Box.createHorizontalBox();
        hBox.setAlignmentY(Box.TOP_ALIGNMENT);
        hBox.setBorder(new EmptyBorder(marginTop, marginLeft, marginBottom, marginRight));

        for (FormElement element : elements) {
            JPanel panel = createPanel(singletonList(element), onChange, 0);
            if(vGap > 0 || hGap > 0) {
                final boolean isLast = elements.getLast().equals(element);
                final int rightGap = isLast ? 0 : vGap;
                panel.setBorder(new EmptyBorder(0,0, hGap, rightGap));
            }
            hBox.add(panel);
        }

        if (columnWeights != null && columnWeights.length > 0) {
            hBox.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int availableWidth = hBox.getWidth();
                    int idx = 0;

                    for (Component panel : hBox.getComponents()) {
                        int width = (int) (availableWidth * columnWeights[idx++]);
                        Dimension size = new Dimension(width, panel.getPreferredSize().height);

                        panel.setMinimumSize(size);
                        panel.setPreferredSize(size);
                        panel.setMaximumSize(size);
                    }

                    hBox.revalidate();
                }
            });
        }

        return hBox;
    }

    @Override
    public void setEnabled(boolean enable) {
        for (FormElement element : elements) {
            element.setEnabled(enable);
        }
    }

    @Override
    public List<FormElement> getValue() {
        return elements;
    }

    @Override
    public void setValue(Object value) {
    }

    public List<FormElement> getElements() {
        return elements;
    }
}
