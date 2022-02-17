package com.agregio.appagregio.infrastructure.secondary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import com.agregio.appagregio.domain.OffreRepository;
import com.agregio.appagregio.domain.model.Offre;

@Repository
class PostGreSQLOffreRepository implements OffreRepository{
	
	private final OffreSpringRepository offreSpringRepository;

	public PostGreSQLOffreRepository(OffreSpringRepository offreSpringRepository) {
		this.offreSpringRepository = offreSpringRepository;
	}

	@Override
	public Offre save(Offre offre) {
		return offreSpringRepository.save(OffreEntity.from(offre)).toDomain();	
	}

	@Override
	public Optional<Offre> getById(UUID id) {
		return offreSpringRepository.findById(id).map(OffreEntity::toDomain);
	}

	@Override
	public List<Offre> getAll() {
		return offreSpringRepository.findAll().stream().map(OffreEntity::toDomain).toList();
	}

	@Override
	public boolean isParcDejaUtilise(UUID id) {
		return offreSpringRepository.countByParcId(id) > 0;
	}

}
