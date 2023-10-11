package com.wamk.sistemaponto.enums.converter;

import com.wamk.sistemaponto.enums.TipoRegistro;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoRegistroConverter implements AttributeConverter<TipoRegistro, String>{

	@Override
	public String convertToDatabaseColumn(TipoRegistro tipoRegistro) {
		if(tipoRegistro == null) {
			return null;
		}
		return tipoRegistro.getDescricao();
	}

	@Override
	public TipoRegistro convertToEntityAttribute(String descricao) {
		if(descricao == null) {
			return null;
		}
		return TipoRegistro.toEnum(descricao);
	}

}
