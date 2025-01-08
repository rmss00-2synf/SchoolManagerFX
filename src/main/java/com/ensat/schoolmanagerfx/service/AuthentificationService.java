package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.UtilisateurDao;
import com.ensat.schoolmanagerfx.entity.Utilisateur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;

import java.util.Optional;

public class AuthentificationService {

    private final UtilisateurDao utilisateurDao;

    // Injection via init
    public AuthentificationService() {
        this.utilisateurDao = Inject.init(UtilisateurDao.class);
    }

    public Optional<String> login(String username, String password) {
        try {
            // Recherche de l'utilisateur avec les identifiants
            Optional<Utilisateur> user = utilisateurDao.findCredentials(username, password);

            if (user.isPresent()) {
                // Génération d'un token (améliorable avec JWT pour une sécurité accrue)
                return Optional.of(generateToken(username));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // Gestion des exceptions
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<String> getUserRole(String token) {
        try {
            // Extraction du nom d'utilisateur depuis le token
            String username = extractUsernameFromToken(token);
            return utilisateurDao.findByUsername(username).map(Utilisateur::getRole);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du rôle utilisateur : " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void logout(String token) {
        // Implémentation simplifiée de la déconnexion
        System.out.println("Utilisateur déconnecté : " + extractUsernameFromToken(token));
    }

    private String generateToken(String username) {
        // Génération simplifiée d'un token
        return "USER_TOKEN_" + username + "_" + System.currentTimeMillis();
    }

    private String extractUsernameFromToken(String token) {
        // Extraction simplifiée du nom d'utilisateur
        return token.split("_")[2];
    }
}
