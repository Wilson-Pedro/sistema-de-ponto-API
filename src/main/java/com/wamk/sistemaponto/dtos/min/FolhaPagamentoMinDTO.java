package com.wamk.sistemaponto.dtos.min;

import java.io.Serializable;
import java.math.BigDecimal;

import com.wamk.sistemaponto.model.FolhaPagamento;

public class FolhaPagamentoMinDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomeFuncionario;
	private Integer horasTrabalhadas;
	private BigDecimal salario;
	
	public FolhaPagamentoMinDTO() {
	}
	
	public FolhaPagamentoMinDTO(FolhaPagamento folhaPagamento) {
		id = folhaPagamento.getId();
		nomeFuncionario = folhaPagamento.getFuncionario().getNome();
		horasTrabalhadas = folhaPagamento.getHorasTrabalhadas();
		salario = folhaPagamento.getSalario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Integer getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(Integer horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
}
