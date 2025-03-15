package pl.edu.pw.mini.ui;

import javax.swing.*;
import java.awt.*;

public class ConfirmedPanel extends JPanel implements UIComponentAdder{
    private JLabel welcomeLabel;

    public ConfirmedPanel() {
        setLayout(new GridBagLayout());

        welcomeLabel = new JLabel("Zalogowano pomyślnie. Możesz rozpocząć naukę lub zaplanować powtórki.", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(90, 169, 230));

        addComponent(this, welcomeLabel, 0, 0, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL, 2, 2, 2, 2);
    }
}
