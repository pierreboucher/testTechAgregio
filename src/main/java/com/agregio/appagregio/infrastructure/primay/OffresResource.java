package com.agregio.appagregio.infrastructure.primay;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agregio.appagregio.application.OffreApplicationService;

@RestController
@RequestMapping("/api/offres")
class OffresResource {

	private final OffreApplicationService offreService;

	public OffresResource(OffreApplicationService offreService) {
		this.offreService = offreService;
	}

	@PostMapping
	public void create(@RequestBody RestOffre restOffre) throws Exception {
		offreService.create(restOffre.toDomain());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestOffre> get(@PathVariable UUID id) throws Exception {
		return ResponseEntity.ok(RestOffre.from(offreService.get(id)));
	}
	
	@GetMapping()
	public ResponseEntity<RestOffres> getAll(){
		return ResponseEntity.ok(RestOffres.from(offreService.getAll()));
	}
	
}
