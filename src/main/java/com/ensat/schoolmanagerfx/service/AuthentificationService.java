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
        try {
            // Use a method that explicitly takes username and password as parameters
            Optional<Utilisateur> user = utilisateurDao.findCredentials(username, password);

            if (user.isPresent()) {
                // Generate a more secure token (this is a simple example, consider using JWT in a real application)
                return Optional.of(generateToken(username));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<String> getUserRole(String token) {
        try {
            String username = extractUsernameFromToken(token);
            return utilisateurDao.findByUsername(username).map(Utilisateur::getRole);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error getting user role: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void logout(String token) {
        // Implement logout logic if needed (e.g., invalidate token)
        System.out.println("User logged out: " + extractUsernameFromToken(token));
    }

    private String generateToken(String username) {
        // In a real application, use a more secure method to generate tokens
        return "USER_TOKEN_" + username + "_" + System.currentTimeMillis();
    }

    private String extractUsernameFromToken(String token) {
        // Simple extraction, adapt based on your token generation logic
        return token.split("_")[2];
    }
}