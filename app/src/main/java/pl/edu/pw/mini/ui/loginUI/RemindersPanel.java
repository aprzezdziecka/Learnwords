package pl.edu.pw.mini.ui.loginUI;

import com.toedter.calendar.JDateChooser;
import pl.edu.pw.mini.base.session.UserSession;
import pl.edu.pw.mini.ui.UIComponentAdder;
import pl.edu.pw.mini.ui.logoutUI.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class RemindersPanel extends JPanel implements UIComponentAdder {
    private JTextField eventNameField;
    private JDateChooser dateChooser;
    private JSpinner startTimeSpinnerHours;
    private JSpinner startTimeSpinnerMinutes;
    private JSpinner endTimeSpinnerHours;
    private JSpinner endTimeSpinnerMinutes;
    private JButton addEventButton;
    private JLabel statusLabel;

    public RemindersPanel() {
        initializeUI();
    }

    private void initializeUI() {
        ImageIcon icon = new ImageIcon("src/main/resources/icon/icons8-calendar-plus-100.png");
        JLabel iconLab = new JLabel();
        iconLab.setIcon(icon);
        eventNameField = new JTextField();
        dateChooser = new JDateChooser();
        startTimeSpinnerHours = createSpinner("HH");
        startTimeSpinnerMinutes = createSpinner("mm");
        endTimeSpinnerHours = createSpinner("HH");
        endTimeSpinnerMinutes = createSpinner("mm");
        addEventButton = new JButton("Dodaj przypomnienie");
        addEventButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        statusLabel = new JLabel();

        JPanel namePanel = createPanel("Nazwa wydarzenia:", eventNameField);
        JPanel datePanel = createPanel("Data:", dateChooser);
        JPanel startTimePanel = createTimePanel("Godzina rozpoczęcia:", startTimeSpinnerHours, startTimeSpinnerMinutes);
        JPanel endTimePanel = createTimePanel("Godzina zakończenia:", endTimeSpinnerHours, endTimeSpinnerMinutes);

        setLayout(new GridBagLayout());
        setBackground(new Color(249, 249, 249));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(iconLab, gbc);
        gbc.gridy++;
        add(namePanel, gbc);
        gbc.gridy++;
        add(datePanel, gbc);
        gbc.gridy++;
        add(startTimePanel, gbc);
        gbc.gridy++;
        add(endTimePanel, gbc);
        gbc.gridy++;
        add(addEventButton, gbc);
        gbc.gridy++;
        add(statusLabel, gbc);

        addEventButton.addActionListener(e -> addEvent());
    }

    private JSpinner createSpinner(String format) {
        JSpinner spinner = new JSpinner(new SpinnerDateModel());
        spinner.setEditor(new JSpinner.DateEditor(spinner, format));
        return spinner;
    }

    private JPanel createPanel(String labelText, JComponent component) {
        JPanel panel = new RoundedPanel(30);
        panel.setBackground(new Color(90, 169, 230));
        panel.setPreferredSize(new Dimension(400, 100));
        panel.setLayout(new GridBagLayout());
        JLabel lab =  new JLabel(labelText);
        lab.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lab.setForeground(new Color(249, 249, 249));
        addComponent2(panel, lab, 0, 0, 1, 0, GridBagConstraints.NONE, GridBagConstraints.CENTER, 10, 10, 10, 5);
        addComponent2(panel, component, 1, 0, 3, 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 10, 5, 10, 10);
        return panel;
    }

    private JPanel createTimePanel(String labelText, JSpinner hoursSpinner, JSpinner minutesSpinner) {
        JPanel panel = new RoundedPanel(30);
        panel.setBackground(new Color(90, 169, 230));
        panel.setPreferredSize(new Dimension(350, 50));
        panel.setLayout(new GridBagLayout());
        JLabel lab = new JLabel(labelText);
        lab.setForeground(new Color(249, 249, 249));
        lab.setFont(new Font("Segoe UI", Font.BOLD, 15));
        addComponent2(panel, lab, 0, 0, 1, 0, GridBagConstraints.NONE, GridBagConstraints.CENTER, 10, 10, 10, 5);

        JPanel timePanel = new RoundedPanel(10);
        timePanel.setLayout(new GridBagLayout());
        timePanel.setPreferredSize(new Dimension(150, 25));
        addComponent2(timePanel, hoursSpinner, 0, 0, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 10, 5, 10, 2);
        addComponent2(timePanel, new JLabel(":"), 1, 0, 1, 0, GridBagConstraints.NONE, GridBagConstraints.CENTER, 10, 2, 10, 2);
        addComponent2(timePanel, minutesSpinner, 2, 0, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 10, 2, 10, 10);
        addComponent2(panel, timePanel, 1, 0, 3, 2, GridBagConstraints.NONE, GridBagConstraints.CENTER, 10, 5, 10, 10);

        return panel;
    }

    private void addEvent() {
        String eventName = eventNameField.getText();
        LocalDate startDate = dateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        int startHour = getTimeFromSpinner(startTimeSpinnerHours, Calendar.HOUR_OF_DAY);
        int startMinute = getTimeFromSpinner(startTimeSpinnerMinutes, Calendar.MINUTE);
        int endHour = getTimeFromSpinner(endTimeSpinnerHours, Calendar.HOUR_OF_DAY);
        int endMinute = getTimeFromSpinner(endTimeSpinnerMinutes, Calendar.MINUTE);

        if (endHour < startHour || (endHour == startHour && endMinute <= startMinute)) {
            statusLabel.setText("Godzina zakończenia nie może być wcześniejsza niż godzina rozpoczęcia");
            return;
        }

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(startHour, startMinute));
        LocalDateTime endDateTime = LocalDateTime.of(startDate, LocalTime.of(endHour, endMinute));

        if (UserSession.getInstance().addEvent(eventName, startDateTime, endDateTime)) {
            statusLabel.setText("Dodano przypomnienie");
        } else {
            statusLabel.setText("Błąd dodawania przypomnienia");
        }
    }

    private int getTimeFromSpinner(JSpinner spinner, int calendarField) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) spinner.getValue());
        return calendar.get(calendarField);
    }
}

