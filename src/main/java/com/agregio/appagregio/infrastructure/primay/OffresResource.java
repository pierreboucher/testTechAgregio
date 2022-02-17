package com.agregio.appagregio.infrastructure.primay;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agregio.appagregio.application.OffreApplicationService;

@RestController
@RequestMapping("/api/offres")
class OffresResource {

	private final OffreApplicationService offreService;

	public OffresResource(OffreApplicationService offreService) {
		this.offreService = offreService;
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RestOffre restOffre) throws Exception {
		String id = offreService.create(restOffre.toDomain()).getId().toString();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
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
