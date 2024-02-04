package com.wamk.sistemaponto.servcies;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wamk.sistemaponto.dtos.min.RegistroMinDTO;
import com.wamk.sistemaponto.enums.FrequenciaStatus;
import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.horario.IntervaloHorarioCalculo;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.model.RegistroEntrada;
import com.wamk.sistemaponto.model.RegistroSaida;
import com.wamk.sistemaponto.projections.RegistroMinProjection;
import com.wamk.sistemaponto.repositories.RegistroRepository;



@Service
public class RegistroService {
	
	@Autowired
	private FolhaPagamentoService folhaPagamentoService;

	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	@Transactional
	public void save(Registro registro) {
		registroRepository.save(registro);
	}
	
	@Transactional(readOnly = true)
	public List<RegistroMinDTO> findAll() {
		List<Registro> list = registroRepository.findAll();
		return list.stream().map(x -> new RegistroMinDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public Page<Registro> findAll(Pageable pageable) {
		return registroRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<RegistroMinDTO> findAllById(Long id){
		List<RegistroMinProjection> list = registroRepository.searchById(id);
		return list.stream().map(x -> new RegistroMinDTO(x)).toList();
	}

	public Registro registrarEntrada(Long id) {
		Registro registro = criarRegistro(id);
		registro.setTipoRegistro(TipoRegistro.ENTRADA);
		Integer status = definirFrequenciaStatus(registro.getDataHora(), 
				"21:00:00", TipoRegistro.ENTRADA);
		registro.setFrequencia(FrequenciaStatus.toEnum(status));
		RegistroEntrada registroEntrada = new RegistroEntrada(registro);
		save(registroEntrada);
		return registroEntrada;
	}

	public RegistroSaida registrarSaida(Long id) {
		var registro = criarRegistro(id);
		var funcionario = funcionarioService.findById(id);
		Integer status = definirFrequenciaStatus(registro.getDataHora(), 
				"22:00:00", TipoRegistro.SAIDA);
		
		registro.setFrequencia(FrequenciaStatus.toEnum(status));
		registro.setTipoRegistro(TipoRegistro.SAIDA);
		String intervalo = acharIntervalo(registro.getDataHora(), funcionario);
		RegistroSaida registroSaida = new RegistroSaida(registro);
		registroSaida.setIntervalo(intervalo);
		save(registroSaida);
		folhaPagamentoService.salvarSalario(intervalo, id);
		return registroSaida;
	}
	
	private Integer definirFrequenciaStatus(String dataHora, String horarioPonto, TipoRegistro registro) {
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
		Funcionario func = funcionarioService.findById(id);
		Registro registro = new RegistroEntrada();
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
