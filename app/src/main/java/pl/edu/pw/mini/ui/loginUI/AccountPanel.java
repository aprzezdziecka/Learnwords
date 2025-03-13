package pl.edu.pw.mini.ui.loginUI;

import pl.edu.pw.mini.base.SetOfCards;
import pl.edu.pw.mini.base.session.SetSession;
import pl.edu.pw.mini.ui.PanelManager;
import pl.edu.pw.mini.ui.UIComponentAdder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class AccountPanel extends JPanel implements UIComponentAdder {

    private JButton actualizeButton;
    private JScrollPane flashcardsScrollPane;
    private JComboBox<SetOfCards> setOfCardsComboBox;
    private List<SetOfCards> currentSets = SetSession.getInstance().setsOfcurrentUser();
    private JTable table;
    private String[] columnNames = {"Pojęcie", "Definicja"};
    private JButton removeButton;
    private JButton removeFlashcardButton;
    private DefaultTableModel tableModel;
    private JButton showStatsButton;


    public AccountPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(249, 249, 249));
        GridBagConstraints gbc = new GridBagConstraints();

        // Aktualizacja
        actualizeButton = new JButton("Zaktualizuj dostępne fiszki");
        actualizeButton.setPreferredSize(new Dimension(200, 40));
        addComponent(this, actualizeButton, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 2, 5, 2, 5);
        // JComboBox
        setOfCardsComboBox = new JComboBox<>();
        setOfCardsComboBox.setPreferredSize(new Dimension(250, 30));
        addComponent(this, setOfCardsComboBox, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 2,5,2,5);
        // Usuwanie zestawu
        removeButton = new JButton("Usuń wybrany zestaw");
        removeButton.setPreferredSize(new Dimension(200, 40));
        addComponent(this, removeButton, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,1,5,2,5);

        removeFlashcardButton = new JButton("Usuń wybraną fiszkę z zestawu.");
        removeFlashcardButton.setPreferredSize(new Dimension(200, 40));
        addComponent(this, removeFlashcardButton, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 2,5,2,5);

        showStatsButton = new JButton("Statystyki");
        showStatsButton.setPreferredSize(new Dimension(200, 40));
        addComponent(this, showStatsButton, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 2,5,2,5);

        // JScrollPane
        flashcardsScrollPane = new JScrollPane();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(flashcardsScrollPane, gbc);
        setVisible(true);

        actualizeButton.addActionListener(e -> {
            currentSets = SetSession.getInstance().setsOfcurrentUser();
            setOfCardsComboBox.removeAllItems();
            if (currentSets != null) {
                for (SetOfCards setOfCards : currentSets) {
                    setOfCardsComboBox.addItem(setOfCards);
                }
            }
        });

        removeButton.addActionListener(e -> {
            SetOfCards selectedSet = (SetOfCards) setOfCardsComboBox.getSelectedItem();
            if (selectedSet != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Czy potwierdzasz usunięcie zestawu?", "Usuń trwale zestaw", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (SetSession.getInstance().removeSet(selectedSet.getName())) {
                        JOptionPane.showMessageDialog(this, "Zestaw został usunięty.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Błąd usuwania", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }
                actualizeButton.getActionListeners()[0].actionPerformed(e);
            }
        });
        removeFlashcardButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            SetOfCards selectedSet = (SetOfCards) setOfCardsComboBox.getSelectedItem();
            if (selectedSet == null) {
                return;
            }
            if (row != -1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Czy potwierdzasz usunięcie wybranej fiszki?", "Usuń trwale fiszkę", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (SetSession.getInstance().removeFlashcardFromSet(selectedSet.getName(), row)) {
                        tableModel.removeRow(row);
                    } else {
                        JOptionPane.showMessageDialog(this, "Nie udało się usunąć fiszki");
                    }
                }
            }
        });

        setOfCardsComboBox.addActionListener(e -> {
            SetOfCards set = (SetOfCards) setOfCardsComboBox.getSelectedItem();
            if (set == null) {
                if (!currentSets.isEmpty()) {
                    set = currentSets.get(0);
                }
            }
            if (set != null) {
                Object[][] data = set.setOfFlashcards.stream()
                        .map(card -> new Object[]{card.word, card.definition})
                        .toArray(Object[][]::new);

                tableModel = new DefaultTableModel(data, columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                if (table == null) {
                    table = new JTable(tableModel);
                    table.setShowGrid(true);
                    flashcardsScrollPane.setViewportView(table);
                } else {
                    table.setModel(tableModel);
                }
            }
        });
        showStatsButton.addActionListener(e -> {
            JPanel statsPanel = new StatsPanel((SetOfCards) setOfCardsComboBox.getSelectedItem());
            PanelManager.getInstance().showPanel(statsPanel);
        });
    }

}
