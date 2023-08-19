package com.wamk.sistemaponto.dtos.inputs;

import java.io.Serializable;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.Funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FuncionarioInputDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "nome é obrigatório!")
	private String nome;
	
	@NotNull(message = "tipoIdentificacao não pode ser nulo!")
	private TipoIdentificacao tipoIdentificacao;
	
	public FuncionarioInputDTO() {
	}

	public FuncionarioInputDTO(String nome, TipoIdentificacao tipoIdentificacao) {
		this.nome = nome;
		this.tipoIdentificacao = (tipoIdentificacao == null) ? null : tipoIdentificacao;
	}
	
	public FuncionarioInputDTO(Funcionario funcionario) {
		nome = funcionario.getNome();
		tipoIdentificacao = funcionario.getTipoIdentificacao();
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoIdentificacao getTipoIdentificacao() {
		return tipoIdentificacao;
	}

	public void setTipoIdentificacao(TipoIdentificacao tipoIdentificacao) {
		this.tipoIdentificacao = tipoIdentificacao;
	}
}
