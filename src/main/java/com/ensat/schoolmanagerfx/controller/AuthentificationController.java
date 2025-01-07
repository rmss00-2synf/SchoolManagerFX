package com.ensat.schoolmanagerfx.controller;

import com.ensat.schoolmanagerfx.factory.DependencyFactory;
import com.ensat.schoolmanagerfx.service.AuthentificationService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthentificationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private AuthentificationService authService;
    private String userToken;

    public void initialize() {
        this.authService = DependencyFactory.getAuthentificationService();
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
            userToken = token;
            String role = authService.getUserRole(token).orElse("UNKNOWN");
            redirectToRoleBasedDashboard(role);
        }, () -> {
            showAlert("Erreur d'authentification", "Nom d'utilisateur ou mot de passe incorrect.");
        });
    }

    @FXML
    public void handleLogout() {
        if (userToken != null) {
            authService.logout(userToken);
            userToken = null;

            Stage stage = (Stage) usernameField.getScene().getWindow();
            loadScene(stage, "Login.fxml");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            stage.setScene(new Scene(loader.load()));

            Object controller = loader.getController();
            if (controller instanceof AuthentificationController) {
                ((AuthentificationController) controller).initialize();
            }

            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger la scène : " + fxmlFile);
            e.printStackTrace();
        }
    }

    private void redirectToRoleBasedDashboard(String role) {
        Stage stage = (Stage) usernameField.getScene().getWindow();

        String fxmlFile = switch (role) {
            case "ADMIN" -> "AdminDashboard.fxml";
            case "SECRETAIRE" -> "SecretaryDashboard.fxml";
            case "PROFESSEUR" -> "ProfessorDashboard.fxml";
            default -> null;
        };

        if (fxmlFile != null) {
            loadScene(stage, fxmlFile);
        } else {
            showAlert("Erreur", "Rôle non reconnu : " + role);
        }
    }
}
