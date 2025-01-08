package com.ensat.schoolmanagerfx.dto;

import com.ensat.schoolmanagerfx.entity.Etudiant;
import com.ensat.schoolmanagerfx.entity.Module;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.JointureDeColonne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.RelationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InscriptionDto {
    private int id;
    private Date date_inscription;
    private int id_etudiant;
    private int id_module;
}
