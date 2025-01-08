package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.EtudiantDao;
import com.ensat.schoolmanagerfx.dao.InscriptionDao;
import com.ensat.schoolmanagerfx.dao.ModuleDao;
import com.ensat.schoolmanagerfx.dao.ProfesseurDao;
import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.entity.Inscription;
import com.ensat.schoolmanagerfx.entity.Module;
import com.ensat.schoolmanagerfx.entity.Professeur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StatistiquesService {
    private final EtudiantDao etudiantDao;
    private final ProfesseurDao professeurDao;
    private final ModuleDao moduleDao;
    private final InscriptionDao inscriptionDao;

    public StatistiquesService(){
        this.etudiantDao = Inject.init(EtudiantDao.class);
        this.professeurDao = Inject.init(ProfesseurDao.class);
        this.moduleDao = Inject.init(ModuleDao.class);
        this.inscriptionDao = Inject.init(InscriptionDao.class);
    }
    public int getNombreTotal(String entite){
        return switch (entite.toUpperCase()) {
            case "ETUDIANT" -> etudiantDao.findAll(Etudiant.class).size();
            case "PROFESSEUR" -> professeurDao.findAll(Professeur.class).size();
            case "MODULE" -> moduleDao.findAll(Module.class).size();
            default -> -1;
        };
    }
    public String getModulesLesPlusSuivis(){
        List<?> inscriptionList = inscriptionDao.findAll(Inscription.class);
        Map<String, Integer> map = new HashMap<>();
        for (Object inscription : inscriptionList){
            if (inscription instanceof Inscription inscription1){
                String id = inscription1.getModule().getNom_module();
                if (map.containsKey(id)){
                    map.put(id, map.get(id) + 1);
                }else map.put(id,1);
            }
        }
        int max = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue() > max){
                max = entry.getValue();
                stringBuilder.replace(0,stringBuilder.length(),entry.getKey());
            }
        }
        return stringBuilder.toString();
    }

}
