package com.ensat.schoolmanagerfx.entity;

import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.JointureDeColonne;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.RelationType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Utilisateur extends Personne {
	private String username;
	private String password;
	private String role;
	@Relation(type = RelationType.ONE_TO_ONE)
	@JointureDeColonne(nom = "id")
	private Personne personne;
//	public TeacherInformationsDTO findTeacherInformations() {
//		return null;
//	}

}
