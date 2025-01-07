package com.ensat.schoolmanagerfx.dao;

import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Requete;
import com.ensat.schoolmanagerfx.utils.ensatjpa.dbmanip.ENSATJPA;

import java.util.List;

public interface EtudiantDao extends ENSATJPA<Etudiant, Long> {

}