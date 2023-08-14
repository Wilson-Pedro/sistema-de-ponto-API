package com.wamk.sistemaponto.servcies;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.sistemaponto.enums.FrequenciaStatus;
import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.horario.IntervaloHorarioCalculo;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.model.RegistroEntrada;
import com.wamk.sistemaponto.model.RegistroSaida;
import com.wamk.sistemaponto.repositories.RegistroRepository;

import jakarta.transaction.Transactional;

@Service
public class RegistroService {
	
	@Autowired
	private FolhaPagamentoService folhaPagamentoService;

	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	public List<Registro> findAll() {
		return registroRepository.findAll();
	}
	
	@Transactional
	public void save(Registro registro) {
		registroRepository.save(registro);
	}
	
	public List<Registro> findAllById(Long funciorarioId){
		List<Registro> registros = registroRepository.searchById(funciorarioId);
		return registros;
	}

	public Registro registrarEntrada(Long id) {
		RegistroEntrada registro = criarRegistroEntrada(id);
		registro.setTipoRegistro(TipoRegistro.ENTRADA);
		Integer status = definirFrequenciaStatus(registro.getDataHora(), "13:00:00", TipoRegistro.ENTRADA);
		registro.setFrequencia(FrequenciaStatus.toEnum(status));
		save(registro);
		return registro;
	}

	public RegistroSaida registrarSaida(Long id) {
		RegistroSaida registro = criarRegistroSaida(id);
		registro.setTipoRegistro(TipoRegistro.SAIDA);
		Funcionario funcionario = funcionarioService.findById(id).get();
		int validarSaida = funcionario.validarSaida();
		if(validarSaida == 0) {
			return null;
		}
		String intervalo = acharIntervalo(registro.getDataHora(), funcionario);
		registro.setIntervalo(intervalo);
		Integer status = definirFrequenciaStatus(registro.getDataHora(), "17:00:00", TipoRegistro.SAIDA);
		registro.setFrequencia(FrequenciaStatus.toEnum(status));
		save(registro);
		folhaPagamentoService.salvarSalario(intervalo, id);
		return registro;
	}
	
	private Integer definirFrequenciaStatus(String dataHora, String horarioPonto, TipoRegistro registro) {
		String[] horario = dataHora.split("T");
		Integer status = 5;
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

	public RegistroEntrada criarRegistroEntrada(Long id) {
		Funcionario func = funcionarioService.findById(id).get();
		RegistroEntrada registro = new RegistroEntrada();
		registro.setFuncionario(func);
		registro.setDataHora(OffsetDateTime.now().format(formatter));
		registro.setTipoRegistro(TipoRegistro.INDEFINIDO);
		return registro;
	}
	
	public RegistroSaida criarRegistroSaida(Long id) {
		Funcionario func = funcionarioService.findById(id).get();
		RegistroSaida registro = new RegistroSaida();
		registro.setFuncionario(func);
		registro.setDataHora(OffsetDateTime.now().format(formatter));
		registro.setTipoRegistro(TipoRegistro.INDEFINIDO);
		return registro;
	}
	
	private String acharIntervalo(String saida, Funcionario funcionario) {
		String entrada = "";
		for(Registro x : funcionario.getRegistros()) {
			if(TipoRegistro.ENTRADA.equals(x.getTipoRegistro())) {
				entrada = x.getDataHora();
			}
		}
		String intervalo = IntervaloHorarioCalculo.intervalo(entrada, saida);
		
		return intervalo;
	}
}
