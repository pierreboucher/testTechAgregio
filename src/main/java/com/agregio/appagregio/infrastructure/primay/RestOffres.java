package com.agregio.appagregio.infrastructure.primay;

import java.util.List;

import com.agregio.appagregio.domain.model.Offre;

class RestOffres {
	
	private List<RestOffre> offres;

	public RestOffres(List<RestOffre> offres) {
		this.offres = offres;
	}

	public List<RestOffre> getOffres() {
		return offres;
	}

	public static RestOffres from(List<Offre> offres) {
		return new RestOffres(offres.stream().map(RestOffre::from).toList());
	}

}
