package pl.edu.pw.mini.ui;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel implements UIComponentAdder{
    private JLabel welcomeLabel;
    private JLabel motivationalQuote;

    public StartPanel() {
        setLayout(new GridBagLayout());

        welcomeLabel = new JLabel("Witaj w aplikacji do nauki fiszek!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(90, 169, 230));

        motivationalQuote = new JLabel("Ucz się skutecznie i z przyjemnością! Zaczynaj teraz.", SwingConstants.CENTER);
        motivationalQuote.setFont(new Font("Arial", Font.ITALIC, 16));
        motivationalQuote.setForeground(new Color(102, 102, 102));

        addComponent(this, welcomeLabel, 0, 0, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL, 2, 2, 2, 2);
        addComponent(this, motivationalQuote, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 2, 2, 2,2);

    }
}
