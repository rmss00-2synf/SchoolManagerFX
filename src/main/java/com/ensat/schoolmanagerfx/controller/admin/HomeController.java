package com.ensat.schoolmanagerfx.controller.admin;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class HomeController {

    @FXML
    private Label studentsLabel;

    @FXML
    private Label teachersLabel;

    @FXML
    private Label staffsLabel;

    @FXML
    private Label awardsLabel;

    @FXML
    private DatePicker calendarPicker; // Pour le calendrier à droite.


    /**
     * Met à jour les statistiques principales.
     */
    public void updateStatistics(int students, int teachers, int staffs, int awards) {
        studentsLabel.setText(String.valueOf(students));
        teachersLabel.setText(String.valueOf(teachers));
        staffsLabel.setText(String.valueOf(staffs));
        awardsLabel.setText(String.valueOf(awards));
    }

    /**
     * Initialisation du calendrier.
     */
    @FXML
    private Label monthYearLabel;

    @FXML
    private GridPane calendarGrid;

    private LocalDate currentDate;

    public void initialize() {
        currentDate = LocalDate.now();
        buildCalendar(currentDate);
    }

    private void buildCalendar(LocalDate date) {
        calendarGrid.getChildren().clear(); // Clear existing content

        YearMonth yearMonth = YearMonth.from(date);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        // Update month and year label
        monthYearLabel.setText(date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + date.getYear());

        // Add headers for days of the week
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            dayLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #555;");
            calendarGrid.add(dayLabel, i, 0);
        }

        // Add days of the month
        int row = 1;
        int column = firstDayOfWeek % 7;
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDay = yearMonth.atDay(day);
            StackPane dayPane = new StackPane();
            dayPane.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-padding: 5;");
            Label dayLabel = new Label(String.valueOf(day));
            dayPane.getChildren().add(dayLabel);

            // Highlight current date
            if (currentDay.equals(LocalDate.now())) {
                dayPane.setStyle("-fx-background-color: #6c63ff; -fx-text-fill: white; -fx-border-radius: 15; -fx-background-radius: 15;");
                dayLabel.setStyle("-fx-text-fill: white;");
            }

            // Add click event for each day
            dayPane.setOnMouseClicked(event -> {
                System.out.println("Selected date: " + currentDay);
            });

            calendarGrid.add(dayPane, column, row);
            column++;
            if (column > 6) {
                column = 0;
                row++;
            }
        }
    }

}
