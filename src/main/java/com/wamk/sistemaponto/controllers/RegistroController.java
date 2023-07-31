package com.wamk.sistemaponto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/{id}/entrada")
	public ResponseEntity<Registro> registrarEntrada(@PathVariable Long id){
		Registro registro = registroService.registroEntrada(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@PostMapping("/{id}/saida")
	public ResponseEntity<Registro> registrarSaida(@PathVariable Long id){
		Registro registro = registroService.registroSaida(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
}
