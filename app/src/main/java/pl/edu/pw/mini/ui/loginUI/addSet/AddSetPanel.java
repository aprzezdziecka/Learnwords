package pl.edu.pw.mini.ui.loginUI.addSet;

import pl.edu.pw.mini.ui.UIComponentAdder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddSetPanel extends JPanel implements UIComponentAdder {
    protected JButton uploadButton;
    protected JLabel statusLabel;
    protected JTextField nameField;
    protected JLabel nameLabel;

    public AddSetPanel() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());

        uploadButton = new JButton(" Wgraj plik");
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        nameField = new JTextField(20);
        nameLabel = new JLabel("Podaj nazwę zestawu (lub zostanie ustawiona na nazwę pliku)");

        addComponent(this, nameLabel, 0, 0, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComponent(this, nameField, 0, 1, 2, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);

        addComponent(this, uploadButton, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        addComponent(this, statusLabel, 0, 3, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFileUpload();
            }
        });
    }
    protected abstract void handleFileUpload();

    /*private void addComponent(Component comp, int x, int y, int gridWidth, int gridHeight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(comp, gbc);
    }*/


}
