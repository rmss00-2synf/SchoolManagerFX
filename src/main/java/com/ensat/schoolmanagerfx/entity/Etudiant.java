package com.ensat.schoolmanagerfx.entity;

import com.ensat.schoolmanagerfx.entity.asset.Personne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.JointureDeColonne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.RelationType;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Etudiant {
	private String matricule;
	private Date date_naissance;
	private String email;
	private int promotion;
	@Relation(type=RelationType.ONE_TO_ONE,mappedBy = "id_etudiant")
	private Inscription inscription;
	@Relation(type = RelationType.ONE_TO_ONE)
	@JointureDeColonne(nom = "id")
	private Personne personne;

}
