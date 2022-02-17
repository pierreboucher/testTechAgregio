package com.agregio.appagregio.infrastructure.primay;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agregio.appagregio.application.ParcApplicationService;
import com.agregio.appagregio.domain.model.Marche;

@RestController
@RequestMapping("/api/parcs")
class ParcsResource {

	private final ParcApplicationService parcService;

	public ParcsResource(ParcApplicationService parcService) {
		this.parcService = parcService;
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody RestParc restParc) throws Exception {
		String id = parcService.create(restParc.toDomain()).getId().toString();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestParc> get(@PathVariable UUID id) throws Exception {
		return ResponseEntity.ok(RestParc.from(parcService.get(id)));
	}
	
	@GetMapping("/offre/{marche}")
	public ResponseEntity<RestParcs> getAllByMarche(@PathVariable Marche marche){
		return ResponseEntity.ok(RestParcs.from(parcService.getAllByMarche(marche.toString())));
	}
}
