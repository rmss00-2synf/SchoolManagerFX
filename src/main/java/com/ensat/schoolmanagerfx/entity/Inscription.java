package com.ensat.schoolmanagerfx.entity;

import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.JointureDeColonne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.RelationType;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inscription {

	private int id;
	private Date date_inscription;
	@Relation(type = RelationType.ONE_TO_ONE)
	@JointureDeColonne(nom = "id_etudiant")
	private Etudiant etudiant;
	@Relation(type = RelationType.MANY_TO_ONE)
	@JointureDeColonne(nom = "id_module")
	private Module module;


	public void cancelInscrition(Inscription inscription) {
	}
	public void inscire(Etudiant student) {
	}
	public List<Etudiant> studentInAnInscription(Module module) {
		return null;
	}

}
