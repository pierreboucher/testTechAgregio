package com.agregio.appagregio.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.agregio.appagregio.domain.OffreRepository;
import com.agregio.appagregio.domain.ParcRepository;
import com.agregio.appagregio.domain.model.Offre;
import com.agregio.appagregio.domain.model.Parc;

@Service
public class OffreApplicationService {

	private final OffreRepository offreRepository;
	private final ParcRepository parcRepository;

	public OffreApplicationService(OffreRepository offreRepository, ParcRepository parcRepository) {
		this.offreRepository = offreRepository;
		this.parcRepository = parcRepository;
	}
	
	public Offre get(UUID id) throws Exception {
		return offreRepository.getById(id).orElseThrow(() -> new Exception("L'offre n'existe pas"));
	}

	// LISTER LES OFFRES
	public List<Offre> getAll() {
		return offreRepository.getAll();
	}

	// CREER UNE OFFRE
	public void create(Offre offre) throws Exception {
		assertOffre(offre);
		assertParc(offre);
		offreRepository.save(offre);
	}

	// Vérification Regles Metiers du Parc
	private void assertParc(Offre offre) throws Exception {
		Parc parc = parcRepository.getById(offre.getParc().getId()).orElseThrow(() -> new Exception("Le parc est inconnu"));
		assertEnergieParc(parc,offre);
		assertHoraireParc(parc,offre);
		assertDisponibiliteParc(parc);
	}

	// Vérification Regles Metiers de l'Offre
	private void assertOffre(Offre offre) throws Exception {
		assertEnergie(offre);
		assertBlocHoraire(offre);
		assertPrixPlancher(offre);		
	}

	// Le parc n'est pas déjà utilisé dans une offre
	private void assertDisponibiliteParc(Parc parc) throws Exception {
		if (offreRepository.isParcDejaUtilise(parc.getId())) {
			throw new Exception("Le parc est déjà utilisé dans une offre");
		}
	}
	
	// Le parc peut fournir de l'énergie sur un horaire au moins égal à celui de l'offre
	private void assertHoraireParc(Parc parc, Offre offre) throws Exception {
		boolean horaireParcSuffisant = parc.getBlocHoraire().getHeureDebut() <= offre.getBlocHoraire().getHeureDebut()
				&& parc.getBlocHoraire().getHeureFin() >= offre.getBlocHoraire().getHeureFin();
		if (!horaireParcSuffisant) {
			throw new Exception("Le parc n'est pas capable de fournir d'energie durant le bloc horaire annoncé dans l'offre");
		}
	}

	// Le parc peut fournir une quantité d'énergie au moins égale à celle de l'offre
	private void assertEnergieParc(Parc parc, Offre offre) throws Exception {
		boolean energieParcSuffisante = parc.getEnergie() >= offre.getEnergie();
		if(!energieParcSuffisante) {
			throw new Exception("Le parc n'est pas capable de fournir l'energie annoncée dans l'offre");
		}
	}

	// Le prix plancher de l'offre est >0
	private void assertPrixPlancher(Offre offre) throws Exception {
		boolean prixPlancherSup0 = offre.getPrixPlancher() > 0;
		if(!prixPlancherSup0) {
			throw new Exception("Le prix plancher doit être supérieur à 0");
		}
		
	}

	// Le bloc horaire de l'offre est bien défini
	private void assertBlocHoraire(Offre offre) throws Exception {
		boolean blocHoraireCoherent = 0 <= offre.getBlocHoraire().getHeureDebut() && offre.getBlocHoraire().getHeureDebut() < offre.getBlocHoraire().getHeureFin()
				&& offre.getBlocHoraire().getHeureFin() <= 24;
		if(!blocHoraireCoherent) {
			throw new Exception("Le bloc horaire de l'offre doit etre inclus dans la tranche horaire [0;24]");
		}
	}

	// L'energie de l'offre répond au critère RTE d'être >1MW
	private void assertEnergie(Offre offre) throws Exception {
		boolean energieSup1MW = offre.getEnergie() >= 1;
		if(!energieSup1MW) {
			throw new Exception("L'energie minimale proposable dans une offre est de 1 MW");
		}
		
	}

}
