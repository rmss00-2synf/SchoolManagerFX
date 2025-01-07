package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.ModuleDao;
import com.ensat.schoolmanagerfx.dao.ProfesseurDao;
import com.ensat.schoolmanagerfx.dto.EtudiantDto;
import com.ensat.schoolmanagerfx.dto.ModuleDto;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;
import com.ensat.schoolmanagerfx.entity.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfesseurService {
    private final ProfesseurDao professeurDao;
    private final ModuleDao moduleDao;

    public ProfesseurService() {
        this.professeurDao= Inject.init(ProfesseurDao.class);
        this.moduleDao= Inject.init(ModuleDao.class);
    }

    public List<ModuleDto> getModulesByProfesseur(int profId){
        System.out.println();
        List<Module> modules = moduleDao.findById_professeur(profId,new Module()).orElse(null);
        List<ModuleDto> moduleDtos = new ArrayList<>();

        if (modules!=null) {
            for (Module module : modules) {
                moduleDtos.add(convertToDto(module));
            }

        } else {
            throw new RuntimeException("Il n'ya pas de module pour ce prof");
        }
        return moduleDtos;
    }


    public List<EtudiantDto> getEtudiantsByModule(int moduleId){
        return null;
    }


    private ModuleDto convertToDto(Module module){
        return ModuleDto.builder()
                .id_professeur(module.getId_professeur())
                .id(module.getId())
                .code_module(module.getCode_module())
                .nom_module(module.getNom_module())
                .build();

    }

}
