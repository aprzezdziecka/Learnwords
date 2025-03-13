package pl.edu.pw.mini.ui.logoutUI;

import pl.edu.pw.mini.base.session.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    private Runnable menuUpdate;

    public LoginPanel(Runnable menuUpdate) {
        this.menuUpdate = menuUpdate;
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

        // Przycisk logowania
        loginButton = new JButton("Zaloguj");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

        // Etykieta statusu
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(statusLabel, gbc);

        // Obsługa logowania
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if(UserSession.getInstance().login(username, password)){
            statusLabel.setText("Logowanie udane!");
            passwordField.setText("");
            usernameField.setText("");
            int confirm = JOptionPane.showConfirmDialog(this, "Czy chcesz dodać konto Google w celu dodawania przypomnień do kalendarza Google?", "Dodaj konto Google", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if(UserSession.getInstance().googleAccount()) {
                    statusLabel.setText("Konto Google dodane!");
                } else {
                    statusLabel.setText("Nie udało się dodać konta Google.");
                }
            }
            if (menuUpdate != null) menuUpdate.run();
        }else{
            statusLabel.setText("Nieprawidłowa nazwa użytkownika lub hasło.");
        }
    }

}
