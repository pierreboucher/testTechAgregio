package com.agregio.appagregio.infrastructure.secondary;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
class TypeParcConverter implements AttributeConverter<TypeParcEntity, String>{

	@Override
	public String convertToDatabaseColumn(TypeParcEntity type) {
		if (type == null) {
			return null;
		}
		return type.getLibelle();
	}

	@Override
	public TypeParcEntity convertToEntityAttribute(String libelle) {
        if (libelle == null) {
            return null;
        }

        return Stream.of(TypeParcEntity.values())
          .filter(c -> c.getLibelle().equals(libelle))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
	}

}
