package com.agregio.appagregio.infrastructure.secondary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import com.agregio.appagregio.domain.ParcRepository;
import com.agregio.appagregio.domain.model.Parc;

@Repository
class PostGreSQLParcRepository implements ParcRepository{

	private final ParcSpringRepository parcsSpringRepository;
	
	public PostGreSQLParcRepository(ParcSpringRepository parcs) {
		this.parcsSpringRepository = parcs;
	}
	
	@Override
	public Parc save(Parc parc) {
		return parcsSpringRepository.save(ParcEntity.from(parc)).toDomain();
	}

	@Override
	public Optional<Parc> getById(UUID id) {
		return parcsSpringRepository.findById(id).map(ParcEntity::toDomain);
	}

	@Override
	public List<Parc> getAllByMarche(String marche) {
		MarcheEntity marcheEntity = Stream.of(MarcheEntity.values()).filter(c -> c.toString().equals(marche))
				.findFirst().orElseThrow(IllegalArgumentException::new);
		return parcsSpringRepository.findDistinctByOffresMarche(marcheEntity).stream().map(ParcEntity::toDomain).toList();
	}
	
}
