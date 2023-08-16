package com.wamk.sistemaponto.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.wamk.sistemaponto.enums.FrequenciaStatus;
import com.wamk.sistemaponto.enums.TipoRegistro;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_REGISTRO_SAIDA")
@JsonTypeName("registroSaida")
public class RegistroSaida extends Registro{ 
	private static final long serialVersionUID = 1L;
	
	private String intervalo;

	public RegistroSaida() {
		super();
	}

	public RegistroSaida(Long id, TipoRegistro tipoRegistro, String dataHora, FrequenciaStatus frequencia,
			Funcionario funcionario) {
		super(id, tipoRegistro, dataHora, frequencia, funcionario);
	}
	
	public RegistroSaida(Registro registro) {
		super(registro.getId(), registro.getTipoRegistro(), registro.getDataHora(), registro.getFrequencia(), registro.getFuncionario());
	}

	public String getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}
}
