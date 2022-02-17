package com.agregio.appagregio.infrastructure.primay;

import java.util.UUID;

import com.agregio.appagregio.domain.model.Parc;
import com.agregio.appagregio.domain.model.TypeParc;

class RestParc {
	
	private UUID id;
	private String nom;
	private TypeParc type;
	private Double energie;
	private RestBlocHoraire blocHoraire;
	
	public RestParc(UUID id, String nom, TypeParc type, Double energie, RestBlocHoraire blocHoraire) {
		this.id = id;
		this.nom = nom;
		this.type = type;
		this.energie = energie;
		this.blocHoraire = blocHoraire;
	}
	
	static RestParc from(Parc parc) {
		return new RestParc(parc.getId(), parc.getNom(), parc.getType(), parc.getEnergie(), RestBlocHoraire.from(parc.getBlocHoraire()));
	}
	
	Parc toDomain() {
		return new Parc(getId(), getNom(), getType(), getEnergie(), getBlocHoraire().toDomain());
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

	public RestBlocHoraire getBlocHoraire() {
		return blocHoraire;
	}
	

}
