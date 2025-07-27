package com.wamk.sistemaponto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.sistemaponto.dtos.FolhaPagamentoDTO;
import com.wamk.sistemaponto.dtos.min.FolhaPagamentoMinDTO;
import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.servcies.FolhaPagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class FolhaPagamentoController {
	
	@Autowired
	private FolhaPagamentoService folhaPagamentoService;
	
	@GetMapping
	public ResponseEntity<List<FolhaPagamentoMinDTO>> findAll(){
		List<FolhaPagamento> list = folhaPagamentoService.findAll();
		var dtos = list.stream().map(x -> new FolhaPagamentoMinDTO(x)).toList();
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FolhaPagamentoDTO> findById(@PathVariable Long id){
		FolhaPagamento folhaPagamentoOpt = folhaPagamentoService.findById(id);
		return ResponseEntity.ok(new FolhaPagamentoDTO(folhaPagamentoOpt));
	}
	
	@GetMapping("/pages")
	public ResponseEntity<Page<FolhaPagamento>> paginar(Pageable pageable){
		Page<FolhaPagamento> pages = folhaPagamentoService.findAll(pageable);
		return ResponseEntity.ok(pages);
	}
}
