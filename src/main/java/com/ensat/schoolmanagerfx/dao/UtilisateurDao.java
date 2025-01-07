package com.ensat.schoolmanagerfx.dao;

import com.ensat.schoolmanagerfx.entity.Utilisateur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Requete;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.util.List;
import java.util.Optional;

public interface UtilisateurDao extends ENSATJPA<Utilisateur, Long> {

    @Requete(value = "SELECT * FROM utilisateur WHERE username = ? AND password = ?")
    Optional<Utilisateur> findCredentials(String query, Utilisateur utilisateur, String password, String username);

    Optional<String> findRoleByUsername(String username, Utilisateur utilisateur);
}


