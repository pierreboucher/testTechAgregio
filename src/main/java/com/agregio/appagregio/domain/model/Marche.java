package com.agregio.appagregio.domain.model;

public enum Marche {
	PRIMAIRE("Réserve Primaire"), 
	SECONDAIRE("Réserve Secondaire"), 
	RAPIDE("Réserve Rapide");
	
	private String libelle;

	private Marche(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}
	

}
