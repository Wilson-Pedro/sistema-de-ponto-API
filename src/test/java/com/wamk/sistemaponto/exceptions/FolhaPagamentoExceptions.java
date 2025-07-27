package com.wamk.sistemaponto.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wamk.sistemaponto.servcies.impl.FolhaPagamentoServiceImpl;

@SpringBootTest
class FolhaPagamentoExceptions {
	
	@Autowired
	FolhaPagamentoServiceImpl folhaPagamentoService;

	@Test
	void EntityNotFoundExceptionWhenTryingToFindFolhaPagamento() {
		
		assertThrows(EntityNotFoundException.class, () -> folhaPagamentoService.findById(70L));
	}

}
