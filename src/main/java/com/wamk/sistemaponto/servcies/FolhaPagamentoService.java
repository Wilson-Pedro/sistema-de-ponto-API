package com.wamk.sistemaponto.servcies;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wamk.sistemaponto.exceptions.EntityNotFoundException;
import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FolhaPagamentoRepository;

@Service
public class FolhaPagamentoService {

	@Autowired
	private FolhaPagamentoRepository folhaPagamentoRepository;
	
	@Transactional
	public void novaFolhaPagamento(Funcionario funcionario) {
		var folhaPagamento = new FolhaPagamento(null, funcionario, 0, new BigDecimal(0.0));
		folhaPagamentoRepository.save(folhaPagamento);
	}

	@Transactional(readOnly = true)
	public List<FolhaPagamento> findAll() {
		return  folhaPagamentoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<FolhaPagamento> findAll(Pageable pageable) {
		return folhaPagamentoRepository.findAll(pageable);
	}

	public FolhaPagamento findById(Long id) {
		return folhaPagamentoRepository.findById(id).orElseThrow(() -> 
				new EntityNotFoundException("Folha de Pagamento não encontrada."));
	}

	public void salvarSalario(String intervalo, Long id) {
		var folhaPagamento = findById(id);
		BigDecimal salario = calcularSalario(intervalo, new BigDecimal(200.0));
		Integer horasTrabalhadas = acharHorasTrabalhadas(intervalo);
		folhaPagamento.setHorasTrabalhadas(horasTrabalhadas);
		folhaPagamento.setSalario(salario);
		folhaPagamentoRepository.save(folhaPagamento);
	}

	public Integer acharHorasTrabalhadas(String intervalo) {
		String[] horario = intervalo.split(":");
		Integer horasTrabalhadas = Integer.parseInt(horario[0]);
		return horasTrabalhadas;
	}

	public BigDecimal calcularSalario(String intervalo, BigDecimal valorPorHora) {
		String[] horario = intervalo.split(":");
		BigDecimal horasTrabalhadas = new BigDecimal(horario[0]);
		BigDecimal salario = horasTrabalhadas.multiply(valorPorHora);
		return salario;
	}
}
