package com.wamk.sistemaponto.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.model.Funcionario;

public class FolhaPagamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Funcionario funcionario;
	private Integer horasTrabalhadas;
	private BigDecimal salario;
	
	public FolhaPagamentoDTO() {
	}
	
	public FolhaPagamentoDTO(FolhaPagamento folhaPagamento) {
		id = folhaPagamento.getId();
		funcionario = folhaPagamento.getFuncionario();
		horasTrabalhadas = folhaPagamento.getHorasTrabalhadas();
		salario = folhaPagamento.getSalario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
