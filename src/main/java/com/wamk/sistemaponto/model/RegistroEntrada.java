package com.wamk.sistemaponto.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.wamk.sistemaponto.enums.FrequenciaStatus;
import com.wamk.sistemaponto.enums.TipoRegistro;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_REGISTRO_ENTRADA")
@JsonTypeName("registroEntrada")
public class RegistroEntrada extends Registro{ 
	private static final long serialVersionUID = 1L;

	public RegistroEntrada() {
		super();
	}

	public RegistroEntrada(Long id, TipoRegistro tipoRegistro, String dataHora, FrequenciaStatus frequencia,
			Funcionario funcionario) {
		super(id, tipoRegistro, dataHora, frequencia, funcionario);
	}
	
	public RegistroEntrada(Registro registro) {
		super(registro.getId(), registro.getTipoRegistro(), registro.getDataHora(), registro.getFrequencia(), registro.getFuncionario());
	}
}
