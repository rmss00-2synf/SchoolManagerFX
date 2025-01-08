package com.ensat.schoolmanagerfx.controller.admin;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;


public class DashBoardController {

    @FXML
    private VBox sidebar;

    @FXML
    private HBox homeHBox, aboutHBox, pagesHBox, portfolioHBox, contactHBox, InscriHBox;

    @FXML
    private Label contentTitle;



    private boolean isSidebarCollapsed = false; // Indique si la barre latérale est réduite
    public DashBoardController() {}
    @FXML
    protected void initialize() {
        // Ajouter des icônes aux boutons
        addIconToHBox(homeHBox, "/icons/dashboard.png");
        addIconToHBox(aboutHBox, "/icons/graduated1.png");
        addIconToHBox(pagesHBox, "/icons/teacher.png");
        addIconToHBox(portfolioHBox, "/icons/project-manager.png");
        addIconToHBox(contactHBox, "/icons/inscription.png");
        addIconToHBox(InscriHBox, "/icons/stack-of-books.png");
        navigateHome();
    }

    private void addIconToHBox(HBox hbox, String iconPath) {
        // Charger l'image
        Image iconImage = new Image(getClass().getResourceAsStream(iconPath));
        ImageView iconView = new ImageView(iconImage);

        // Définir la taille de l'icône
        iconView.setFitWidth(20);
        iconView.setFitHeight(20);

        // Ajouter l'icône au début du HBox
        hbox.getChildren().add(0, iconView);
    }

    @FXML
    protected void toggleSidebar() {
        if (isSidebarCollapsed) {
            // Rétablir la taille complète de la barre latérale
            sidebar.setPrefWidth(250);
            sidebar.getChildren().forEach(node -> node.setVisible(true)); // Afficher le texte des boutons
        } else {
            // Réduire la barre latérale à une largeur minimale
            sidebar.setPrefWidth(60); // Largeur minimale
            sidebar.getChildren().forEach(node -> {
                if (node instanceof HBox) {
                    ((HBox) node).getChildren().forEach(child -> {
                        if (child instanceof Label) {
                            child.setVisible(false); // Masquer le texte
                        }
                    });
                }
            });
        }
        isSidebarCollapsed = !isSidebarCollapsed;
    }



    @FXML
    private VBox contentArea;

    @FXML
    protected void navigateHome() {
        try {
            // Charger la vue Home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ensat/schoolmanagerfx/Home-view.fxml"));
            Parent homeRoot = loader.load();

            // Réinitialiser la zone de contenu (contentArea) uniquement
            contentArea.getChildren().clear();

            // Ajouter la vue Home au contentArea
            contentArea.getChildren().add(homeRoot);

            System.out.println("Navigated to Home");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load Home-view.fxml");
        }
    }

    @FXML
    protected void navigateAbout() {
        try {
            // Charger la vue Home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/Student-view.fxml"));
            Parent homeRoot = loader.load();

            // Réinitialiser la zone de contenu (contentArea) uniquement
            contentArea.getChildren().clear();

            // Ajouter la vue Home au contentArea
            contentArea.getChildren().add(homeRoot);

            System.out.println("Navigated to Home");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load Home-view.fxml");
        }
    }

    @FXML
    protected void navigatePages() {
        try {
            // Charger la vue Home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/Prof-view.fxml"));
            Parent homeRoot = loader.load();

            // Réinitialiser la zone de contenu (contentArea) uniquement
            contentArea.getChildren().clear();

            // Ajouter la vue Home au contentArea
            contentArea.getChildren().add(homeRoot);

            System.out.println("Navigated to Home");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load Home-view.fxml");
        }
    }

    @FXML
    protected void navigatePortfolio() {
        try {
            // Charger la vue Home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/Secretaire-view.fxml"));
            Parent homeRoot = loader.load();

            // Réinitialiser la zone de contenu (contentArea) uniquement
            contentArea.getChildren().clear();

            // Ajouter la vue Home au contentArea
            contentArea.getChildren().add(homeRoot);

            System.out.println("Navigated to Home");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load Home-view.fxml");
        }
    }

    @FXML
    protected void InscriHBox() {
        try {
            // Charger la vue Home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/Module-view.fxml"));
            Parent homeRoot = loader.load();

            // Réinitialiser la zone de contenu (contentArea) uniquement
            contentArea.getChildren().clear();

            // Ajouter la vue Home au contentArea
            contentArea.getChildren().add(homeRoot);

            System.out.println("Navigated to Home");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load Home-view.fxml");
        }
    }

    @FXML
    protected void navigateContact() {
        try {
            // Charger la vue Home
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin/Inscription-view.fxml"));
            Parent homeRoot = loader.load();

            // Réinitialiser la zone de contenu (contentArea) uniquement
            contentArea.getChildren().clear();

            // Ajouter la vue Home au contentArea
            contentArea.getChildren().add(homeRoot);

            System.out.println("Navigated to Home");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load Home-view.fxml");
        }
    }

}
