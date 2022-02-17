package com.agregio.appagregio.infrastructure.secondary;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.agregio.appagregio.domain.model.Offre;

@Entity
@Table(name = "offre")
class OffreEntity {

	@Id
	private UUID id;
	private MarcheEntity marche;
	private Double energie;
	@Embedded
	private BlocHoraireEntity blocHoraire;
	private Double prixPlancher;
	@ManyToOne
	@JoinColumn(name="parc_id", nullable=false)
	private ParcEntity parc;

	public OffreEntity() {
		// JPA
	}

	public OffreEntity(UUID id, MarcheEntity marche, Double energie, BlocHoraireEntity blocHoraire, Double prixPlancher,
			ParcEntity parc) {
		this.id = id;
		this.marche = marche;
		this.energie = energie;
		this.blocHoraire = blocHoraire;
		this.prixPlancher = prixPlancher;
		this.parc = parc;
	}

	public static OffreEntity from(Offre offre) {
		return new OffreEntity(offre.getId(), MarcheEntity.from(offre.getMarche()), offre.getEnergie(),
				BlocHoraireEntity.from(offre.getBlocHoraire()), offre.getPrixPlancher(), new ParcEntity(offre.getParc().getId()));
	}

	public Offre toDomain() {
		return new Offre(getId(), MarcheEntity.toDomain(getMarche()), getEnergie(), getBlocHoraire().toDomain(), getPrixPlancher(),
				getParc().toDomain());
	}

	public UUID getId() {
		return id;
	}

	public MarcheEntity getMarche() {
		return marche;
	}

	public Double getEnergie() {
		return energie;
	}

	public BlocHoraireEntity getBlocHoraire() {
		return blocHoraire;
	}

	public Double getPrixPlancher() {
		return prixPlancher;
	}

	public ParcEntity getParc() {
		return parc;
	}

}
