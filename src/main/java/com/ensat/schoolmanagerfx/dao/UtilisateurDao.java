package com.ensat.schoolmanagerfx.dao;

import com.ensat.schoolmanagerfx.entity.Utilisateur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Requete;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.util.Optional;

public interface UtilisateurDao extends ENSATJPA<Utilisateur,Long> {

    @Requete(value = "SELECT * FROM UTILISATEUR WHERE username = ? AND password = ?")
    Optional<Utilisateur> findCredentials(String query,Utilisateur utilisateur,String username,String password);
}
