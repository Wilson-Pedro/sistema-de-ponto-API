package com.wamk.sistemaponto.servcies;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;
import com.wamk.sistemaponto.repositories.RegistroRepository;

@Service
public class RegistroService {

	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public void save(Registro registro) {
		registroRepository.save(registro);
	}

	public Registro registroEntrada(Long id) {
		Registro registro = criarNovoRegistro(id);
		registro.setTipoRegistro(TipoRegistro.ENTRADA);
		save(registro);
		return registro;
	}
	
	public Registro registroSaida(Long id) {
		Registro registro = criarNovoRegistro(id);
		registro.setTipoRegistro(TipoRegistro.SAIDA);
		save(registro);
		return registro;
	}
	
	public Registro criarNovoRegistro(Long id) {
		Funcionario func = funcionarioRepository.findById(id).get();
		Registro registro = new Registro();
		registro.setFuncionario(func);
		registro.setDataHora(OffsetDateTime.now());
		registro.setTipoRegistro(TipoRegistro.INDEFINIDO);
		return registro;
	}
}
