package com.wamk.sistemaponto.web.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wamk.sistemaponto.dtos.FuncionarioDTO;
import com.wamk.sistemaponto.dtos.inputs.FuncionarioInputDTO;
import com.wamk.sistemaponto.model.Funcionario;

@RequestMapping("/funcionarios")
public interface FuncionarioAPI {

	@GetMapping
	ResponseEntity<List<FuncionarioDTO>> getAll();
	
	@GetMapping("/{id}")
	ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id);
	
	@GetMapping("/pages")
	ResponseEntity<Page<FuncionarioDTO>> paginar(Pageable pageable);
	
	@PostMapping
	public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@RequestBody FuncionarioInputDTO funcionarioDTO);
	
	@PutMapping("/{id}")
	ResponseEntity<FuncionarioDTO> atualizarFuncionario(
			@RequestBody FuncionarioInputDTO funcionarioDTO, 
			@PathVariable Long id);
	
	@DeleteMapping("/{id}")
	ResponseEntity<Funcionario> deletarFuncionario(@PathVariable Long id);
}
