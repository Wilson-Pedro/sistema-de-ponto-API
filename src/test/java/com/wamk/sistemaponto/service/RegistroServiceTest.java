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

import com.wamk.sistemaponto.enums.FrequenciaStatus;
import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.model.RegistroEntrada;
import com.wamk.sistemaponto.projections.RegistroMinProjection;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;
import com.wamk.sistemaponto.repositories.RegistroRepository;
import com.wamk.sistemaponto.servcies.RegistroService;

import jakarta.transaction.Transactional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegistroServiceTest {
	
	@Autowired
	RegistroService registroService;
	
	@Autowired
	RegistroRepository registroRepository;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Test
	@Order(1)
	void mustSaveTheRegistroSuccessfully() {
		funcionarioRepository.deleteAll();
		
		Funcionario funcionario = new Funcionario(null, "Wilson", "160.942.170-12", TipoIdentificacao.BIOMETRIA);
		
		RegistroEntrada registroEntrada = new RegistroEntrada();
		registroEntrada.setId(null);
		registroEntrada.indefinido();
		registroEntrada.setDataHora("14:00:00");
		registroEntrada.ponto();
		registroEntrada.setFuncionario(funcionario);
		
		assertEquals(0, registroRepository.count());
		
		funcionarioRepository.save(funcionario);
		registroService.save(registroEntrada);
		
		assertEquals(1, registroRepository.count());
	}

	@Test
	@Order(2)
	void mustFetchAListOfRegistrosSuccessfully() {
		
		List<Registro> list = registroService.findAll();
		
		assertNotNull(list);
		assertEquals(list.size(), registroRepository.count());
		
	}

	@Test
	@Order(3)
	void mustRegisterEntrySuccessFully() {
		Long funcionarioid = funcionarioRepository.findAll().get(0).getId();
		
		registroService.registrarEntrada(funcionarioid);
		Long registroId = registroRepository.findAll().get(1).getId();
		
		Registro registro = registroRepository.findById(registroId).get();
		
		assertNotNull(registro);
		assertEquals(TipoRegistro.ENTRADA, registro.getTipoRegistro());
	}
	
	@Test
	@Order(4)
	@Transactional
	void mustRegisterOutputSuccessFully() {
		Long funcionarioid = funcionarioRepository.findAll().get(0).getId();
		
		registroService.registrarSaida(funcionarioid);
		Long registroId = registroRepository.findAll().get(2).getId();
		
		Registro registro = registroRepository.findById(registroId).get();
		
		assertNotNull(registro);
		assertEquals(TipoRegistro.SAIDA, registro.getTipoRegistro());
	}
	
	@Test
	@Order(5)
	void mustFindAllRecorddsByEmployeeIdSuccessfully() {
		
		Long id = funcionarioRepository.findAll().get(0).getId();
		
		List<RegistroMinProjection> projections = registroService.findAllByFuncionarioId(id);
		
		assertNotNull(projections);
		assertEquals(2, projections.size());
	}
	
	@Test
	void mustSetFrequencyPontooSuccessfully() {
		
		Integer statusCode = registroService.definirFrequenciaStatus("2024-02-09T14:00:00", "14:00:00", TipoRegistro.ENTRADA);
		
		FrequenciaStatus status = FrequenciaStatus.toEnum(statusCode);
		
		assertEquals(FrequenciaStatus.PONTO, status);
	}
	
	@Test
	void mustSetFrequencyAtrasadoSuccessfully() {
		
		Integer statusCode = registroService.definirFrequenciaStatus("2024-02-09T15:00:00", "14:00:00", TipoRegistro.ENTRADA);
		
		FrequenciaStatus status = FrequenciaStatus.toEnum(statusCode);
		
		assertEquals(FrequenciaStatus.ATRASADO, status);
	}
	
	@Test
	void mustSetFrequencyHoraExtraSuccessfully() {
		
		Integer statusCode = registroService.definirFrequenciaStatus("2024-02-09T19:00:00", "18:00:00", TipoRegistro.SAIDA);
		
		FrequenciaStatus status = FrequenciaStatus.toEnum(statusCode);
		
		assertEquals(FrequenciaStatus.HORA_EXTRA, status);
	}
}
