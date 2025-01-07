package com.ensat.schoolmanagerfx.controller;

import com.ensat.schoolmanagerfx.entity.Utilisateur;
import com.ensat.schoolmanagerfx.service.AuthentificationService;

import java.util.Optional;

public class AuthentificationController {

    private final AuthentificationService authService;

    // Injectez le service via le constructeur
    public AuthentificationController(AuthentificationService authService) {
        this.authService = authService;
    }

    // Méthode appelée lors de la tentative de connexion
    public String login(String username, String password) {
        Optional<Utilisateur> utilisateur = authService.authentifier(username, password);

        if (utilisateur.isPresent()) {
            // Authentification réussie
            Utilisateur user = utilisateur.get();
            return "Bienvenue, " + user.getUsername() + " avec le rôle : " + user.getRole();
        } else {
            // Authentification échouée
            return "Échec de la connexion. Vérifiez vos identifiants.";
        }
    }
}
