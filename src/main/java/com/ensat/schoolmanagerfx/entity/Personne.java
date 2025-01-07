package com.ensat.schoolmanagerfx.entity;

import lombok.*;

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
