package de.milchreis.uibooster.components;

import de.milchreis.uibooster.model.DialogClosingState;
import de.milchreis.uibooster.model.LoginCredentials;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static de.milchreis.uibooster.utils.WindowIconHelper.applyWindowIcon;

public class LoginDialog extends JDialog {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbAddress;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private DialogClosingState closingState;

    public LoginDialog(String title, String message, String usernameLabel, String passwordLabel, String loginButtonLabel, String cancelButtonLabel, String iconPath) {
        super((JFrame) null, title, true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.ipadx = 5;
        cs.ipady = 5;

        lbAddress = new JLabel(message);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(lbAddress, cs);

        lbUsername = new JLabel(usernameLabel);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel(passwordLabel);
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        pfPassword.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });

        closingState = new DialogClosingState();

        btnLogin = new JButton(loginButtonLabel);

        btnLogin.addActionListener(e -> dispose());
        btnCancel = new JButton(cancelButtonLabel);
        btnCancel.addActionListener(e -> {
            closingState.setClosedByUser(true);
            dispose();
        });

        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closingState.setClosedByUser(true);
            }
        });

        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        applyWindowIcon(iconPath, this);

        tfUsername.requestFocus();
    }

    public LoginCredentials showDialog() {
        setVisible(true);
        dispose();
        return closingState.isClosedByUser() ?
                null :
                new LoginCredentials(tfUsername.getText().trim(), new String(pfPassword.getPassword()));
    }
}
