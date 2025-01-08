package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.InscriptionDao;
import com.ensat.schoolmanagerfx.dao.ModuleDao;
import com.ensat.schoolmanagerfx.dao.ProfesseurDao;
import com.ensat.schoolmanagerfx.dto.EtudiantDto;
import com.ensat.schoolmanagerfx.dto.ModuleDto;
import com.ensat.schoolmanagerfx.dto.ProfesseurDto;
import com.ensat.schoolmanagerfx.entity.Inscription;
import com.ensat.schoolmanagerfx.entity.Professeur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;
import com.ensat.schoolmanagerfx.entity.Module;

import java.util.ArrayList;
import java.util.List;

public class ProfesseurService {
    private final ProfesseurDao professeurDao;
    private final ModuleDao moduleDao;
    private final InscriptionDao inscriptionDao;

    public ProfesseurService() {
        this.professeurDao= Inject.init(ProfesseurDao.class);
        this.moduleDao= Inject.init(ModuleDao.class);
        this.inscriptionDao= Inject.init(InscriptionDao.class);
    }

    public List<ModuleDto> getModulesByProfesseur(int profId){
        System.out.println();
        List<Module> modules = moduleDao.findById_professeur(profId,new Module()).orElse(null);
        List<ModuleDto> moduleDtos = new ArrayList<>();

        if (modules!=null) {
            for (Module module : modules) {
                moduleDtos.add(convertToDto(module));
            }

        }
        return moduleDtos;
    }


    public List<EtudiantDto> getEtudiantsByModule(int moduleId){
        List<Inscription> inscriptions = inscriptionDao.findById_module(moduleId,new Inscription()).orElse(null);
        List<EtudiantDto> etudiantDtos = new ArrayList<>();
        if (inscriptions!=null) {
            for (Inscription inscription : inscriptions) {
                etudiantDtos.add(convertToDto(inscription));
            }
        }
        return etudiantDtos;
    }


    private ModuleDto convertToDto(Module module){
        return ModuleDto.builder()
                .id_professeur(module.getProfesseur().getId())
                .id(module.getId())
                .code_module(module.getCode_module())
                .nom_module(module.getNom_module())
                .build();

    }

    public EtudiantDto convertToDto(Inscription dto){
        return EtudiantDto.builder()
                .nom(dto.getEtudiant().getNom())
                .prenom(dto.getEtudiant().getPrenom())
                .email(dto.getEtudiant().getEmail())
                .date_naissance(dto.getEtudiant().getDate_naissance())
                .id(dto.getEtudiant().getId())
                .promotion(dto.getEtudiant().getPromotion())
                .matricule(dto.getEtudiant().getMatricule())
                .build();
    }

    public ProfesseurDto convertToDto(Professeur professeur){
        return ProfesseurDto.builder()
                .username(professeur.getUtilisateur().getUsername())
                .role(professeur.getUtilisateur().getRole())
                .specialite(professeur.getSpecialite())
                .prenom(professeur.getUtilisateur().getPersonne().getPrenom())
                .id(professeur.getUtilisateur().getPersonne().getId())
                .nom(professeur.getUtilisateur().getPersonne().getNom())
                .build();
    }

}
