package pl.edu.pw.mini.ui.loginUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import pl.edu.pw.mini.base.Flashcard;
import pl.edu.pw.mini.base.SetOfCards;
import pl.edu.pw.mini.ui.PanelManager;
import pl.edu.pw.mini.ui.logoutUI.RoundedPanel;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsPanel extends JPanel {
    private SetOfCards setOfCards;
    private JLabel startLabel;
    private JLabel intLabel1;
    private JLabel intLabel2;
    private JButton returnButton;
    private JPanel panelint;
    private JPanel chartPanel;
    private AtomicInteger correct;
    private AtomicInteger incorrect;
    private ChartPanel chartPanel1;
    private ChartPanel chartPanel2;

    public StatsPanel(SetOfCards setOfCards) {
        this.setOfCards = setOfCards;
        init();
    }

    private void init(){
        //button init
        returnButton = new JButton("Powrót");
        returnButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        returnButton.setBackground(new Color(249, 249, 249));
        returnButton.setForeground(Color.BLACK);
        returnButton.setPreferredSize(new Dimension(150, 40));

        //startLabel init
        startLabel = new JLabel("Statystyki zestawu: " + setOfCards.getName());
        startLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        startLabel.setForeground(new Color(90, 169, 230));

        //int stat panel init
        intLabel1 = new JLabel("Liczba fiszek: " + setOfCards.setOfFlashcards.size());
        intLabel1.setVerticalAlignment(SwingConstants.CENTER);
        intLabel1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        intLabel1.setForeground(new Color(249, 249, 249));

        if (setOfCards.creationDate == null) {
            setOfCards.creationDate = setOfCards.creationDate.now();
        }
        intLabel2 = new JLabel("Zestaw stworzono: " + setOfCards.creationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        intLabel2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        intLabel2.setForeground(new Color(249, 249, 249));
        intLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        layoutComp1();

        //pie chart
        List<Flashcard> flashcards = setOfCards.setOfFlashcards;
        correct = new AtomicInteger();
        incorrect = new AtomicInteger();
        flashcards.forEach(e-> {
            correct.addAndGet(e.correctAnswers);
            incorrect.addAndGet(e.incorrectAnswers);
        });
        chartPanel = new CircularProgressbar.CircularProgressChart(correct.get(), correct.get() + incorrect.get());
        chartPanel.setPreferredSize(new Dimension(400, 200));

        JPanel panel1 = new RoundedPanel(30);
        panel1.setBackground(new Color(249, 249, 249));
        panel1.setLayout(new GridLayout(1, 2, 20, 20));
        panel1.add(panelint);
        panel1.add(chartPanel);

        //bar charts
        createBarChartPanel(flashcards);
        JPanel panel2 = new RoundedPanel(30);
        panel2.setBackground(new Color(249, 249, 249));
        panel2.setLayout(new GridLayout(1, 2, 20, 20));
        panel2.add(chartPanel1);
        panel2.add(chartPanel2);

        //button
        returnButton.addActionListener(e -> {
            PanelManager.getInstance().showPanel("AccountPanel");
        });

        //dodanie do panelu
        SpringLayout layout = new SpringLayout();
        setBackground(new Color(249, 249, 249));
        setLayout(layout);
        add(startLabel);
        add(panel1);
        add(panel2);
        add(returnButton);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, startLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, startLabel, 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, panel1, 50, SpringLayout.SOUTH, startLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, panel2, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, panel2, 50, SpringLayout.SOUTH, panel1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, returnButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.SOUTH, returnButton, -10, SpringLayout.SOUTH, this);

    }

    private void layoutComp1(){
        panelint = new RoundedPanel(30);
        panelint.setBackground(new Color(127, 200, 248, 255));
        SpringLayout layout1 = new SpringLayout();
        panelint.setLayout(layout1);
        panelint.setPreferredSize(new Dimension(400, 200));
        panelint.add(intLabel1);
        panelint.add(intLabel2 );
        layout1.putConstraint(SpringLayout.HORIZONTAL_CENTER, intLabel1, 0, SpringLayout.HORIZONTAL_CENTER, panelint);
        layout1.putConstraint(SpringLayout.NORTH, intLabel1, 60, SpringLayout.NORTH, panelint);
        layout1.putConstraint(SpringLayout.HORIZONTAL_CENTER, intLabel2, 0, SpringLayout.HORIZONTAL_CENTER, panelint);
        layout1.putConstraint(SpringLayout.NORTH, intLabel2, 20, SpringLayout.SOUTH, intLabel1);
    }

    private void createBarChartPanel(List<Flashcard> flashcards){
        int[] data3 = flashcards.stream().mapToInt(e -> e.correctAnswers).toArray();
        int[] data4 = flashcards.stream().mapToInt(e -> e.incorrectAnswers).toArray();

        Map<Integer, Integer> correctAnswers = new HashMap<>();
        Map<Integer, Integer> incorrectAnswers = new HashMap<>();

        for(int correct : data3){
            correctAnswers.put(correct, correctAnswers.getOrDefault(correct, 0) + 1);
        }
        for(int incorrect : data4){
            incorrectAnswers.put(incorrect, incorrectAnswers.getOrDefault(incorrect, 0) + 1);
        }

        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        for(Map.Entry<Integer, Integer> entry : correctAnswers.entrySet()){
            dataset3.addValue(entry.getValue(), "Poprawne odpowiedzi", entry.getKey());
        }

        DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();
        for(Map.Entry<Integer, Integer> entry : incorrectAnswers.entrySet()){
            dataset4.addValue(entry.getValue(), "Błędne odpowiedzi", entry.getKey());
        }

        JFreeChart chart5 = ChartFactory.createBarChart("Poprawne odpowiedzi", "Liczba poprawnych odpowiedzi", "Liczba fiszek", dataset3, PlotOrientation.VERTICAL, true, true, false);
        JFreeChart chart6 = ChartFactory.createBarChart("Błędne odpowiedzi", "Liczba błędnych odpowiedzi", "Liczba fiszek", dataset4, PlotOrientation.VERTICAL, true, true, false);

        chartTheme(chart5);
        chartTheme(chart6);

        chartPanel1 = new ChartPanel(chart5);
        chartPanel1.setBackground(new Color(249, 249, 249));
        chartPanel2 = new ChartPanel(chart6);
        chartPanel2.setBackground(new Color(249, 249, 249));
        chartPanel1.setPreferredSize(new Dimension(500, 400));
        chartPanel2.setPreferredSize(new Dimension(500, 400));
    }

    private void chartTheme(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setBackgroundPaint(new Color(249, 249, 249));
        BarRenderer renderer5 = (BarRenderer) plot.getRenderer();
        renderer5.setSeriesPaint(0, new Color(255, 99, 146));
    }



}
