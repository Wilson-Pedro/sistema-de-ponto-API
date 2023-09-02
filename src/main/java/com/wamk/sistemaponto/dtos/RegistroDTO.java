package com.wamk.sistemaponto.dtos;

import java.io.Serializable;

import com.wamk.sistemaponto.enums.FrequenciaStatus;
import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;

public class RegistroDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private TipoRegistro tipoRegistro;
	private String dataHora;
	private FrequenciaStatus frequencia;
	private Funcionario funcionario;
	
	public RegistroDTO() {
	}
	
	public RegistroDTO(Registro registro) {
		id = registro.getId();
		tipoRegistro = registro.getTipoRegistro();
		dataHora = registro.getDataHora();
		frequencia = registro.getFrequencia();
		funcionario = registro.getFuncionario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public FrequenciaStatus getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(FrequenciaStatus frequencia) {
		this.frequencia = frequencia;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
