package com.wamk.sistemaponto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.enums.TipoRegistro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_FUNCIONARIO")
public class Funcionario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer tipoIdentificacao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "funcionario")
	private List<Registro> registros = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "funcionario")
	private FolhaPagamento folhaPagamento;
	
	public Funcionario() {
	}

	public Funcionario(Long id, String nome, TipoIdentificacao tipoIdentificacao) {
		this.id = id;
		this.nome = nome;
		this.tipoIdentificacao = (tipoIdentificacao == null) ? null : tipoIdentificacao.getCod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoIdentificacao getTipoIdentificacao() {
		return TipoIdentificacao.toEnum(tipoIdentificacao);
	}

	public void setTipoIdentificacao(TipoIdentificacao tipoIdentificacao) {
		this.tipoIdentificacao = tipoIdentificacao.getCod();
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public int validarSaida() {
		int sum = 0;
		for(Registro x : registros) {
			if(x.getTipoRegistro().equals(TipoRegistro.ENTRADA)) {
				sum += 1;
			}
		}
		return sum;
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
		Funcionario other = (Funcionario) obj;
		return Objects.equals(id, other.id);
	}
}
