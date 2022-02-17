package com.agregio.appagregio.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class ParcTest {

	@Test
	void itShouldConstructAParkWithInputId() {
		//Given
		UUID id = UUID.fromString("15db370e-8eca-11ec-b909-0242ac120002");
		String nom = "Centrale Solaire";
		TypeParc type= TypeParc.PV;
		Double energie = 10.5;
		BlocHoraire blocHoraire = new BlocHoraire(10,16);
		//When
		Parc parc = new Parc(id,nom,type,energie,blocHoraire);
		//Then
		assertThat(parc.getId()).isEqualTo(id);
		assertThat(parc.getNom()).isEqualTo(nom);
		assertThat(parc.getEnergie()).isEqualTo(energie);
		assertThat(parc.getType().getLibelle()).isEqualTo(type.getLibelle());
		assertThat(parc.getBlocHoraire().getHeureDebut()).isEqualTo(blocHoraire.getHeureDebut());
		assertThat(parc.getBlocHoraire().getHeureFin()).isEqualTo(blocHoraire.getHeureFin());
	}
	
	@Test
	void itShouldConstructAParkWithNullId() {
		//Given
		UUID id = null;
		String nom = "Centrale Solaire";
		TypeParc type= TypeParc.PV;
		Double energie = 10.5;
		BlocHoraire blocHoraire = new BlocHoraire(10,16);
		//When
		Parc parc = new Parc(id,nom,type,energie,blocHoraire);
		//Then
		assertThat(parc.getId()).isNotNull();
		assertThat(parc.getNom()).isEqualTo(nom);
		assertThat(parc.getEnergie()).isEqualTo(energie);
		assertThat(parc.getType().getLibelle()).isEqualTo(type.getLibelle());
		assertThat(parc.getBlocHoraire().getHeureDebut()).isEqualTo(blocHoraire.getHeureDebut());
		assertThat(parc.getBlocHoraire().getHeureFin()).isEqualTo(blocHoraire.getHeureFin());
	}
	
	@Test
	void itShouldConstructAParkWithJustAnId() {
		//Given
		UUID id = UUID.fromString("15db370e-8eca-11ec-b909-0242ac120002");
		//When
		Parc parc = new Parc(id);
		//Then
		assertThat(parc.getId()).isEqualTo(id);
	}

}
