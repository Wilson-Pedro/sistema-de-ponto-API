package com.wamk.sistemaponto.model;

import java.io.Serializable;
import java.util.Objects;

import com.wamk.sistemaponto.enums.TipoRegistro;

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
	private Integer tipoRegistro;
	private String dataHora;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	public Registro() {
	}

	public Registro(Long id, TipoRegistro tipoRegistro, String dataHora, Funcionario funcionario) {
		this.id = id;
		this.tipoRegistro = (tipoRegistro == null) ? null : tipoRegistro.getCod();
		this.dataHora = dataHora;
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoRegistro getTipoRegistro() {
		return TipoRegistro.toEnum(tipoRegistro);
	}

	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro.getCod();
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String string) {
		this.dataHora = string;
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
