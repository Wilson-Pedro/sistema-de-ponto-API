package com.wamk.sistemaponto.dtos;

import java.io.Serializable;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.Funcionario;

public class FuncionarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cpf;
	private Integer tipoIdentificacao;
	
	public FuncionarioDTO() {
	}
	
	public FuncionarioDTO(Funcionario funcionario) {
		id = funcionario.getId();
		nome = funcionario.getNome();
		cpf = funcionario.getCpf(); 
		tipoIdentificacao = funcionario.getTipoIdentificacao().getCod();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoIdentificacao getTipoIdentificacao() {
		return TipoIdentificacao.toEnum(tipoIdentificacao);
	}

	public void setTipoIdentificacao(TipoIdentificacao tipoIdentificacao) {
		this.tipoIdentificacao = tipoIdentificacao.getCod();
	}
}
