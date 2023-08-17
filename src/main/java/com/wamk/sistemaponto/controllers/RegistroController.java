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

import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.servcies.FuncionarioService;
import com.wamk.sistemaponto.servcies.RegistroService;

@RestController
@RequestMapping("/registros")
public class RegistroController {

	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public ResponseEntity<List<Registro>> getAll(){
		List<Registro> list = registroService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{funciorarioId}")
	public ResponseEntity<List<Registro>> findAllById(@PathVariable Long funciorarioId){
		List<Registro> list = registroService.findAllById(funciorarioId);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<Page<Registro>> paginar(Pageable pageable){
		Page<Registro> pages = registroService.findAll(pageable);
		return ResponseEntity.ok(pages);
	}
	
	@PostMapping("/{id}/entrada")
	public ResponseEntity<Registro> registrarEntrada(@PathVariable Long id){
		Registro registro = registroService.registrarEntrada(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@PostMapping("/{id}/saida")
	public ResponseEntity<Object> registrarSaida(@PathVariable Long id){
		Funcionario funcionario = funcionarioService.findById(id).get();
		int validarSaida = funcionario.validarSaida();
		if (validarSaida == 0) {
			return ResponseEntity.badRequest().body("É preciso registrar uma ENTRADA "
					+ "antes de registrar uma SAÍDA");
		}
		Registro registro = registroService.registrarSaida(id);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
}
