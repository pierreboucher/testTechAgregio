package com.agregio.appagregio.infrastructure.secondary;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.agregio.appagregio.domain.model.Parc;
import com.agregio.appagregio.domain.model.TypeParc;

@Entity
@Table(name = "parc")
class ParcEntity {

	@Id
	private UUID id;
	private String nom;
	private TypeParcEntity type;
	private Double energie;
	@Embedded
	private BlocHoraireEntity blocHoraire;
	@OneToMany(mappedBy="parc")
	private Set<OffreEntity> offres;
	
	
	public ParcEntity () {
		// JPA
	}

	public ParcEntity(UUID id, String nom, TypeParcEntity type, Double energie, BlocHoraireEntity blocHoraire) {
		this.id = id;
		this.nom = nom;
		this.type = type;
		this.energie = energie;
		this.blocHoraire = blocHoraire;
	}

	public ParcEntity(UUID id) {
		this.id = id;
	}

	public static ParcEntity from(Parc parc) {
		TypeParcEntity typeParc = 
				Stream.of(TypeParcEntity.values())
		        .filter(c -> c.getLibelle().equals(parc.getType().getLibelle()))
		        .findFirst()
		        .orElseThrow(IllegalArgumentException::new);
		
		return new ParcEntity(parc.getId(), parc.getNom(), typeParc, parc.getEnergie(), BlocHoraireEntity.from(parc.getBlocHoraire()));
	}
	
	public Parc toDomain() {
		TypeParc typeParc = 
				Stream.of(TypeParc.values())
		        .filter(c -> c.getLibelle().equals(getType().getLibelle()))
		        .findFirst()
		        .orElseThrow(IllegalArgumentException::new);
		
		return new Parc(getId(), getNom(), typeParc, getEnergie(), getBlocHoraire().toDomain());
	}

	public UUID getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public TypeParcEntity getType() {
		return type;
	}

	public Double getEnergie() {
		return energie;
	}

	public BlocHoraireEntity getBlocHoraire() {
		return blocHoraire;
	}

}
