package com.wamk.sistemaponto.dtos;

import java.io.Serializable;

import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;

public class RegistroDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String tipoRegistro;
	private String dataHora;
	private String frequencia;
	private Funcionario funcionario;
	
	public RegistroDTO() {
	}
	
	public RegistroDTO(Registro registro) {
		id = registro.getId();
		tipoRegistro = registro.getTipoRegistro().getDescricao();
		dataHora = registro.getDataHora();
		frequencia = registro.getFrequencia().getDescricao();
		funcionario = registro.getFuncionario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
