package com.ensat.schoolmanagerfx.dao;

import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Requete;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.util.List;
import java.util.Optional;

public interface EtudiantDao extends ENSATJPA<Etudiant, Long> {
    Optional<List<Etudiant>> findByMatricule(String matricule, Etudiant etudiant);
}
