package com.agregio.appagregio.domain.model;

public enum TypeParc {
	PV("Parc Photovoltaïque"), 
	HYDRAU("Centrale Hydraulique"), 
	EOLIEN("Parc Eolien");
	
	private String libelle;

	private TypeParc(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}
	
}
