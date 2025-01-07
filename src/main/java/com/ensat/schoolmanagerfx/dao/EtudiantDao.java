package com.ensat.schoolmanagerfx.dao;

import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Requete;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.util.List;
import java.util.Optional;

public interface EtudiantDao extends ENSATJPA<Etudiant, Long> {
    Optional<List<Etudiant>> findByMatricule(String query, Etudiant etudiant);
    @Requete(value = "SELECT * FROM ETUDIANT;")
    Optional<List<Etudiant>> lesEtudiants(String query, Etudiant etudiant);
    @Requete(value = "SELECT e FROM Etudiant e WHERE " +
            "LOWER(e.nom) LIKE LOWER(CONCAT('%', :criteria, '%')) OR " +
            "LOWER(e.prenom) LIKE LOWER(CONCAT('%', :criteria, '%')) OR " +
            "LOWER(e.email) LIKE LOWER(CONCAT('%', :criteria, '%'))")
    List<Etudiant> findByCriteria(String query, String criteria);
}
