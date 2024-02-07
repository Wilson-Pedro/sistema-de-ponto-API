package com.wamk.sistemaponto.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.servcies.FuncionarioService;

@SpringBootTest
class FuncionarioExceptions {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Test
	void ExistingCepExceptionWhenTryingToFindFuncionario() {
		
		funcionarioService.save(new Funcionario(null, "Wilson", "673.998.460-30", TipoIdentificacao.BIOMETRIA));
		var funcionario = new Funcionario(null, "Pedro", "673.998.460-30", TipoIdentificacao.CARTAO);
		
		assertThrows(ExistingCepException.class, () -> funcionarioService.save(funcionario));
	}

	@Test
	void NotFoundExceptionWhenTryingToFindFuncionario() {
		
		assertThrows(EntityNotFoundException.class, () -> funcionarioService.findById(70L));
	}

}
