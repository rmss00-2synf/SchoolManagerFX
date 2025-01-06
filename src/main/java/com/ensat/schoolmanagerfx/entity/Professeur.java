package com.ensat.schoolmanagerfx.entity;

import com.ensat.schoolmanagerfx.entity.asset.Personne;
import com.ensat.schoolmanagerfx.entity.asset.Utilisateur;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.JointureDeColonne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.RelationType;
import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Professeur {
	private String specialite;
	private int id;
//	@Relation(type = RelationType.ONE_TO_MANY, mappedBy = "id_professeur")
//	private Collection<Module> module;
//	@Relation(type = RelationType.ONE_TO_ONE)
//	@JointureDeColonne(nom = "id")
//	private Utilisateur utilisateur;
}
