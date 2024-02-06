package com.wamk.sistemaponto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;
import com.wamk.sistemaponto.servcies.FuncionarioService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FuncionarioServiceTest {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	FuncionarioService funcionarioService;

	@Test
	@Order(1)
	void mustSaveTheEmployeeSuccessfully() {
		funcionarioRepository.deleteAll();
		
		Funcionario employee = new Funcionario();
		employee.setNome("Wilson");
		employee.setCpf("160.942.170-12");
		employee.setTipoIdentificacao(TipoIdentificacao.BIOMETRIA);
		employee.setRegistros(null);
		
		assertEquals(0, funcionarioRepository.count());
		
		funcionarioService.save(employee);
		
		assertEquals(1, funcionarioRepository.count());
	}

	@Test
	@Order(2)
	void mustFetchAListOdEmployeesSuccessfully() {
		
		List<Funcionario> list = funcionarioService.findAll();
		
		assertNotNull(list);
		assertEquals(list.size(), funcionarioRepository.count());
		
	}

	@Test
	@Order(3)
	void mustFindTheEmployeeFromTheIdSuccessfully() {
		
		Long id = funcionarioService.findAll().get(0).getId();
		
		Funcionario employee = funcionarioService.findById(id);
		
		assertNotNull(employee);
		assertEquals("Wilson", employee.getNome());
		assertEquals("160.942.170-12", employee.getCpf());
		assertEquals(TipoIdentificacao.BIOMETRIA, employee.getTipoIdentificacao());
	}
	
	@Test
	@Order(4)
	void mustUpdateTheEmployeeSuccessfully() {
		Long id = funcionarioService.findAll().get(0).getId();
		
		Funcionario employee = funcionarioService.findById(id);
		employee.setNome("Pedro");
		
		Funcionario employeeUpdated = funcionarioService.atulizarFuncionario(id, employee);
		
		assertEquals("Pedro", employeeUpdated.getNome());
		assertEquals(1, funcionarioRepository.count());
	}
	
	@Test
	@Order(5)
	void mustDeleteTheEmployeeFromTheIdSuccessfully() {
		
		assertEquals(1, funcionarioRepository.count());
		
		Long id = funcionarioService.findAll().get(0).getId();
		
		funcionarioService.deletarFuncionarioPorId(id);
		
		assertEquals(0, funcionarioRepository.count());
	}
}
