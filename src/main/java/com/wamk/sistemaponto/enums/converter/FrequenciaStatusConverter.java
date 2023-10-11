package com.wamk.sistemaponto.enums.converter;

import com.wamk.sistemaponto.enums.FrequenciaStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FrequenciaStatusConverter implements AttributeConverter<FrequenciaStatus, String>{

	@Override
	public String convertToDatabaseColumn(FrequenciaStatus frequenciaStatus) {
		if(frequenciaStatus == null) {
			return null;
		}
		return frequenciaStatus.getDescricao();
	}

	@Override
	public FrequenciaStatus convertToEntityAttribute(String descricao) {
		if(descricao == null) {
			return null;
		}
		return FrequenciaStatus.toEnum(descricao);
	}

}
