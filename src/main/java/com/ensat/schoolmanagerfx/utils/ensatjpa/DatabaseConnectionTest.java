//package com.ensat.schoolmanagerfx.utils.ensatjpa;
//
//import com.ensat.schoolmanagerfx.utils.ensatjpa.config.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class DatabaseConnectionTest {
//    public static void main(String[] args) {
//        try {
//            // Obtenir l'instance de connexion
//            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
//
//            // Vérifier si la connexion est établie
//            Connection connection = databaseConnection.getConnection();
//
//            if (connection != null) {
//                System.out.println("Connexion réussie à la base de données !");
//
//                // Tester une requête simple pour s'assurer que tout fonctionne
//                Statement statement = connection.createStatement();
//                String sql = "SELECT 1 AS test";
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()) {
//                    int testValue = resultSet.getInt("test");
//                    System.out.println("Requête test réussie : " + testValue);
//                }
//
//                // Fermer la connexion pour le test
//                // connection.close();
//                // System.out.println("Connexion fermée avec succès.");
//            } else {
//                System.out.println("La connexion à la base de données a échoué.");
//            }
//        } catch (Exception e) {
//            System.err.println("Erreur lors du test de connexion à la base de données : " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
//
