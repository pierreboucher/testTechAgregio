package com.agregio.appagregio.infrastructure.secondary;

import java.util.stream.Stream;

import com.agregio.appagregio.domain.model.Marche;

enum MarcheEntity {
	PRIMAIRE("Réserve Primaire"), 
	SECONDAIRE("Réserve Secondaire"), 
	RAPIDE("Réserve Rapide");
	
	private String libelle;

	private MarcheEntity(String libelle) {
		this.libelle = libelle;
	}

	String getLibelle() {
		return libelle;
	}
	
	public static MarcheEntity from(Marche marche) {
		return Stream.of(MarcheEntity.values())
				.filter(c -> c.getLibelle().equals(marche.getLibelle())).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
	
	public static Marche toDomain(MarcheEntity marche) {
		return Stream.of(Marche.values()).filter(c -> c.getLibelle().equals(marche.getLibelle()))
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}

}
