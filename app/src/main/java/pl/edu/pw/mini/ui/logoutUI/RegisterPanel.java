package pl.edu.pw.mini.ui.logoutUI;


import pl.edu.pw.mini.base.session.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JLabel statusLabel;

    public RegisterPanel() {
        initializeUI();
    }
    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nazwa użytkownika
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nazwa użytkownika:"), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        add(usernameField, gbc);

        // Hasło
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Hasło:"), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Przycisk tworzenia konta
        registerButton = new JButton("Utwórz konto");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(registerButton, gbc);

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(statusLabel, gbc);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if(UserSession.getInstance().register(username, password)) {
            statusLabel.setText("Użytkownik zarejestrowany! Przejdź do panelu logowania :)");
        }else {
            statusLabel.setText("Użytkownik istnieje lub puste hasło/nazwa");
        }
    }
}
