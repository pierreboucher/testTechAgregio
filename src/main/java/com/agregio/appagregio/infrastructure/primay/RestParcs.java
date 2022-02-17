package com.agregio.appagregio.infrastructure.primay;

import java.util.List;

import com.agregio.appagregio.domain.model.Parc;

class RestParcs {
	
	private List<RestParc> parcs;

	public RestParcs(List<RestParc> parcs) {
		this.parcs = parcs;
	}

	public static RestParcs from(List<Parc> parcs) {
		return new RestParcs(parcs.stream().map(RestParc::from).toList());
	}

	public List<RestParc> getParcs() {
		return parcs;
	}

	
}
