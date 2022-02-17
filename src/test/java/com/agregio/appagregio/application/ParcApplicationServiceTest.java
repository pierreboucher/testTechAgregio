package com.agregio.appagregio.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agregio.appagregio.domain.ParcRepository;
import com.agregio.appagregio.domain.model.BlocHoraire;
import com.agregio.appagregio.domain.model.Parc;
import com.agregio.appagregio.domain.model.TypeParc;

@ExtendWith(MockitoExtension.class)
class ParcApplicationServiceTest {

	@Mock
	private ParcRepository parcRepositoryMock;
	@InjectMocks
	private ParcApplicationService parcApplicationService;

	UUID id = UUID.fromString("15db370e-8eca-11ec-b909-0242ac120002");
	String nom = "Centrale Solaire";
	TypeParc type = TypeParc.PV;
	Double energie = 10.5;
	BlocHoraire blocHoraire = new BlocHoraire(10, 16);

	@Test
	void itShouldCreateAParkWithCorrectInput() throws Exception {
		// Given
		Parc parc = new Parc(id, nom, type, energie, blocHoraire);
		Mockito.when(parcRepositoryMock.save(parc)).thenReturn(parc);
		// Then
		assertDoesNotThrow(() -> parcApplicationService.create(parc));
		assertEquals(parcApplicationService.create(parc),parc);
	}

	@Test
	void itShouldNotCreateAParkIfEnergyIsNotStrictlyPositive() throws Exception {
		// Given
		Parc parc = new Parc(id, nom, type, 0.0, blocHoraire);
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			parcApplicationService.create(parc);
		});
		assertEquals("L'energie du parc doit être positive", exception.getMessage());
	}

	@Test
	void itShouldNotCreateAParkIfBlocHoraireIsNotConsistent() throws Exception {
		// Given
		BlocHoraire inconsistentBlocHoraire = new BlocHoraire(28, 27);
		BlocHoraire inconsistentBlocHoraire2 = new BlocHoraire(-1, 0);
		Parc parc = new Parc(id, nom, type, energie, inconsistentBlocHoraire);
		Parc parc2 = new Parc(id, nom, type, energie, inconsistentBlocHoraire2);
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			parcApplicationService.create(parc);
		});
		assertEquals("Le bloc horaire du parc doit etre inclus dans la tranche horaire [0;24]", exception.getMessage());

		Exception exception2 = assertThrows(Exception.class, () -> {
			parcApplicationService.create(parc2);
		});
		assertEquals("Le bloc horaire du parc doit etre inclus dans la tranche horaire [0;24]",
				exception2.getMessage());
	}

	@Test
	void itShouldThrowExceptionIfAParkDoesNotExistInRepository() throws Exception {
		// Given
		Mockito.when(parcRepositoryMock.getById(any(UUID.class))).thenReturn(Optional.empty());
		// Then
		Exception exception = assertThrows(Exception.class, () -> {
			parcApplicationService.get(id);
		});
		assertEquals("Le parc n'existe pas", exception.getMessage());
	}

}
