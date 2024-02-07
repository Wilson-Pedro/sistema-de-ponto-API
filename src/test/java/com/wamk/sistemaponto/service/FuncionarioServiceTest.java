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
	void mustSaveTheFuncionarioSuccessfully() {
		funcionarioRepository.deleteAll();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Wilson");
		funcionario.setCpf("160.942.170-12");
		funcionario.setTipoIdentificacao(TipoIdentificacao.BIOMETRIA);
		funcionario.setRegistros(null);
		
		assertEquals(0, funcionarioRepository.count());
		
		funcionarioService.save(funcionario);
		
		assertEquals(1, funcionarioRepository.count());
	}

	@Test
	@Order(2)
	void mustFetchAListOfFuncionariosSuccessfully() {
		
		List<Funcionario> list = funcionarioService.findAll();
		
		assertNotNull(list);
		assertEquals(list.size(), funcionarioRepository.count());
		
	}

	@Test
	@Order(3)
	void mustFindTheFuncionarioFromTheIdSuccessfully() {
		
		Long id = funcionarioService.findAll().get(0).getId();
		
		Funcionario funcionario = funcionarioService.findById(id);
		
		assertNotNull(funcionario);
		assertEquals("Wilson", funcionario.getNome());
		assertEquals("160.942.170-12", funcionario.getCpf());
		assertEquals(TipoIdentificacao.BIOMETRIA, funcionario.getTipoIdentificacao());
	}
	
	@Test
	@Order(4)
	void mustUpdateTheFuncionarioSuccessfully() {
		Long id = funcionarioService.findAll().get(0).getId();
		
		Funcionario funcionario = funcionarioService.findById(id);
		funcionario.setNome("Pedro");
		
		Funcionario funcionarioAtualizado = funcionarioService.atulizarFuncionario(id, funcionario);
		
		assertEquals("Pedro", funcionarioAtualizado.getNome());
		assertEquals(1, funcionarioRepository.count());
	}
	
	@Test
	@Order(5)
	void mustDeleteTheFuncionarioFromTheIdSuccessfully() {
		
		assertEquals(1, funcionarioRepository.count());
		
		Long id = funcionarioService.findAll().get(0).getId();
		
		funcionarioService.deletarFuncionarioPorId(id);
		
		assertEquals(0, funcionarioRepository.count());
	}
}
