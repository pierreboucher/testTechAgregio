package com.agregio.appagregio.domain.model;

import java.util.UUID;

public class Parc {
	
	private UUID id;
	private String nom;
	private TypeParc type;
	private Double energie;
	private BlocHoraire blocHoraire;
	
	public Parc(UUID id, String nom, TypeParc type, Double energie, BlocHoraire blocHoraire) {
		
	    if (id == null) {
	      this.id = UUID.randomUUID();
	    } else {
			this.id = id;
	    }
	    
		this.nom = nom;
		this.type = type;
		this.energie = energie;
		this.blocHoraire = blocHoraire;
	}

	public Parc(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public TypeParc getType() {
		return type;
	}

	public Double getEnergie() {
		return energie;
	}

	public BlocHoraire getBlocHoraire() {
		return blocHoraire;
	}
	
}
