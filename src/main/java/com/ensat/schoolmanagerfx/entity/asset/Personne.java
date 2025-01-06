package com.ensat.schoolmanagerfx.entity.asset;

import com.ensat.schoolmanagerfx.entity.Module;
import com.ensat.schoolmanagerfx.utils.ensatjpa.anotations.Relation;
import com.ensat.schoolmanagerfx.utils.ensatjpa.config.RelationType;
import lombok.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Personne {
	private int id;
	private String nom;
	private String prenom;
//	public Optional<List<Module>> findPersonModules(Personne person) {
//		return null;
//	}
}
