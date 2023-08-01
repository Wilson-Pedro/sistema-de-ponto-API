package com.wamk.sistemaponto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.sistemaponto.model.Registro;
import com.wamk.sistemaponto.servcies.RegistroService;

@RestController
@RequestMapping("/registros")
public class RegistroController {

	@Autowired
	private RegistroService registroService;
	
	@GetMapping
	public ResponseEntity<List<Registro>> getAll(){
		List<Registro> list = registroService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/{id}/entrada")
	public ResponseEntity<Registro> registrarEntrada(@PathVariable Long id){
		Registro registro = registroService.registrarEntrada(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@PostMapping("/{id}/saida")
	public ResponseEntity<Registro> registrarSaida(@PathVariable Long id){
		Registro registro = registroService.registrarSaida(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
}
