package com.agregio.appagregio.domain.model;

import java.util.UUID;

public class Offre {

	private UUID id;
	private Marche marche;
	private Double energie;
	private BlocHoraire blocHoraire;
	private Double prixPlancher;
	private Parc parc;

	public Offre(UUID id, Marche marche, Double energie, BlocHoraire blocHoraire, Double prixPlancher, Parc parc) {
		
	    if (id == null) {
	      this.id = UUID.randomUUID();
	    } else {
			this.id = id;
	    }

		this.marche = marche;
		this.energie = energie;
		this.blocHoraire = blocHoraire;
		this.prixPlancher = prixPlancher;
		this.parc = parc;
	}

	public UUID getId() {
		return id;
	}

	public Marche getMarche() {
		return marche;
	}

	public Double getEnergie() {
		return energie;
	}

	public BlocHoraire getBlocHoraire() {
		return blocHoraire;
	}

	public Double getPrixPlancher() {
		return prixPlancher;
	}

	public Parc getParc() {
		return parc;
	}
	
}
