package com.ensat.schoolmanagerfx.utils.ensatjpa.config;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

@Getter
@Setter
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final Connection connection;

    private DatabaseConnection() {
        try {
            // Charger la configuration depuis le fichier XML
            DatabaseConfig config = loadDatabaseConfig("src/main/resources/database-config.xml");

            System.out.println("Chargement du driver...");
            Class.forName(config.DRIVER());
            System.out.println("Driver chargé avec succès.");
            System.out.println("Connexion à la base de données...");

            connection = DriverManager.getConnection(config.URL(), config.USERNAME(), config.PASSWORD());
            System.out.println("Connexion réussie.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC non trouvé : " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConfig loadDatabaseConfig(String filePath) throws Exception {
        File file = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        Element root = doc.getDocumentElement();
        String driver = root.getElementsByTagName("DRIVER").item(0).getTextContent();
        String url = root.getElementsByTagName("URL").item(0).getTextContent();
        String username = root.getElementsByTagName("USERNAME").item(0).getTextContent();
        String password = root.getElementsByTagName("PASSWORD").item(0).getTextContent();
        return new DatabaseConfig(driver, url, username, password);
    }
}
