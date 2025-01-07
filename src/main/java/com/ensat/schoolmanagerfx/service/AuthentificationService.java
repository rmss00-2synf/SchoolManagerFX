package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.UtilisateurDao;
import com.ensat.schoolmanagerfx.entity.Utilisateur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;

import java.util.Optional;

public class AuthentificationService {

    private final UtilisateurDao utilisateurDao;

    // Injectez le DAO via le constructeur
    public AuthentificationService() {
        this.utilisateurDao = Inject.init(UtilisateurDao.class);
    }

    // Méthode pour valider les identifiants
    public Optional<Utilisateur> authentifier(String username, String password) {
        // Construire une requête SQL pour chercher l'utilisateur
        String query = "SELECT * FROM Utilisateur WHERE username = '" + username + "' AND password = '" + password + "'";

        // Appeler findByAttribute avec la requête SQL
//        Optional<?> result = utilisateurDao.findByAttribute(query, Utilisateur.class);

        // Vérifier le résultat et effectuer le casting approprié
//        if (result.isPresent()) {
//            return Optional.of((Utilisateur) result.get());
//        }
        return Optional.empty();
    }

    // Méthode pour vérifier si un utilisateur a un rôle spécifique
    public boolean verifierRole(Utilisateur utilisateur, String role) {
        return utilisateur.getRole() != null && utilisateur.getRole().equals(role);
    }
}
