package com.ensat.schoolmanagerfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField dateNaissanceField;

    @FXML
    private TextField promotionField;

    // Ajouter un étudiant
    @FXML
    protected void addStudent() {
        // Récupérer les valeurs des champs
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String dateNaissance = dateNaissanceField.getText();
        String promotion = promotionField.getText();

        // Logique d'ajout de l'étudiant dans la base de données ou TableView
        System.out.println("Étudiant ajouté : " + nom + " " + prenom);

        // Fermer la fenêtre après ajout
        closePopup();
    }

    // Fermer le popup
    @FXML
    protected void closePopup() {
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }
}
