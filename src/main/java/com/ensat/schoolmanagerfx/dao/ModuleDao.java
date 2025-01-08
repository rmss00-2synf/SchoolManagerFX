package com.ensat.schoolmanagerfx.dao;

import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.entity.Module;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.util.List;
import java.util.Optional;

public interface ModuleDao extends ENSATJPA<Module,Long> {
    Optional<List<Module>> findById_professeur(int query, Module module);
}
