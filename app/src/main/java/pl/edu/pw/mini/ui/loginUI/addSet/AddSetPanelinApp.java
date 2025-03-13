package pl.edu.pw.mini.ui.loginUI.addSet;

import pl.edu.pw.mini.base.session.SetSession;
import pl.edu.pw.mini.ui.UIComponentAdder;

import javax.swing.*;
import java.awt.*;

public class AddSetPanelinApp extends JPanel implements UIComponentAdder {
    private JLabel setNameLabel;
    private JTextField setNameTextField;
    private JLabel wordLabel;
    private JTextField wordText;
    private JLabel definitionLabel;
    private JTextField definitionText;
    private JButton addToSetButton;
    private JButton finishAddingSetButton;
    private JLabel statusLabel;

    public AddSetPanelinApp() {
        setBackground(new Color(249, 249, 249));
        initializeUI();
    }
    private void initializeUI() {
        setLayout(new GridBagLayout());

        // Ustawienia komponentów
        setNameLabel = new JLabel("Nazwa zestawu: ");
        setNameTextField = new JTextField(15);
        wordLabel = new JLabel("Pojęcie: ");
        wordText = new JTextField(15);
        definitionLabel = new JLabel("Definicja: ");
        definitionText = new JTextField(15);
        addToSetButton = new JButton("Dodaj fiszkę");
        finishAddingSetButton = new JButton("Zakończ tworzenie zestawu");
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);

        addComponent(this, setNameLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComponent(this, setNameTextField, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
        addComponent(this, wordLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComponent(this, wordText, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
        addComponent(this, definitionLabel, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComponent(this, definitionText, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
        addComponent(this, addToSetButton, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(this, finishAddingSetButton, 1, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        addComponent(this, statusLabel, 0, 5, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        addToSetButton.addActionListener(e -> handleAddToSet());
        finishAddingSetButton.addActionListener(e -> handleAddSet());
    }

    private void handleAddToSet() {
        String word = wordText.getText();
        String definition = definitionText.getText();
        if (SetSession.getInstance().addFlashcard(word, definition)) {
            statusLabel.setText("Fiszka dodana do zestawu.");
            wordText.setText("");
            definitionText.setText("");
        } else {
            statusLabel.setText("Błąd podczas dodawania fiszki.");
        }
    }

    private void handleAddSet() {
        String setName = setNameTextField.getText();
        if (SetSession.getInstance().addSet(setName)) {
            setNameTextField.setText("");
            statusLabel.setText("Zestaw dodany do konta :)");
        } else {
            statusLabel.setText("Błąd podczas dodawania zestawu.");
        }
    }
}