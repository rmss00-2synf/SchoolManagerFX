package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.EtudiantDao;
import com.ensat.schoolmanagerfx.dto.EtudiantDto;
import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EtudiantService {
    private final EtudiantDao etudiantDao;

    public EtudiantService() {
        this.etudiantDao = Inject.init(EtudiantDao.class);
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        etudiantDao.save(etudiant);
    }

    public void modifierEtudiant(Etudiant etudiant) {
        etudiantDao.update(etudiant);
    }

    public List<EtudiantDto> rechercherEtudiants(String critere) {
        List<Etudiant> etudiants = etudiantDao.findByCriteria("", critere);
        List<EtudiantDto> etudiantDtos = new ArrayList<>();

        for (Etudiant etudiant : etudiants) {
            etudiantDtos.add(convertToDto(etudiant));
        }
        return etudiantDtos;
    }

    private EtudiantDto convertToDto(Etudiant etudiant) {
        return EtudiantDto.builder()
                .id(etudiant.getId())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .email(etudiant.getEmail())
                .date_naissance(etudiant.getDate_naissance())
                .promotion(etudiant.getPromotion())
                .matricule(etudiant.getMatricule())
                .build();
    }
}