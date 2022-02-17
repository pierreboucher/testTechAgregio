package com.agregio.appagregio.infrastructure.secondary;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface ParcSpringRepository extends JpaRepository<ParcEntity, UUID> {

	List<ParcEntity>findDistinctByOffresMarche(MarcheEntity marche);
	
}
