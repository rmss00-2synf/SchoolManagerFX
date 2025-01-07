package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.UtilisateurDao;
import com.ensat.schoolmanagerfx.entity.Utilisateur;

import java.util.Optional;

public class AuthentificationService {

    private final UtilisateurDao utilisateurDao;

    public AuthentificationService(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public UtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    public Optional<String> login(String username, String password) {
        // Vérifier les identifiants dans la base de données
        Optional<Utilisateur> user = utilisateurDao.findCredentials(username, password);

        if (user.isPresent()) {
            // Retourner un jeton simple (par exemple, le nom d'utilisateur ou un UUID si nécessaire)
            return Optional.of("USER_TOKEN_" + username);
        } else {
            // Retourner vide si l'utilisateur n'est pas trouvé
            return Optional.empty();
        }
    }

    public Optional<String> getUserRole(String token) {
        // Exemple simple pour extraire le rôle depuis un token (ou base de données)
        if (token.startsWith("USER_TOKEN_")) {
            String username = token.replace("USER_TOKEN_", "");
            return utilisateurDao.findByUsername(username).map(Utilisateur::getRole);
        }
        return Optional.empty();
    }

    public void logout(String token) {
        // Si vous avez une gestion de session ou autre, nettoyez ici
        System.out.println("User logged out: " + token);
    }
}
