//package com.ensat.schoolmanagerfx;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class DashBoardController {
//
//    @FXML
//    protected void onBackButtonClick(javafx.event.ActionEvent event) {
//        try {
//            // Charger la vue initiale (Hello-view.fxml)
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hello-view.fxml"));
//            Parent helloRoot = fxmlLoader.load();
//
//            // Obtenir la scène actuelle à partir du bouton
//            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//
//            // Remplacer la scène par la vue Hello
//            Scene helloScene = new Scene(helloRoot);
//            stage.setScene(helloScene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Erreur : Impossible de charger Hello-view.fxml");
//        }
//    }
//
//}
