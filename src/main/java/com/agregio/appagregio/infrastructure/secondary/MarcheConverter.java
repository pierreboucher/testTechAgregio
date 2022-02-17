package com.agregio.appagregio.infrastructure.secondary;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
class MarcheConverter implements AttributeConverter<MarcheEntity, String> {

	@Override
	public String convertToDatabaseColumn(MarcheEntity marche) {
		if (marche == null) {
			return null;
		}
		return marche.getLibelle();
	}

	@Override
	public MarcheEntity convertToEntityAttribute(String libelle) {
		if (libelle == null) {
			return null;
		}

		return Stream.of(MarcheEntity.values()).filter(c -> c.getLibelle().equals(libelle)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
