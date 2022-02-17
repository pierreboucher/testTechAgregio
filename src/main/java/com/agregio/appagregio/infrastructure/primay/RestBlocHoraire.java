package com.agregio.appagregio.infrastructure.primay;

import com.agregio.appagregio.domain.model.BlocHoraire;

class RestBlocHoraire {

	private int heureDebut;
	private int heureFin;

	public RestBlocHoraire(int heureDebut, int heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	static RestBlocHoraire from(BlocHoraire blocHoraire) {
		return new RestBlocHoraire(blocHoraire.getHeureDebut(), blocHoraire.getHeureFin());
	}

	public BlocHoraire toDomain() {
		return new BlocHoraire(getHeureDebut(),getHeureFin());
	}

	public int getHeureDebut() {
		return heureDebut;
	}

	public int getHeureFin() {
		return heureFin;
	}
	

}
