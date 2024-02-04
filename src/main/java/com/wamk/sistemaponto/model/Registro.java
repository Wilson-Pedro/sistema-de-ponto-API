package com.wamk.sistemaponto.model;

import java.io.Serializable;
import java.util.Objects;

import com.wamk.sistemaponto.enums.FrequenciaStatus;
import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.projections.RegistroMinProjection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_REGISTRO")
@Inheritance(strategy = InheritanceType.JOINED)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Registro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private TipoRegistro tipoRegistro;
	
	private String dataHora;
	
	private FrequenciaStatus frequencia;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	public Registro() {
	}

	public Registro(Long id, TipoRegistro tipoRegistro, String dataHora, FrequenciaStatus frequencia, Funcionario funcionario) {
		this.id = id;
		this.tipoRegistro = tipoRegistro;
		this.dataHora = dataHora;
		this.frequencia = frequencia;
		this.funcionario = funcionario;
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

	public void setDataHora(String string) {
		this.dataHora = string;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		return Objects.equals(id, other.id);
	}
}
