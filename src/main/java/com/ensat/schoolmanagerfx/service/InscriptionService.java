package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.EtudiantDao;
import com.ensat.schoolmanagerfx.dao.InscriptionDao;
import com.ensat.schoolmanagerfx.dao.ModuleDao;
import com.ensat.schoolmanagerfx.dto.EtudiantDto;
import com.ensat.schoolmanagerfx.dto.InscriptionDto;
import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.entity.Inscription;
import com.ensat.schoolmanagerfx.entity.Module;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;

import java.util.Date;

public class InscriptionService {

    private final InscriptionDao inscriptionDao;
    private final EtudiantDao etudiantDao;
    private final ModuleDao moduleDao;

    public InscriptionService() {
        this.inscriptionDao = Inject.init(InscriptionDao.class);
        this.etudiantDao = Inject.init(EtudiantDao.class);
        this.moduleDao = Inject.init(ModuleDao.class);
    }

    public boolean inscrireEtudiant(int etudiantId, int moduleId){
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        InscriptionDto inscriptionDto = InscriptionDto.builder()
                .id_etudiant(etudiantId)
                .date_inscription(sqlDate)
                .id_module(moduleId)
                .build();
        return inscriptionDao.save(inscriptionDto);
    }
    public boolean annulerInscription(int etudiantId, int moduleId){

        return false;
    }

    private Inscription convertDtoToInscription(InscriptionDto inscriptionDto){
        Long idEtd = (long) inscriptionDto.getId_etudiant();
        Long idModule = (long) inscriptionDto.getId_module();
        System.out.println(idEtd+"ID ============================== ID"+idModule);
        Etudiant etudiant = etudiantDao.findById(Etudiant.class, idEtd).orElse(null);
        Module module = moduleDao.findById(Module.class, idModule).orElse(null);
        return Inscription.builder()
                .id(inscriptionDto.getId())
                .date_inscription(inscriptionDto.getDate_inscription())
                .etudiant(etudiant)
                .module(module)
                .build();
    }

    public static void main(String[] args) {
        InscriptionService inscriptionService = new InscriptionService();
        System.out.println(inscriptionService.inscrireEtudiant(20, 1));
    }
}
