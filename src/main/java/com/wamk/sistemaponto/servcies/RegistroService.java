package com.wamk.sistemaponto.servcies;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.horario.IntervaloHorarioCalculo;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.model.RegistroEntrada;
import com.wamk.sistemaponto.model.RegistroSaida;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;
import com.wamk.sistemaponto.repositories.RegistroRepository;

import jakarta.transaction.Transactional;

@Service
public class RegistroService {

	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
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
		Registro registro = criarRegistroEntrada(id);
		registro.setTipoRegistro(TipoRegistro.ENTRADA);
		save(registro);
		return registro;
	}
	
	public RegistroSaida registrarSaida(Long id) {
		RegistroSaida registro = criarRegistroSaida(id);
		registro.setTipoRegistro(TipoRegistro.SAIDA);
		Funcionario funcionario = funcionarioRepository.findById(id).get();
		int validarSaida = funcionario.validarSaida();
		if(validarSaida == 0) {
			return null;
		}
		String intervalo = acharIntervalo(registro.getDataHora(), funcionario);
		registro.setIntervalo(intervalo);
		save(registro);
		return registro;
	}

	public Registro criarRegistroEntrada(Long id) {
		Funcionario func = funcionarioRepository.findById(id).get();
		RegistroEntrada registro = new RegistroEntrada();
		registro.setFuncionario(func);
		registro.setDataHora(OffsetDateTime.now().format(formatter));
		registro.setTipoRegistro(TipoRegistro.INDEFINIDO);
		return registro;
	}
	
	public RegistroSaida criarRegistroSaida(Long id) {
		Funcionario func = funcionarioRepository.findById(id).get();
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
