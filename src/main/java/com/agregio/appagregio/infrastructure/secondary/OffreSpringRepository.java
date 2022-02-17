package com.agregio.appagregio.infrastructure.secondary;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface OffreSpringRepository extends JpaRepository<OffreEntity, UUID> {
	
	Integer countByParcId(UUID id);
}
