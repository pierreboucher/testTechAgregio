package com.agregio.appagregio.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.agregio.appagregio.domain.model.Parc;

public interface ParcRepository {
	
	Parc save(Parc parc);

	Optional<Parc> getById(UUID id);

	List<Parc> getAllByMarche(String marche);
}
