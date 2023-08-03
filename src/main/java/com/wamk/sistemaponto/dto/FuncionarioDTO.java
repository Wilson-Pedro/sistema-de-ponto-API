package com.wamk.sistemaponto.dto;

import java.io.Serializable;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.Funcionario;

public class FuncionarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Integer tipoIdentificacao;
	
	public FuncionarioDTO() {
	}

	public FuncionarioDTO(String nome, TipoIdentificacao tipoIdentificacao) {
		this.nome = nome;
		this.tipoIdentificacao = (tipoIdentificacao == null) ? null : tipoIdentificacao.getCod();
	}
	
	public FuncionarioDTO(Funcionario funcionario) {
		nome = funcionario.getNome();
		tipoIdentificacao = funcionario.getTipoIdentificacao().getCod();
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
}
