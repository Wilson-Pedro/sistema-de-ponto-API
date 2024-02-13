package com.wamk.sistemaponto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FolhaPagamentoRepository;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;
import com.wamk.sistemaponto.servcies.FolhaPagamentoService;

import jakarta.transaction.Transactional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FolhaPagamentoServiceTest {
	
	@Autowired
	FolhaPagamentoRepository folhaPagamentoRepository;
	
	@Autowired
	FolhaPagamentoService folhaPagamentoService;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Test
	@Transactional
	@Order(1)
	void mustSaveTheFolhaDePagamentoSuccessfully() {
		var funcionario = new Funcionario(null, "Pedro", "273.118.060-80", TipoIdentificacao.CARTAO);
		funcionarioRepository.save(funcionario);
		
		assertEquals(0, folhaPagamentoRepository.count());
		
		folhaPagamentoService.novaFolhaPagamento(funcionario);
		
		assertEquals(1, folhaPagamentoRepository.count());
	}

	@Test
	@Order(2)
	void mustFetchAListOfFolhaDePagamentosSuccesssfully() {
		
		var list = folhaPagamentoService.findAll();
		
		assertNotNull(list);
		assertEquals(list.size(), folhaPagamentoRepository.count());
		
	}
	
	@Test
	@Order(3) 
	void mustFindTheFolhaPagamentoFromTheIdSuccessfully() {
		var funcionario = new Funcionario(null, "Pedro", "123.332.060-10", TipoIdentificacao.CARTAO);
		var folhaPagamento = new FolhaPagamento(null, funcionario, 0, new BigDecimal(0.0));
		
		funcionarioRepository.save(funcionario);
		folhaPagamentoRepository.save(folhaPagamento);
		
		Long id = folhaPagamentoRepository.findAll().get(0).getId();
		
		FolhaPagamento folhPagamento = folhaPagamentoService.findById(id);
		
		assertEquals(id, folhPagamento.getId());
		assertEquals(0, folhPagamento.getHorasTrabalhadas());
		assertEquals(new BigDecimal("0.00"), folhPagamento.getSalario());
	}
	
	@Test
	@Order(4)
	void mustSaveSalarySuccessfully() {
		String intervalo = "10:00:00";
		folhaPagamentoService.salvarSalario(intervalo);
		
		Long id = folhaPagamentoRepository.findAll().get(1).getId();
		
		FolhaPagamento folhPagamento = folhaPagamentoService.findById(id);
		
		assertEquals(new BigDecimal("2000.00"), folhPagamento.getSalario());
	}
	
	@Test
	@Order(5)
	void mustFindHoursWorkedSuccessfully() {
		String intervalo = "10:00:00";
		
		Integer horas = folhaPagamentoService.acharHorasTrabalhadas(intervalo);
		
		assertEquals(10, horas);
	}
	
	@Test
	@Order(6)
	void mustcalculeSalarySuccessfully() {
		String intervalo = "10:00:00";
		BigDecimal valorPorHora = new BigDecimal("200.0");
		
		BigDecimal salario = folhaPagamentoService.calcularSalario(intervalo, valorPorHora);
		
		assertEquals(new BigDecimal("2000.0"), salario);
	}
}
