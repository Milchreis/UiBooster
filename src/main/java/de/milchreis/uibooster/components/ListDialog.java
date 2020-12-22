package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;
import de.milchreis.uibooster.model.ListElement;
import de.milchreis.uibooster.model.SelectElementListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

public class ListDialog {
    private static final int ICON_DIMENSION = 80;

    public static ListElement showList(String message, String title, String iconPath, ListElement... elements) {
        return showList(message, title, iconPath, null, elements);
    }

    public static ListElement showList(String message, String title, String iconPath,
                                       SelectElementListener selectElementListener,
                                       ListElement... elements) {

        JList<ListElement> list = createList(selectElementListener, elements);

        final JScrollPane jScrollPane = new JScrollPane(
                list,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        jScrollPane.setPreferredSize(new Dimension(400, 400));

        SimpleBlockingDialog dialog = new SimpleBlockingDialog(jScrollPane);

        DialogClosingState closingState = dialog.showDialog(message, title, null, iconPath, true);
        return closingState.isClosedByUser() ? null : list.getSelectedValue();
    }

    public static JList<ListElement> createList(SelectElementListener selectElementListener, ListElement[] elements) {
        DefaultListModel<ListElement> listModel = createListModel(elements);
        JList<ListElement> list = new JList<>(listModel);

        if (selectElementListener != null) {
            list.addListSelectionListener(e -> {
                if (e.getValueIsAdjusting()) {
                    selectElementListener.onSelected(elements[e.getFirstIndex()]);
                }
            });
        }

        boolean hasAtLeasedOneIcon = Stream.of(elements).anyMatch(e -> e.getImage() != null);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer((list1, listElement, index, isSelected, cellHasFocus) -> {

            JPanel row = new JPanel(new BorderLayout());

            Box vBox = Box.createVerticalBox();
            vBox.setAlignmentY(Box.TOP_ALIGNMENT);
            vBox.add(new JMultilineLabel(listElement.getTitle(), true));
            vBox.add(new JMultilineLabel(listElement.getMessage(), false));
            row.add(vBox, BorderLayout.CENTER);

            final Image preview = listElement.getImage() != null ?
                    listElement.getImage().getScaledInstance(ICON_DIMENSION, ICON_DIMENSION, BufferedImage.SCALE_FAST)
                    : new BufferedImage(ICON_DIMENSION, ICON_DIMENSION, BufferedImage.TYPE_4BYTE_ABGR);

            JLabel image = new JLabel(new ImageIcon(preview));
            image.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3));
            image.setVerticalAlignment(JLabel.TOP);

            if (hasAtLeasedOneIcon)
                row.add(image, BorderLayout.WEST);

            if (isSelected) {
                row.setBackground(list.getSelectionBackground());
                row.setForeground(list.getSelectionForeground());
            } else {
                row.setBackground(list.getBackground());
                row.setForeground(list.getForeground());
            }

            row.setEnabled(list.isEnabled());
            row.setFont(list.getFont());
            row.setOpaque(true);
            row.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));

            return row;
        });

        return list;
    }

    @NotNull
    public static DefaultListModel<ListElement> createListModel(ListElement[] elements) {
        DefaultListModel<ListElement> listModel = new DefaultListModel<>();
        Stream.of(elements).forEach(listModel::addElement);
        return listModel;
    }

    static class JMultilineLabel extends JLabel {
        private static final long serialVersionUID = 1L;

        public JMultilineLabel(String text, boolean bold) {
            super();

            if (text != null) {
                final String prepared = "<html>"
                        + text
                        .replace("\r", "")
                        .replace("\n", "<br>")
                        + "</html>";

                setText(prepared);
            }

            setCursor(null);
            setOpaque(false);
            setFocusable(false);
            setFont(UIManager.getFont("Label.font"));
            if (!bold)
                setFont(getFont().deriveFont(getFont().getStyle() & ~Font.BOLD));
            setBorder(new EmptyBorder(5, 5, 0, 5));
            setAlignmentY(JLabel.CENTER_ALIGNMENT);
        }
    }

}
