package pl.edu.pw.mini.ui.loginUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import pl.edu.pw.mini.base.Flashcard;
import pl.edu.pw.mini.base.SetOfCards;
import pl.edu.pw.mini.base.session.SetSession;
import pl.edu.pw.mini.ui.UIComponentAdder;
import pl.edu.pw.mini.ui.logoutUI.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class LearnPanel extends JPanel implements UIComponentAdder {
    private JButton loadSetsButton;
    private JComboBox<SetOfCards> setsOfUser;
    private JLabel chosenSet;
    private JButton learnButton;
    private JTextField firstSideOfFlashcard;
    private JTextField secondSideOfFlashcard;
    private JButton checkButton;

    private List<Flashcard> remainingFlashcards = new ArrayList<>();
    private Flashcard currentFlashcard;
    private Random random = new Random();

    private SetOfCards selectedSet;
    private JButton endOfStudy;

    private DefaultPieDataset dataset;

    public LearnPanel() {
        initializeUI();
    }

    private void initializeUI() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setBackground(new Color(249, 249, 249));

        loadSetsButton = new JButton("Załaduj zestawy");
        setsOfUser = new JComboBox<>();
        chosenSet = new JLabel();
        chosenSet.setFont(new Font("Segoe UI", Font.BOLD, 20));
        chosenSet.setForeground(new Color(249, 249, 249));
        learnButton = new JButton("Przejdź do nauki");

        firstSideOfFlashcard = new JTextField();
        secondSideOfFlashcard = new JTextField();
        checkButton = new JButton("Sprawdź odpowiedź");

        endOfStudy = new JButton("Zakończ naukę");

        Dimension buttonSize = new Dimension(200, 30);
        loadSetsButton.setPreferredSize(buttonSize);
        learnButton.setPreferredSize(buttonSize);
        checkButton.setPreferredSize(buttonSize);
        endOfStudy.setPreferredSize(buttonSize);

        firstSideOfFlashcard.setEditable(false);
        firstSideOfFlashcard.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        firstSideOfFlashcard.setHorizontalAlignment(JTextField.CENTER);
        firstSideOfFlashcard.setBackground(new Color(0,0,0,0));
        firstSideOfFlashcard.setColumns(15);
        firstSideOfFlashcard.setFocusable(false);

        secondSideOfFlashcard.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        secondSideOfFlashcard.setHorizontalAlignment(JTextField.CENTER);
        secondSideOfFlashcard.setColumns(12);
        secondSideOfFlashcard.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        add(loadSetsButton);

        JPanel startPanel = new RoundedPanel(30);
        startPanel.setLayout(new GridBagLayout());
        startPanel.setBackground(new Color(90, 169, 230, 255));
        startPanel.setPreferredSize(new Dimension(300, 200));
        addComponent2(startPanel, setsOfUser, 0, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 2, 10, 2, 10);
        addComponent2(startPanel, chosenSet, 0, 2, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, 2, 10, 2, 10);
        addComponent2(startPanel, learnButton, 0, 3, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 2, 10, 2, 10);

        add(startPanel);

        JPanel learnPanel1 = new RoundedPanel(30);
        SpringLayout layout2 = new SpringLayout();
        learnPanel1.setLayout(layout2);
        learnPanel1.setBackground(new Color(127, 200, 248, 255));
        learnPanel1.setPreferredSize(new Dimension(250, 300));
        JLabel label1 = new JLabel("Pojęcie");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label1.setForeground(new Color(249, 249, 249));
        learnPanel1.add(label1);
        learnPanel1.add(firstSideOfFlashcard);
        layout2.putConstraint(SpringLayout.NORTH, label1, 20, SpringLayout.NORTH, learnPanel1);
        layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, label1, 0, SpringLayout.HORIZONTAL_CENTER, learnPanel1);
        layout2.putConstraint(SpringLayout.NORTH, firstSideOfFlashcard, 20, SpringLayout.SOUTH, label1);
        layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, firstSideOfFlashcard, 0, SpringLayout.HORIZONTAL_CENTER, learnPanel1);

        JPanel learnPanel2 = new RoundedPanel(20);

        learnPanel2.setLayout(layout2);
        learnPanel2.setPreferredSize(new Dimension(250, 300));
        learnPanel2.setBackground(new Color(127, 200, 248, 255));
        JLabel label2 = new JLabel("Definicja");
        label2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label2.setForeground(new Color(249, 249, 249));
        learnPanel2.add(label2);
        learnPanel2.add(secondSideOfFlashcard);
        layout2.putConstraint(SpringLayout.NORTH, label2, 20, SpringLayout.NORTH, learnPanel2);
        layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, label2, 0, SpringLayout.HORIZONTAL_CENTER, learnPanel2);
        layout2.putConstraint(SpringLayout.NORTH, secondSideOfFlashcard, 30, SpringLayout.SOUTH, label2);
        layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, secondSideOfFlashcard, 0, SpringLayout.HORIZONTAL_CENTER, learnPanel2);

        JPanel leanPanel = new RoundedPanel(30);
        leanPanel.setLayout(new GridLayout(1, 3, 20, 5));
        leanPanel.setBackground(new Color(249, 249, 249));
        leanPanel.add(learnPanel1, 0, 0);
        JPanel panel = new RoundedPanel(30);
        panel.setBackground(new Color(249, 249, 249));
        panel.setPreferredSize(new Dimension(20, 300));
        leanPanel.add(panel, 0 , 1);
        leanPanel.add(learnPanel2,0, 2);
        add(leanPanel);

        add(checkButton);
        add(endOfStudy);

        layout.putConstraint(SpringLayout.NORTH, startPanel, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, startPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, checkButton, 20, SpringLayout.SOUTH, leanPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, checkButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.SOUTH, endOfStudy, -20, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, endOfStudy, 20, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, loadSetsButton, 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, loadSetsButton, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, leanPanel, 50, SpringLayout.SOUTH, startPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, leanPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);

        initButtons();
    }
    private void initButtons() {
        loadSetsButton.addActionListener(e -> loadSets());
        setsOfUser.addActionListener(e -> updateChosenSet());
        learnButton.addActionListener(e -> startLearning());
        checkButton.addActionListener(e -> checkButtonListener());
        endOfStudy.addActionListener(e -> handleEndOfStudy());
    }

    private void loadSets() {
        List<SetOfCards> availableSets = SetSession.getInstance().setsOfcurrentUser();
        setsOfUser.removeAllItems();
        if (availableSets != null && !availableSets.isEmpty()) {
            availableSets.forEach(setsOfUser::addItem);
        } else {
            JOptionPane.showMessageDialog(this, "Brak dostępnych zestawów.");
        }
    }

    private void updateChosenSet() {
        selectedSet = (SetOfCards) setsOfUser.getSelectedItem();
        chosenSet.setText(selectedSet != null ? selectedSet.getName() : "");
    }

    private void startLearning() {
        selectedSet = (SetOfCards) setsOfUser.getSelectedItem();
        if (selectedSet != null) {
            SetSession.getInstance().setCurrentSet(selectedSet.getName());
            remainingFlashcards = new ArrayList<>(selectedSet.setOfFlashcards);
            remainingFlashcards.stream().forEach(flashcard -> flashcard.resetStats());
            if (!remainingFlashcards.isEmpty()) {
                showNextFlashcard();
            } else {
                JOptionPane.showMessageDialog(this, "Wybrany zestaw jest pusty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nie wybrano zestawu.");
        }
    }

    private void checkButtonListener() {
        if (remainingFlashcards.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Brak aktywnej fiszki.");
            return;
        }

        String userAnswer = secondSideOfFlashcard.getText().trim();
        String correctAnswer = currentFlashcard.definition.trim();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Prawidłowa odpowiedź!");
            currentFlashcard.incrementCorrect();
            remainingFlashcards.remove(currentFlashcard);
            showNextFlashcard();
        } else {
            handleIncorrectAnswer(userAnswer, correctAnswer);
        }
    }

    private void handleIncorrectAnswer(String userAnswer, String correctAnswer) {
        int choice = JOptionPane.showConfirmDialog(
                this,
                String.format("Nieprawidłowa odpowiedź.\nTwoja: %s\nPoprawna: %s\nCzy oznaczyć jako opanowaną?",
                        userAnswer, correctAnswer),
                "Błąd",
                JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) {
            currentFlashcard.incrementCorrect();
            remainingFlashcards.remove(currentFlashcard);
        }else{
            currentFlashcard.incrementIncorrect();
        }
        showNextFlashcard();
    }

    private void showNextFlashcard() {
        if (remainingFlashcards.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this,  "Czy chcesz uczyć się kolejną rundę", "Przejdź do kolejnej rundy", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                SetSession.getInstance().setCurrentSet(selectedSet.getName());
                remainingFlashcards = new ArrayList<>(selectedSet.setOfFlashcards);
            } else {
                handleEndOfStudy();
                return;
            }
        }
        int index = random.nextInt(remainingFlashcards.size());
        currentFlashcard = remainingFlashcards.get(index);
        firstSideOfFlashcard.setText(currentFlashcard.word);
        secondSideOfFlashcard.setText("");
    }

    private void handleEndOfStudy(){
        JOptionPane.showMessageDialog(this, "Zakończono naukę.");
        firstSideOfFlashcard.setText("");
        secondSideOfFlashcard.setText("");
        remainingFlashcards.clear();
        currentFlashcard = null;

        JFreeChart chart = chart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));

        JFrame chartFrame = new JFrame("Statystyki zestawu");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setSize(600, 600);
        chartFrame.add(chartPanel);
        chartFrame.setVisible(true);
    }
    private JFreeChart chart(){
        dataset = new DefaultPieDataset();
        AtomicInteger sumCorrect = new AtomicInteger();
        AtomicInteger sumInncorrect = new AtomicInteger();
        selectedSet.setOfFlashcards.stream().forEach(flashcard -> {
            sumCorrect.addAndGet(flashcard.lastCorrectAnswers);
            sumInncorrect.addAndGet(flashcard.lastIncorrectAnswers);
        });
        dataset.setValue("Poprawne", sumCorrect.get());
        dataset.setValue("Niepoprawne",sumInncorrect.get());
        JFreeChart chart = ChartFactory.createPieChart("Poprawność odpowiedzi w sesji", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();

        plot.setSectionPaint("Poprawne", new Color(255, 228, 95));
        plot.setSectionPaint("Niepoprawne", new Color(255, 99, 146));
        plot.setLabelBackgroundPaint(new Color(249, 249, 249));
        plot.setLabelFont(new Font("Segoe UI", Font.BOLD, 15));
        plot.setLabelGap(0.02);
        plot.setCircular(true);
        plot.setBackgroundPaint(new Color(255, 255, 255));

        chart.getLegend().setItemFont(new Font("Segoe UI", Font.PLAIN, 12));

        return chart;
    }

}

