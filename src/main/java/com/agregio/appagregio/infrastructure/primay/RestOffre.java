package com.agregio.appagregio.infrastructure.primay;

import java.util.UUID;

import com.agregio.appagregio.domain.model.Marche;
import com.agregio.appagregio.domain.model.Offre;
import com.agregio.appagregio.domain.model.Parc;

class RestOffre {

	private UUID id;
	private Marche marche;
	private Double energie;
	private RestBlocHoraire blocHoraire;
	private Double prixPlancher;
	private RestParc parc;

	public RestOffre(UUID id, Marche marche, Double energie, RestBlocHoraire blocHoraire, Double prixPlancher,
			RestParc parc) {
		this.id = id;
		this.marche = marche;
		this.energie = energie;
		this.blocHoraire = blocHoraire;
		this.prixPlancher = prixPlancher;
		this.parc = parc;
	}

	public Offre toDomain() {
		return new Offre(getId(), getMarche(), getEnergie(), getBlocHoraire().toDomain(), getPrixPlancher(),new Parc(getParc().getId()));
	}

	public static RestOffre from(Offre offre) {
		return new RestOffre(offre.getId(), offre.getMarche(), offre.getEnergie(),
				RestBlocHoraire.from(offre.getBlocHoraire()), offre.getPrixPlancher(), RestParc.from(offre.getParc()));
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

	public RestBlocHoraire getBlocHoraire() {
		return blocHoraire;
	}

	public Double getPrixPlancher() {
		return prixPlancher;
	}

	public RestParc getParc() {
		return parc;
	}

}
