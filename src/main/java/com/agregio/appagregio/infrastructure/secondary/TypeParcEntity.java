package com.agregio.appagregio.infrastructure.secondary;

enum TypeParcEntity {
	PV("Parc Photovoltaïque"), 
	HYDRAU("Centrale Hydraulique"), 
	EOLIEN("Parc Eolien");
	
	private String libelle;

	private TypeParcEntity(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

}
