package com.wamk.sistemaponto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.wamk.sistemaponto.dtos.FuncionarioDTO;
import com.wamk.sistemaponto.dtos.inputs.FuncionarioInputDTO;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.servcies.FolhaPagamentoService;
import com.wamk.sistemaponto.servcies.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FolhaPagamentoService folhaPagamentoService;

	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> getAll(){
		List<FuncionarioDTO> list = funcionarioService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable Long id){
		Funcionario funcionarioOpt = funcionarioService.findById(id);
		return ResponseEntity.ok(funcionarioOpt);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<Page<FuncionarioDTO>> paginar(Pageable pageable){
		Page<Funcionario> pages = funcionarioService.findAll(pageable);
		Page<FuncionarioDTO> pagesDTO = pages.map(FuncionarioDTO::new);
		return ResponseEntity.ok(pagesDTO);
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody @Valid FuncionarioInputDTO funcionarioDTO){
		var funcionario = funcionarioService.novoFuncionario(funcionarioDTO);
		folhaPagamentoService.novaFolhaPagamento(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@RequestBody @Valid FuncionarioInputDTO funcionarioDTO, 
			@PathVariable Long id){
		funcionarioService.atulizarFuncionario(id, funcionarioDTO);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> deletarFuncionario(@PathVariable Long id){
		funcionarioService.deletarFuncionarioPorId(id);
		return ResponseEntity.ok().build();
	}
}
