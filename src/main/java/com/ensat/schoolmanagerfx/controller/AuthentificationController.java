package com.ensat.schoolmanagerfx.controller;

import com.ensat.schoolmanagerfx.controller.admin.DashBoardController;
import com.ensat.schoolmanagerfx.service.AuthentificationService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class AuthentificationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final AuthentificationService authService;
    private Optional<String> userToken;

    public AuthentificationController() {
        this.authService = new AuthentificationService();
        this.userToken = Optional.empty(); // Initialize userToken as empty
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Erreur de saisie", "Veuillez remplir tous les champs.");
            return;
        }

        authService.login(username, password).ifPresentOrElse(token -> {
            userToken = Optional.of(token);
            System.out.println("Hello 1"+userToken.get());
            try {
                System.out.println("Hello 2"+userToken.get());
                String role = authService.getUserRole(token).orElse(null);
                System.out.println("Hello 3"+role);
                redirectToRoleBasedDashboard(role);
            } catch (Exception e) {
                showAlert("Erreur", "E" + e.getMessage());
            }
        }, () -> {
            showAlert("Erreur d'authentification", "Nom d'utilisateur ou mot de passe incorrect.");
        });
    }

    @FXML
    public void handleLogout() {
        userToken.ifPresent(token -> {
            authService.logout(token);
            userToken = Optional.empty(); // Clear the user token

            Stage stage = (Stage) usernameField.getScene().getWindow();
            loadScene(stage, "Login.fxml");
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadScene(Stage stage, String fxmlFile) {
        try {
            System.out.println(fxmlFile);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load(), 320, 240);
            System.out.println(fxmlFile);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la scène : " + fxmlFile);
            e.printStackTrace();
        }
    }

    private void redirectToRoleBasedDashboard(String role) {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        System.out.println(role);
        // Map roles to their corresponding FXML files
        Map<String, String> roleToFxmlMap = Map.of(
                "ADMIN", "/views/admin/DashBoard-view.fxml",
                "SECRETAIRE", "SecretaryDashboard.fxml",
                "PROFESSEUR", "ProfessorDashboard.fxml"
        );

        String fxmlFile = roleToFxmlMap.get(role);
        System.out.println(fxmlFile);
        if (fxmlFile != null) {
            loadScene(stage, fxmlFile);
        } else {
            showAlert("Erreur", "Rôle non reconnu : " + role);
        }
    }
}