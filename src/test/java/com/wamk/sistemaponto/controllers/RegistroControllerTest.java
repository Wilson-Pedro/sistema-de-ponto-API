package com.wamk.sistemaponto.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.servcies.impl.FuncionarioServiceImpl;
import com.wamk.sistemaponto.servcies.impl.RegistroServiceImpl;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegistroControllerTest {
	
	private static String ENDPOINT = "/registros";
	
	@Autowired
	RegistroServiceImpl registroService;
	
	@Autowired
	FuncionarioServiceImpl funcionarioService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@Order(1)
	void mustRegisterEntrySuccessFully() throws Exception {
		
		funcionarioService.save(new Funcionario(null, "Wilson", "211.908.060-20", TipoIdentificacao.BIOMETRIA));
		
		Long funcionarioId = funcionarioService.findAll().get(0).getId();
		
		mockMvc.perform(post(ENDPOINT + "/{id}/entrada", funcionarioId))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.tipoRegistro", equalTo("ENTRADA")))
				.andReturn();
	}
	
	@Test
	@Order(2)
	@Transactional
	void mustRegisterOutputSuccessFully() throws Exception {
		
		Long funcionarioId = funcionarioService.findAll().get(0).getId();
		
		mockMvc.perform(post(ENDPOINT + "/{id}/saida", funcionarioId))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.tipoRegistro", equalTo("SAIDA")))
				.andReturn();
	}
	
	@Test
	@Order(3)
	void mustFetchAListOfRegistrosSuccessfully() throws Exception {
		
		mockMvc.perform(get(ENDPOINT))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	@Order(4)
	void mustfindAllByFuncionarioIdSuccessfully() throws Exception {
		
		Long funcionarioId = funcionarioService.findAll().get(0).getId();
		
		mockMvc.perform(get(ENDPOINT + "/{id}/funcionarios", funcionarioId))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	@Order(5)
	void mustPageAListOfRegistrosSuccessfully() throws Exception {

		mockMvc.perform(get(ENDPOINT + "/pages"))
				.andExpect(status().isOk())
				.andReturn();
	}
}
