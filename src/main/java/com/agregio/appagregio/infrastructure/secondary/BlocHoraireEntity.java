package com.agregio.appagregio.infrastructure.secondary;

import javax.persistence.Embeddable;

import com.agregio.appagregio.domain.model.BlocHoraire;

@Embeddable
class BlocHoraireEntity {

	private int heureDebut;
	private int heureFin;
	
	public BlocHoraireEntity(int heureDebut, int heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	public BlocHoraireEntity() {}
	
	public static BlocHoraireEntity from(BlocHoraire blocHoraire) {
		return new BlocHoraireEntity(blocHoraire.getHeureDebut(),blocHoraire.getHeureFin());
	}
	
	public int getHeureDebut() {
		return heureDebut;
	}
	
	public int getHeureFin() {
		return heureFin;
	}

	public BlocHoraire toDomain() {
		return new BlocHoraire(getHeureDebut(),getHeureFin());
	}

}
