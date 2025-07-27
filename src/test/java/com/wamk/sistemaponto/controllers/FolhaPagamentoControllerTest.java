package com.wamk.sistemaponto.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FolhaPagamentoRepository;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;
import com.wamk.sistemaponto.servcies.impl.FolhaPagamentoServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FolhaPagamentoControllerTest {
	
	private static String ENDPOINT = "/pagamentos";
	
	@Autowired
	FolhaPagamentoRepository folhaPagamentoRepository;
	
	@Autowired
	FolhaPagamentoServiceImpl folhaPagamentoService;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@Order(1)
	void mustSaveTheEntities() throws Exception {
		
		Funcionario funcionario = new Funcionario(null, "Wilson", "160.942.170-12", TipoIdentificacao.BIOMETRIA);
		FolhaPagamento folhaPagamento = new FolhaPagamento(null, funcionario, 8, new BigDecimal(1600));
		
		funcionarioRepository.save(funcionario);
		folhaPagamentoRepository.save(folhaPagamento);
		
	}

	@Test
	@Order(2)
	void mustFetchAListOfFolhaDePagamentosSuccesssfully() throws Exception {

		mockMvc.perform(get(ENDPOINT))
				.andExpect(status().isOk());
		
	}
	
	@Test
	@Order(3)
	void mustFindTheFolhaPagamentoFromTheIdSuccessfully() throws Exception {
		
		Long id = folhaPagamentoRepository.findAll().get(0).getId();

		mockMvc.perform(get(ENDPOINT + "/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(id.intValue())))
				.andExpect(jsonPath("$.horasTrabalhadas", equalTo(8)))
				.andExpect(jsonPath("$.salario", equalTo(1600.0)));
	}
	
	@Test
	@Order(4)
	void mustPageAListOfFolhaPagamentoSuccessfully() throws Exception {

		mockMvc.perform(get(ENDPOINT + "/pages"))
				.andExpect(status().isOk());
		
	}

}
