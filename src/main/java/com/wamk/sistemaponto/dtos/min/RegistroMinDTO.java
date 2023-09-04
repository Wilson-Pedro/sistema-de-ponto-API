package com.wamk.sistemaponto.dtos.min;

import java.io.Serializable;

import com.wamk.sistemaponto.enums.FrequenciaStatus;
import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.projections.RegistroMinProjection;

public class RegistroMinDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer tipoRegistro;
	private String dataHora;
	private Integer frequencia;
	private String nomeFuncionario;
	
	public RegistroMinDTO() {
	}
	
	public RegistroMinDTO(Registro registro) {
		id = registro.getId();
		tipoRegistro = registro.getTipoRegistro().getCod();
		dataHora = registro.getDataHora();
		frequencia = registro.getFrequencia().getCod();
		nomeFuncionario = registro.getFuncionario().getNome();
	}
	
	public RegistroMinDTO(RegistroMinProjection projection) {
		id = projection.getId();
		tipoRegistro = projection.getTipoRegsitro();
		dataHora = projection.getDataHora();
		frequencia = projection.getFrequencia();
		nomeFuncionario = projection.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoRegistro getTipoRegistro() {
		return TipoRegistro.toEnum(frequencia);
	}

	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro.getCod();
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public FrequenciaStatus getFrequencia() {
		return FrequenciaStatus.toEnum(frequencia);
	}

	public void setFrequencia(FrequenciaStatus frequencia) {
		this.frequencia = frequencia.getCod();
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
}
