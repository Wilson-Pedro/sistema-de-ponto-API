package com.wamk.sistemaponto.servcies.impl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wamk.sistemaponto.enums.FrequenciaStatus;
import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.exceptions.RegistroSaidaException;
import com.wamk.sistemaponto.horario.IntervaloHorarioCalculo;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.model.RegistroEntrada;
import com.wamk.sistemaponto.model.RegistroSaida;
import com.wamk.sistemaponto.projections.RegistroMinProjection;
import com.wamk.sistemaponto.repositories.RegistroRepository;
import com.wamk.sistemaponto.servcies.RegistroService;

@Service
public class RegistroServiceImpl implements RegistroService {
	
	@Autowired
	private FolhaPagamentoServiceImpl folhaPagamentoService;

	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private FuncionarioServiceImpl funcionarioService;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	@Transactional
	public Registro save(Registro registro) {
		return registroRepository.save(registro);
	}
	
	@Transactional(readOnly = true)
	public List<Registro> findAll() {
		return registroRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Registro> findAll(Pageable pageable) {
		return registroRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<RegistroMinProjection> findAllByFuncionarioId(Long id){
		return registroRepository.searchById(id);
	}

	public RegistroEntrada registrarEntrada(Long funcionarioID) {
		Registro registro = criarRegistro(funcionarioID);
		registro.entrada();
		Integer status = definirFrequenciaStatus(registro.getDataHora(), 
				"14:00:00", TipoRegistro.ENTRADA);
		registro.setFrequencia(FrequenciaStatus.toEnum(status));
		return registroRepository.save(new RegistroEntrada(registro));
	}

	@Transactional
	public RegistroSaida registrarSaida(Long id) {
		var registro = criarRegistro(id);
		var funcionario = funcionarioService.findById(id);
		
		validarSaida(funcionario);
		
		Integer status = definirFrequenciaStatus(registro.getDataHora(), 
				"18:00:00", TipoRegistro.SAIDA);
		
		registro.setFrequencia(FrequenciaStatus.toEnum(status));
		registro.saida();
		
		String intervalo = acharIntervalo(LocalDateTime.parse(registro.getDataHora()), funcionario);
		RegistroSaida registroSaida = new RegistroSaida(registro);
		
		registroSaida.setIntervalo(intervalo);
		
		registroRepository.save(registroSaida);
		folhaPagamentoService.salvarSalario(intervalo);
		
		return registroSaida;
	}
	
	public Integer definirFrequenciaStatus(String dataHora, String horarioPonto, TipoRegistro registro) {
		String[] horario = dataHora.split("T");
		Integer status = 4;
		int comparacao = horario[1].compareTo(horarioPonto);
		if(comparacao <= 0) {
			status = FrequenciaStatus.PONTO.getCod();
		} else if (comparacao > 0 && TipoRegistro.ENTRADA.equals(registro)) {
			status = FrequenciaStatus.ATRASADO.getCod();
		} else if (comparacao > 0 && TipoRegistro.SAIDA.equals(registro)) {
			status = FrequenciaStatus.HORA_EXTRA.getCod();
		}
		return status;
	}

	public Registro criarRegistro(Long id) {
		Registro registro = new RegistroEntrada();
		registro.setFuncionario(funcionarioService.findById(id));
		registro.setDataHora(OffsetDateTime.now().format(formatter));
		registro.indefinido();
		return registro;
	}
	
	private String acharIntervalo(LocalDateTime saida, Funcionario funcionario) {
		
		String entrada = "";
		for(Registro x : funcionario.getRegistros()) {
			if(TipoRegistro.ENTRADA.equals(x.getTipoRegistro()))
				entrada = x.getDataHora();
		}
		return IntervaloHorarioCalculo.intervalo(LocalDateTime.parse(entrada), saida);
	}
	
	public void validarSaida(Funcionario funcionario) {
		if (funcionario.validarSaida() == 0) 
			throw new RegistroSaidaException(
					"É preciso registrar uma ENTRADA antes de registrar uma SAÍDA");
	}
}
