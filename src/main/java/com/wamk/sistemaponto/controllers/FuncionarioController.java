package com.wamk.sistemaponto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.sistemaponto.dto.FuncionarioDTO;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.servcies.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public ResponseEntity<List<Funcionario>> getAll(){
		List<Funcionario> list = funcionarioService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable Long id){
		Funcionario funcionarioOpt = funcionarioService.findById(id).get();
		return ResponseEntity.ok(funcionarioOpt);
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO){
		var funcionario = funcionarioService.novoFuncionario(funcionarioDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO, 
			@PathVariable Long id){
		var funcionarioOpt = funcionarioService.findById(id);
		if(funcionarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		funcionarioService.atulizarFuncionario(id, funcionarioDTO);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> deletarFuncionario(@PathVariable Long id){
		var funcionarioOpt = funcionarioService.findById(id);
		if(funcionarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		funcionarioService.deletarFuncionarioPorId(id);
		return ResponseEntity.ok().build();
	}
}
