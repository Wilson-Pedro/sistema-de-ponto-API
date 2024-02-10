package com.wamk.sistemaponto.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;
import com.wamk.sistemaponto.servcies.RegistroService;

import jakarta.transaction.Transactional;

@SpringBootTest
class RegistroExceptionTest {
	
	@Autowired
	RegistroService registroService;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Test
	@Transactional
	void RegistroSaidaExceptionWhenTryingRegisterOutput() {
		funcionarioRepository.save(new Funcionario(null, "Wilson", "160.942.170-12", TipoIdentificacao.BIOMETRIA));
		
		Long funcionarioid = funcionarioRepository.findAll().get(0).getId();
		
		assertThrows(RegistroSaidaException.class, () -> registroService.registrarSaida(funcionarioid));
	}
}
