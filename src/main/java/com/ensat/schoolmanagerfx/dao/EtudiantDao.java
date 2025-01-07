package com.ensat.schoolmanagerfx.dao;

import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Requete;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.util.List;
import java.util.Optional;

public interface EtudiantDao extends ENSATJPA<Etudiant, Long> {
<<<<<<< HEAD

    Optional<List<Etudiant>> findByMatricule(String query, Etudiant etudiant);
    @Requete(value = "SELECT * FROM ETUDIANT;")
    Optional<List<Etudiant>> lesEtudiants(String query, Etudiant etudiant);

}
=======
    Optional<List<Etudiant>> findByMatricule(String matricule, Etudiant etudiant);
}
>>>>>>> dfd32e26dd98459e4ab771438bbfc73896490fae
