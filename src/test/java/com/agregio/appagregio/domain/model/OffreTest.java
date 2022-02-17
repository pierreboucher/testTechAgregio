package com.agregio.appagregio.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class OffreTest {
	
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
	void itShouldConstructAnOffer() throws Exception {
		//When
		Offre offre = new Offre(idOffre, marche, energieOffre, blocHoraireOffre, prixPlancher, parc);
		//Then
		assertEquals(offre.getId(),idOffre);
		assertEquals(offre.getMarche(),marche);
		assertEquals(offre.getMarche().getLibelle(),marche.getLibelle());
		assertEquals(offre.getEnergie(),energieOffre);
		assertEquals(offre.getBlocHoraire(),blocHoraireOffre);
		assertEquals(offre.getPrixPlancher(),prixPlancher);
		assertEquals(offre.getParc(),parc);
	}

}
