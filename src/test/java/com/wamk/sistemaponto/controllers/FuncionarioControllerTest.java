package com.wamk.sistemaponto.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wamk.sistemaponto.dtos.inputs.FuncionarioInputDTO;
import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;
import com.wamk.sistemaponto.repositories.RegistroRepository;
import com.wamk.sistemaponto.servcies.impl.FuncionarioServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FuncionarioControllerTest {
	
	private static String ENDPOINT = "/funcionarios";

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	FuncionarioServiceImpl funcionarioService;
	
	@Autowired
	RegistroRepository registroRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@Order(1)
	void deleteAll() {
		registroRepository.deleteAll();
		funcionarioRepository.deleteAll();
	}

	@Test
	@Order(2)
	void mustSaveTheFuncionarioSuccessfully() throws Exception {
		
		String jsonRequest = objectMapper.writeValueAsString(
				new FuncionarioInputDTO("Wilson", "160.942.170-12", TipoIdentificacao.BIOMETRIA));
		
		assertEquals(0, funcionarioRepository.count());
		
		mockMvc.perform(post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.nome", equalTo("Wilson")))
				.andExpect(jsonPath("$.cpf", equalTo("160.942.170-12")))
				.andExpect(jsonPath("$.tipoIdentificacao", equalTo("BIOMETRIA")));
		
		assertEquals(1, funcionarioRepository.count());
	}

	@Test
	@Order(3)
	void mustFetchAListOfFuncionariosSuccessfully() throws Exception {
		
		mockMvc.perform(get(ENDPOINT))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	@Order(4)
	void mustPageAListOfFuncionariosSuccessfully() throws Exception {
		
		mockMvc.perform(get(ENDPOINT + "/pages"))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	@Order(5)
	void mustFindTheFuncionarioFromTheIdSuccessfully() throws Exception {
		
		Long id = funcionarioService.findAll().get(0).getId();
		
		mockMvc.perform(get(ENDPOINT + "/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", equalTo("Wilson")))
				.andExpect(jsonPath("$.cpf", equalTo("160.942.170-12")))
				.andExpect(jsonPath("$.tipoIdentificacao", equalTo("BIOMETRIA")));
	}

	@Test
	@Order(6)
	void mustUpdateTheFuncionarioSuccessfully() throws Exception {

		String jsonRequest = objectMapper.writeValueAsString(
				new FuncionarioInputDTO("Pedro", "160.942.170-12", TipoIdentificacao.CARTAO));
		
		Long id = funcionarioService.findAll().get(0).getId();
		
		mockMvc.perform(put(ENDPOINT + "/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", equalTo("Pedro")))
				.andExpect(jsonPath("$.tipoIdentificacao", equalTo("CARTAO")));
		
		assertEquals(1, funcionarioRepository.count());
	}
	
	@Test
	@Order(7)
	void mustDeleteTheFuncionarioFromTheIdSuccessfully() throws Exception {
		
		assertEquals(1, funcionarioRepository.count());
		
		Long id = funcionarioService.findAll().get(0).getId();
		
		mockMvc.perform(delete(ENDPOINT + "/{id}", id))
				.andExpect(status().isOk());
		
		assertEquals(0, funcionarioRepository.count());
	}

}
