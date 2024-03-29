package com.wamk.sistemaponto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.sistemaponto.dtos.RegistroDTO;
import com.wamk.sistemaponto.dtos.min.RegistroMinDTO;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.projections.RegistroMinProjection;
import com.wamk.sistemaponto.servcies.FuncionarioService;
import com.wamk.sistemaponto.servcies.RegistroService;

@RestController
@RequestMapping("/registros")
public class RegistroController {

	@Autowired
	private RegistroService registroService;
	
	@GetMapping
	public ResponseEntity findAll(){
		List<Registro> list = registroService.findAll();
		var dtos = list.stream().map(x -> new RegistroMinDTO(x)).toList();
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/{id}/funcionarios")
	public ResponseEntity findAllById(@PathVariable Long id){
		List<RegistroMinProjection> list = registroService.findAllByFuncionarioId(id);
		List<RegistroMinDTO> dtos = list.stream().map(x -> new RegistroMinDTO(x)).toList();
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/pages")
	public ResponseEntity paginar(Pageable pageable){
		Page<Registro> pages = registroService.findAll(pageable);
		Page<RegistroDTO> pagesDTO = pages.map(RegistroDTO::new);
		return ResponseEntity.ok(pagesDTO);
	}
	
	@PostMapping("/{id}/entrada")
	public ResponseEntity registrarEntrada(@PathVariable Long id){
		Registro registro = registroService.registrarEntrada(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@PostMapping("/{id}/saida")
	public ResponseEntity registrarSaida(@PathVariable Long id){
		Registro registro = registroService.registrarSaida(id);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
}
