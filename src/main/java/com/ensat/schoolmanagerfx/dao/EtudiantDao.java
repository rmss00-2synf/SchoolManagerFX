package com.ensat.schoolmanagerfx.dao;

import com.ensat.schoolmanagerfx.dto.EtudiantDto;
import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

public interface EtudiantDao extends ENSATJPA<Etudiant, Long> {
}
