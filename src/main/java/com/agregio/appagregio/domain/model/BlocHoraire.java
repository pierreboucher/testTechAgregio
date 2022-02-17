package com.agregio.appagregio.domain.model;

public class BlocHoraire {
	private int heureDebut;
	private int heureFin;
	
	public BlocHoraire(int heureDebut, int heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
		
	public int getHeureDebut() {
		return heureDebut;
	}
	
	public int getHeureFin() {
		return heureFin;
	}
	
	
}
