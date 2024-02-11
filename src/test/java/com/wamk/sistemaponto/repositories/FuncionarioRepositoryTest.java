package com.wamk.sistemaponto.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.Funcionario;

@DataJpaTest
class FuncionarioRepositoryTest {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Test
	void existsByCpfTest() {
		funcionarioRepository.save(new Funcionario(null, "Wilson", "200.900.100-10", TipoIdentificacao.BIOMETRIA));
		
		
		assertTrue(funcionarioRepository.existsByCpf("200.900.100-10"));
	}
}
