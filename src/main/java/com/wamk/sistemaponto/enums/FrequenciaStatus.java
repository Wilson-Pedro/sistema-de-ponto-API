package com.wamk.sistemaponto.enums;

import java.util.stream.Stream;

public enum FrequenciaStatus {
	
	PONTO(0, "Ponto"),
	ATRASADO(1, "Atrasado"),
	HORA_EXTRA(2, "Hora extra"),
	FALTA(3, "Falta"),
	PROCESSANDO(4, "Processando");
	
	private Integer cod;
	private String descricao;
	
	private FrequenciaStatus(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static FrequenciaStatus toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(FrequenciaStatus x : FrequenciaStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
	public static FrequenciaStatus toEnum(String descricao) {
		return Stream.of(FrequenciaStatus.values())
				.filter(frequencia -> frequencia.getDescricao().equals(descricao))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
