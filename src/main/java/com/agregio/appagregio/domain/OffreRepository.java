package com.agregio.appagregio.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.agregio.appagregio.domain.model.Offre;

public interface OffreRepository {
	
	void save(Offre offre);
	
	Optional<Offre> getById(UUID id);

	List<Offre> getAll();

	boolean isParcDejaUtilise(UUID id);
}
