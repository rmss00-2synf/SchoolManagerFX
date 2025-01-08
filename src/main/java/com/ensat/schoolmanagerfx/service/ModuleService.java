package com.ensat.schoolmanagerfx.service;

import com.ensat.schoolmanagerfx.dao.ModuleDao;
import com.ensat.schoolmanagerfx.dao.ProfesseurDao;
import com.ensat.schoolmanagerfx.dto.ModuleDto;
import com.ensat.schoolmanagerfx.entity.Module;
import com.ensat.schoolmanagerfx.entity.Professeur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.proxy.Inject;

public class ModuleService {
    private final ModuleDao moduleDao;
    private final ProfesseurDao professeurDao;
    public ModuleService() {
        this.moduleDao= Inject.init(ModuleDao.class);
        this.professeurDao = Inject.init(ProfesseurDao.class);
    }

    public boolean ajouterModule(ModuleDto moduleDto){
        Module module = convertDtoToModule(moduleDto);
        if (module!=null){
            return moduleDao.save(module);
        }else throw new NullPointerException("Le module est obligatoire");

    }

    public boolean attribuerProfesseur(int moduleId, int profId){
        System.out.println(professeurDao.findById(Professeur.class, (long) profId).orElse(null));
        Professeur professeur = professeurDao.findById(Professeur.class, (long) profId).orElse(null);
        if(professeur != null){
            Module module = moduleDao.findById(Module.class, (long) moduleId).orElse(null);
            if (module != null){
                module.setProfesseur(professeur);
                ModuleDto moduleDto = convertModuleToDto(module);
                return moduleDao.update(moduleDto);
            }
            throw new NullPointerException("Le module est obligatoire");
        }
        return false;
    }

    private Module convertDtoToModule(ModuleDto moduleDto){
        Professeur professeur = professeurDao.findById(Professeur.class, (long) moduleDto.getId_professeur()).orElse(null);

        if(professeur != null){
            return Module.builder()
                    .id(moduleDto.getId())
                    .code_module(moduleDto.getCode_module())
                    .nom_module(moduleDto.getNom_module())
                    .inscription(null)
                    .professeur(professeur)
                    .build();
        }
        return null;

    }
    private ModuleDto convertModuleToDto(Module module){
        return ModuleDto.builder()
                .nom_module(module.getNom_module())
                .code_module(module.getCode_module())
                .id_professeur(module.getProfesseur().getId())
                .id(module.getId())
                .build();
    }
}
