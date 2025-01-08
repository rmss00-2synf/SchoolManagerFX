package com.ensat.schoolmanagerfx.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentController {

    @FXML
    protected void showAddStudentPopup() {
        try {
            // Charger le fichier FXML pour le formulaire d'ajout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ensat/schoolmanagerfx/admin/AddStudentForm.fxml"));
            Parent root = loader.load();

            // Créer un nouveau stage pour afficher le formulaire
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // Modal window
            stage.setTitle("Ajouter Étudiant");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement du formulaire d'ajout.");
        }
    }
}
