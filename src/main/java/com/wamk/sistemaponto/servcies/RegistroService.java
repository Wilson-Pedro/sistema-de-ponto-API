package com.wamk.sistemaponto.servcies;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wamk.sistemaponto.enums.TipoRegistro;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.model.RegistroEntrada;
import com.wamk.sistemaponto.model.RegistroSaida;
import com.wamk.sistemaponto.projections.RegistroMinProjection;

public interface RegistroService {
	
	Registro save(Registro registro);
	
	List<Registro> findAll();
	
	Page<Registro> findAll(Pageable pageable);
	
	List<RegistroMinProjection> findAllByFuncionarioId(Long id);

	RegistroEntrada registrarEntrada(Long funcionarioID);

	RegistroSaida registrarSaida(Long id);
	
	Integer definirFrequenciaStatus(String dataHora, String horarioPonto, TipoRegistro registro);

	Registro criarRegistro(Long id);
}
