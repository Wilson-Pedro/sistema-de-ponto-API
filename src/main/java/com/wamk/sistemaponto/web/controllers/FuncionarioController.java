package com.wamk.sistemaponto.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.sistemaponto.dtos.FuncionarioDTO;
import com.wamk.sistemaponto.dtos.inputs.FuncionarioInputDTO;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.servcies.FolhaPagamentoService;
import com.wamk.sistemaponto.servcies.FuncionarioService;
import com.wamk.sistemaponto.web.api.FuncionarioAPI;

@RestController
public class FuncionarioController implements FuncionarioAPI{
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FolhaPagamentoService folhaPagamentoService;

	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> getAll(){
		var list = funcionarioService.findAll();
		var dtos = list.stream().map(x -> new FuncionarioDTO(x)).toList();
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id){
		Funcionario funcionarioOpt = funcionarioService.findById(id);
		return ResponseEntity.ok(new FuncionarioDTO(funcionarioOpt));
	}
	
	@GetMapping("/pages")
	public ResponseEntity<Page<FuncionarioDTO>> paginar(Pageable pageable){
		Page<Funcionario> pages = funcionarioService.findAll(pageable);
		return ResponseEntity.ok(pages.map(FuncionarioDTO::new));
	}
	
	@PostMapping
	public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@RequestBody FuncionarioInputDTO funcionarioDTO){
		var funcionario = funcionarioService.save(new Funcionario(funcionarioDTO));
		folhaPagamentoService.novaFolhaPagamento(funcionario);
		return ResponseEntity.status(201).body(new FuncionarioDTO(funcionario));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> atualizarFuncionario(
			@RequestBody FuncionarioInputDTO funcionarioDTO, 
			@PathVariable Long id){
		var funcionario = funcionarioService.atulizarFuncionario(id, new Funcionario(funcionarioDTO));
		return ResponseEntity.ok(new FuncionarioDTO(funcionario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> deletarFuncionario(@PathVariable Long id){
		funcionarioService.deletarFuncionarioPorId(id);
		return ResponseEntity.ok().build();
	}
}
