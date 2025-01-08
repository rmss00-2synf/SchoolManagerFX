package com.ensat.schoolmanagerfx.dao;

import com.ensat.schoolmanagerfx.entity.Inscription;
import com.ensat.schoolmanagerfx.entity.Utilisateur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Requete;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.util.List;
import java.util.Optional;

public interface InscriptionDao extends ENSATJPA<Inscription,Long> {
    Optional<List<Inscription>> findById_module(int query, Inscription module);
    @Requete(value = "SELECT * FROM INSCRIPTION WHERE id_etudiant = ? AND id_module = ?")
    Optional<Utilisateur> findWithUserIdModuleId(String query, Inscription inscription, String password, String username);
}
