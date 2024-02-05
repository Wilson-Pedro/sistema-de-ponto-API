package com.wamk.sistemaponto.servcies;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wamk.sistemaponto.exceptions.EntidadeNaoEncontradaException;
import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FolhaPagamentoRepository;

@Service
public class FolhaPagamentoService {

	@Autowired
	private FolhaPagamentoRepository folhaPagamentoRepository;
	
	@Transactional
	private void save(FolhaPagamento folhaPagamento) {
		folhaPagamentoRepository.save(folhaPagamento);
	}
	
	@Transactional
	public void novaFolhaPagamento(Funcionario funcionario) {
		var folhaPagemnto = new FolhaPagamento(null, funcionario, 0, new BigDecimal(0.0));
		save(folhaPagemnto);
	}

	@Transactional(readOnly = true)
	public List<FolhaPagamento> findAll() {
		return  folhaPagamentoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<FolhaPagamento> findAll(Pageable pageable) {
		return folhaPagamentoRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public FolhaPagamento findById(Long id) {
		return folhaPagamentoRepository.findById(id).orElseThrow(() -> 
				new EntidadeNaoEncontradaException("Folha de Pagamento não encontrada."));
	}

	public void salvarSalario(String intervalo, Long id) {
		var folhaPagamento = findById(id);
		BigDecimal salario = calcularSalario(intervalo, new BigDecimal(200.0));
		Integer horasTrabalhadas = acharHorasTrabalhadas(intervalo);
		folhaPagamento.setHorasTrabalhadas(horasTrabalhadas);
		folhaPagamento.setSalario(salario);
		save(folhaPagamento);
	}

	private Integer acharHorasTrabalhadas(String intervalo) {
		String[] horario = intervalo.split(":");
		Integer horasTrabalhadas = Integer.parseInt(horario[2]);
		return horasTrabalhadas;
	}

	private BigDecimal calcularSalario(String intervalo, BigDecimal valorPorHora) {
		String[] horario = intervalo.split(":");
		BigDecimal horasTrabalhadas = new BigDecimal(horario[2]);
		BigDecimal salario = horasTrabalhadas.multiply(valorPorHora);
		return salario;
	}
}
