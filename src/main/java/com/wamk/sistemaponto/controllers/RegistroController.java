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
import com.wamk.sistemaponto.servcies.FuncionarioService;
import com.wamk.sistemaponto.servcies.RegistroService;
import com.wamk.sistemaponto.servcies.ValidacaoService;

@RestController
@RequestMapping("/registros")
public class RegistroController {

	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private ValidacaoService validacaoService;
	
	@GetMapping
	public ResponseEntity<List<RegistroMinDTO>> findAll(){
		List<RegistroMinDTO> list = registroService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}/funcionarios")
	public ResponseEntity<List<RegistroMinDTO>> findAllById(@PathVariable Long id){
		List<RegistroMinDTO> list = registroService.findAllById(id);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<Page<RegistroDTO>> paginar(Pageable pageable){
		Page<Registro> pages = registroService.findAll(pageable);
		Page<RegistroDTO> pagesDTO = pages.map(RegistroDTO::new);
		return ResponseEntity.ok(pagesDTO);
	}
	
	@PostMapping("/{id}/entrada")
	public ResponseEntity<Registro> registrarEntrada(@PathVariable Long id){
		Registro registro = registroService.registrarEntrada(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@PostMapping("/{id}/saida")
	public ResponseEntity<Object> registrarSaida(@PathVariable Long id){
		Funcionario funcionario = funcionarioService.findById(id);
		validacaoService.validarSaida(funcionario);
		Registro registro = registroService.registrarSaida(id);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
}
