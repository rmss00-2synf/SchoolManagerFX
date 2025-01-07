package com.ensat.schoolmanagerfx;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.LocalDate;

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

//    @FXML
//    private Label agendaLabel; // Pour afficher l'agenda.
//
//    @FXML
//    private VBox attendanceChartBox; // Conteneur pour le graphique de présence.

//    @FXML
//    private Label contentTitle;
//
//    @FXML
//    private Label contentBody;
//
//    @FXML
//    private VBox mainContent;
//    private boolean isSidebarCollapsed = false;
//
//    @FXML
//    private VBox sidebar;

//    @FXML
//    protected void toggleSidebar() {
//        if (isSidebarCollapsed) {
//            // Rétablir la taille complète de la barre latérale
//            sidebar.setPrefWidth(250);
//            sidebar.getChildren().forEach(node -> node.setVisible(true)); // Afficher le texte des boutons
//        } else {
//            // Réduire la barre latérale à une largeur minimale
//            sidebar.setPrefWidth(60); // Largeur minimale
//            sidebar.getChildren().forEach(node -> {
//                if (node instanceof HBox) {
//                    ((HBox) node).getChildren().forEach(child -> {
//                        if (child instanceof Label) {
//                            child.setVisible(false); // Masquer le texte
//                        }
//                    });
//                }
//            });
//        }
//        isSidebarCollapsed = !isSidebarCollapsed;
//    }

//    public void initialize() {
//        // Charger les données dynamiques (exemple statique ici)
//        updateStatistics(124684, 12379, 29300, 95800);
//
//        // Initialiser le calendrier avec la date du jour.
//        initializeCalendar();
//
//        // Charger les données de l'agenda et du graphique.
//        updateAgenda(LocalDate.now());
//        //initializeAttendanceChart();
//    }

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

    /**
     * Mise à jour de l'agenda en fonction de la date sélectionnée.
     */
//    private void updateAgenda(LocalDate date) {
//        // Exemple de données dynamiques pour l'agenda.
//        if (date.equals(LocalDate.now())) {
//            agendaLabel.setText("Agenda pour aujourd'hui :\n" +
//                    "08:00 AM - Homeroom & Announcement\n" +
//                    "10:00 AM - Math Review & Practice\n" +
//                    "10:30 AM - Science Experiment & Discussion");
//        } else {
//            agendaLabel.setText("Agenda pour " + date + " :\n" +
//                    "Aucun événement programmé.");
//        }
//    }

    /**
     * Initialisation d'un graphique de présence (statique ici, peut être dynamique).
     */
//    private void initializeAttendanceChart() {
//        // Exemple : Vous pouvez utiliser des bibliothèques comme JFreeChart ou JavaFX Charts.
//        // Pour l'instant, un simple message :
//        Label chartPlaceholder = new Label("Graphique de présence à insérer ici.");
//        attendanceChartBox.getChildren().add(chartPlaceholder);
//    }
}
