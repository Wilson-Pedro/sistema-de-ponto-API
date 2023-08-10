package com.wamk.sistemaponto.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.servcies.FolhaPagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class FolhaPagamentoController {
	
	@Autowired
	private FolhaPagamentoService folhaPagamentoService;
	
	@GetMapping
	public ResponseEntity<List<FolhaPagamento>> findAll(){
		List<FolhaPagamento> list = folhaPagamentoService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FolhaPagamento> findById(@PathVariable Long id){
		Optional<FolhaPagamento> folhaPagamentoOpt = folhaPagamentoService.findById(id);
		return ResponseEntity.ok(folhaPagamentoOpt.get());
	}
}
