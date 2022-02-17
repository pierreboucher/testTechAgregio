package com.agregio.appagregio.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agregio.appagregio.domain.OffreRepository;
import com.agregio.appagregio.domain.ParcRepository;
import com.agregio.appagregio.domain.model.BlocHoraire;
import com.agregio.appagregio.domain.model.Marche;
import com.agregio.appagregio.domain.model.Offre;
import com.agregio.appagregio.domain.model.Parc;
import com.agregio.appagregio.domain.model.TypeParc;


@ExtendWith(MockitoExtension.class)
class OffreApplicationServiceTest {
	
	@Mock
	private ParcRepository parcRepositoryMock;
	@Mock
	private OffreRepository offreRepositoryMock;
	@InjectMocks
	private OffreApplicationService offreApplicationService;

	UUID idParc = UUID.fromString("15db370e-8eca-11ec-b909-0242ac120002");
	String nom = "Centrale Solaire";
	TypeParc type = TypeParc.PV;
	Double energieParc = 10.5;
	BlocHoraire blocHoraireParc = new BlocHoraire(10, 16);
	Parc parc = new Parc(idParc, nom, type, energieParc, blocHoraireParc);
	
	UUID idOffre = UUID.fromString("15db370e-8eca-11ec-b909-0242ac120003");
	Marche marche = Marche.PRIMAIRE;
	Double energieOffre = 9.8;
	BlocHoraire blocHoraireOffre = new BlocHoraire(11, 15);
	Double prixPlancher = 110.6;
	
	@Test
	void itShouldCreateAnOfferWithAllConsistentInput() throws Exception {
		//Given
		Offre offre = new Offre(idOffre, marche, energieOffre, blocHoraireOffre, prixPlancher, parc);
		Mockito.when(parcRepositoryMock.getById(idParc)).thenReturn(Optional.of(parc));
		Mockito.when(offreRepositoryMock.isParcDejaUtilise(idParc)).thenReturn(false);		
		//Then
		assertDoesNotThrow(() -> offreApplicationService.create(offre));
	}
	
	@Test
	void itShouldCreateAnOfferWithAllConsistentInputAndNullId() throws Exception {
		//Given
		Mockito.when(parcRepositoryMock.getById(idParc)).thenReturn(Optional.of(parc));
		Mockito.when(offreRepositoryMock.isParcDejaUtilise(idParc)).thenReturn(false);		
		//When
		Offre offre = new Offre(null, marche, energieOffre, blocHoraireOffre, prixPlancher, parc);
		//Then
		assertThat(offre.getId()).isNotNull();
		assertDoesNotThrow(() -> offreApplicationService.create(offre));
	}
	
	@Test
	void itShouldNotCreateAnOfferIfParkIsAlreadyUsed() throws Exception {
		//Given
		Offre offre = new Offre(idOffre, marche, energieOffre, blocHoraireOffre, prixPlancher, parc);
		Mockito.when(parcRepositoryMock.getById(idParc)).thenReturn(Optional.of(parc));
		Mockito.when(offreRepositoryMock.isParcDejaUtilise(idParc)).thenReturn(true);		
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			offreApplicationService.create(offre);
		});
		assertEquals("Le parc est déjà utilisé dans une offre", exception.getMessage());
	}
	
	@Test
	void itShouldNotCreateAnOfferIfParkDoesNotExist() throws Exception {
		//Given
		Offre offre = new Offre(idOffre, marche, energieOffre, blocHoraireOffre, prixPlancher, parc);
		Mockito.when(parcRepositoryMock.getById(idParc)).thenReturn(Optional.empty());	
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			offreApplicationService.create(offre);
		});
		assertEquals("Le parc est inconnu", exception.getMessage());
	}
	
	@Test
	void itShouldNotCreateAnOfferIfParkHoraireDoesNotFullyCoverOffreHoraire() throws Exception {
		//Given
		BlocHoraire blocHoraire8h20h = new BlocHoraire(8,20);
		Offre offre = new Offre(idOffre, marche, energieOffre, blocHoraire8h20h, prixPlancher, parc);
		Mockito.when(parcRepositoryMock.getById(idParc)).thenReturn(Optional.of(parc));
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			offreApplicationService.create(offre);
		});
		assertEquals("Le parc n'est pas capable de fournir d'energie durant le bloc horaire annoncé dans l'offre", exception.getMessage());
	}	
	
	@Test
	void itShouldNotCreateAnOfferIfParkEnergyIsInsufficient() throws Exception {
		//Given
		Double energieOffre2 = 200.;
		Offre offre = new Offre(idOffre, marche, energieOffre2, blocHoraireOffre, prixPlancher, parc);
		Mockito.when(parcRepositoryMock.getById(idParc)).thenReturn(Optional.of(parc));
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			offreApplicationService.create(offre);
		});
		assertEquals("Le parc n'est pas capable de fournir l'energie annoncée dans l'offre", exception.getMessage());
	}	
	
	@Test
	void itShouldNotCreateAnOfferIfPrixPlancherIsNotStrictlyPositivet() throws Exception {
		//Given
		Double prixPlancher2 = 0.;
		Offre offre = new Offre(idOffre, marche, energieOffre, blocHoraireOffre, prixPlancher2, parc);
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			offreApplicationService.create(offre);
		});
		assertEquals("Le prix plancher doit être supérieur à 0", exception.getMessage());
	}
	
	@Test
	void itShouldNotCreateAnOfferIfBlocHoraireIsInconsistent() throws Exception {
		//Given
		BlocHoraire inconsistentBlocHoraire = new BlocHoraire(28, 27);
		Offre offre = new Offre(idOffre, marche, energieOffre, inconsistentBlocHoraire, prixPlancher, parc);
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			offreApplicationService.create(offre);
		});
		assertEquals("Le bloc horaire de l'offre doit etre inclus dans la tranche horaire [0;24]", exception.getMessage());
	}
	
	@Test
	void itShouldNotCreateAnOfferIfEnergyIsInferiorTo1MW() throws Exception {
		//Given
		Double energieOffre2 = 0.5;
		Offre offre = new Offre(idOffre, marche, energieOffre2, blocHoraireOffre, prixPlancher, parc);
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			offreApplicationService.create(offre);
		});
		assertEquals("L'energie minimale proposable dans une offre est de 1 MW", exception.getMessage());
	}
	
	

}
