package com.wamk.sistemaponto.servcies;

import org.springframework.stereotype.Service;

import com.wamk.sistemaponto.exceptions.RegistroSaidaException;
import com.wamk.sistemaponto.model.Funcionario;

@Service
public class ValidacaoService {

	public void validarSaida(Funcionario funcionario) {
		int validarSaida = funcionario.validarSaida();
		if (validarSaida == 0) {
			throw new RegistroSaidaException(
					"É preciso registrar uma ENTRADA antes de registrar uma SAÍDA");
		}
	}

	
}
