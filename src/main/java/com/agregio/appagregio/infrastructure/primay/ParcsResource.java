package com.agregio.appagregio.infrastructure.primay;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agregio.appagregio.application.ParcApplicationService;

@RestController
@RequestMapping("/api/parcs")
class ParcsResource {

	private final ParcApplicationService parcService;

	public ParcsResource(ParcApplicationService parcService) {
		this.parcService = parcService;
	}

	@PostMapping
	public void create(@RequestBody RestParc restParc) throws Exception {
		parcService.create(restParc.toDomain());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestParc> get(@PathVariable UUID id) throws Exception {
		return ResponseEntity.ok(RestParc.from(parcService.get(id)));
	}
	
	@GetMapping("/offre/{marche}")
	public ResponseEntity<RestParcs> getAllByMarche(@PathVariable String marche){
		return ResponseEntity.ok(RestParcs.from(parcService.getAllByMarche(marche)));
	}
}
