package com.wamk.sistemaponto.servcies;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.model.Funcionario;

public interface FolhaPagamentoService {
	
	void novaFolhaPagamento(Funcionario funcionario);

	List<FolhaPagamento> findAll();
	
	Page<FolhaPagamento> findAll(Pageable pageable);

	FolhaPagamento findById(Long id);

	void salvarSalario(String intervalo);

	Integer acharHorasTrabalhadas(String intervalo);

	BigDecimal calcularSalario(String intervalo, BigDecimal valorPorHora);
}
