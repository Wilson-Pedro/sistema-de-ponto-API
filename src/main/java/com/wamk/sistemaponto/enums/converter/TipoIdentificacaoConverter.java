package com.wamk.sistemaponto.enums.converter;

import com.wamk.sistemaponto.enums.TipoIdentificacao;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoIdentificacaoConverter implements AttributeConverter<TipoIdentificacao, String>{

	@Override
	public String convertToDatabaseColumn(TipoIdentificacao tipoIdentificacao) {
		if(tipoIdentificacao == null) {
			return null;
		}
		return tipoIdentificacao.getDescricao();
	}

	@Override
	public TipoIdentificacao convertToEntityAttribute(String descricao) {
		if(descricao == null) {
			return null;
		}
		return TipoIdentificacao.toEnum(descricao);
	}

}
