package com.ensat.schoolmanagerfx.entity;

import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.JointureDeColonne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.RelationType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Professeur extends Utilisateur {
	private String specialite;
	@Relation(type = RelationType.ONE_TO_MANY, mappedBy = "id_professeur")
	private List<Module> module;
	@Relation(type = RelationType.ONE_TO_ONE)
	@JointureDeColonne(nom = "id")
	private Utilisateur utilisateur;
}
