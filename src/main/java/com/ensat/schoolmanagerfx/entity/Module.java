package com.ensat.schoolmanagerfx.entity;

import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.JointureDeColonne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.RelationType;
import lombok.*;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Module {
	private int id;
	private String nom_module;
	private String code_module;
	@Relation(type = RelationType.ONE_TO_MANY)
	private Collection<Inscription> inscription;
	@Relation(type = RelationType.MANY_TO_ONE)
	@JointureDeColonne(nom = "id_professeur")
	private Professeur professeur;
	public boolean assigneAModuleToTeacher(Professeur teacher) {
		return false;
	}
	public boolean assigneStudents(List<Etudiant> students) {
		return false;
	}

}
