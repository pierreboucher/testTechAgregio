package com.agregio.appagregio.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agregio.appagregio.domain.ParcRepository;
import com.agregio.appagregio.domain.model.Parc;

@Service
public class ParcApplicationService {

	private final ParcRepository parcRepository;

	public ParcApplicationService(ParcRepository parcRepository) {
		this.parcRepository = parcRepository;
	}

	public Parc get(UUID id) throws Exception {
		return parcRepository.getById(id).orElseThrow(() -> new Exception("Le parc n'existe pas"));
	}

	// OBTENIR LA LISTE DES PARCS QUI VENDENT SUR UN MARCHE
	public List<Parc> getAllByMarche(String marche) {
		return parcRepository.getAllByMarche(marche);
	}

	// CREER UN PARC
	public Parc create(Parc parc) throws Exception {
		assertParc(parc);
		return parcRepository.save(parc);
	}

	// Verifications Regles Metier du Parc
	private void assertParc(Parc parc) throws Exception {
		assertEnergie(parc);
		assertBlocHoraire(parc);
	}

	// Le bloc horaire de l'offre est bien défini
	private void assertBlocHoraire(Parc parc) throws Exception {
		boolean blocHoraireCoherent = parc.getBlocHoraire().getHeureDebut() > 0
				&& parc.getBlocHoraire().getHeureDebut() < parc.getBlocHoraire().getHeureFin()
				&& parc.getBlocHoraire().getHeureFin() <= 24;
		if (!blocHoraireCoherent) {
			throw new Exception("Le bloc horaire du parc doit etre inclus dans la tranche horaire [0;24]");
		}
	}

	// Le parc fournit une quantité d'énergie > 0
	private void assertEnergie(Parc parc) throws Exception {
		if (parc.getEnergie() <= 0) {
			throw new Exception("L'energie du parc doit être positive");
		}
	}

}
