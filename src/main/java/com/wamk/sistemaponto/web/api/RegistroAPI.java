package com.wamk.sistemaponto.web.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wamk.sistemaponto.dtos.RegistroDTO;
import com.wamk.sistemaponto.dtos.min.RegistroMinDTO;
import com.wamk.sistemaponto.model.Registro;

@RequestMapping("/registros")
public interface RegistroAPI {

	@GetMapping
	ResponseEntity<List<RegistroMinDTO>> findAll();
	
	@GetMapping("/{id}/funcionarios")
	ResponseEntity<List<RegistroMinDTO>> findAllById(@PathVariable Long id);
	
	@GetMapping("/pages")
	ResponseEntity<Page<RegistroDTO>> paginar(Pageable pageable);
	
	@PostMapping("/{id}/entrada")
	ResponseEntity<Registro> registrarEntrada(@PathVariable Long id);
	
	@PostMapping("/{id}/saida")
	ResponseEntity<Registro> registrarSaida(@PathVariable Long id);
}
