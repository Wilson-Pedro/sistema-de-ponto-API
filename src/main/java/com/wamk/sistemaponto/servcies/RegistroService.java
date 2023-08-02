package com.wamk.sistemaponto.servcies;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;
import com.wamk.sistemaponto.repositories.RegistroRepository;

import jakarta.transaction.Transactional;

@Service
public class RegistroService {

	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss.SSSXXXX");
	
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
		Registro registro = criarNovoRegistro(id);
		registro.setTipoRegistro(TipoRegistro.ENTRADA);
		save(registro);
		return registro;
	}
	
	public Registro registrarSaida(Long id) {
		Registro registro = criarNovoRegistro(id);
		registro.setTipoRegistro(TipoRegistro.SAIDA);
		Funcionario funcionario = funcionarioRepository.findById(id).get();
		int validarSaida = funcionario.validarSaida();
		if(validarSaida == 0) {
			return null;
		}
		save(registro);
		return registro;
	}
	
	public Registro criarNovoRegistro(Long id) {
		Funcionario func = funcionarioRepository.findById(id).get();
		Registro registro = new Registro();
		registro.setFuncionario(func);
		registro.setDataHora(OffsetDateTime.now().format(formatter));
		registro.setTipoRegistro(TipoRegistro.INDEFINIDO);
		return registro;
	}
}
