package com.wamk.sistemaponto.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
		List<FolhaPagamentoMinDTO> list = folhaPagamentoService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FolhaPagamentoDTO> findById(@PathVariable Long id){
		Optional<FolhaPagamento> folhaPagamentoOpt = folhaPagamentoService.findById(id);
		FolhaPagamentoDTO folhaPagamentoDTO = new FolhaPagamentoDTO();
		BeanUtils.copyProperties(folhaPagamentoOpt.get(), folhaPagamentoDTO);
		return ResponseEntity.ok(folhaPagamentoDTO);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<Page<FolhaPagamento>> paginar(Pageable pageable){
		Page<FolhaPagamento> pages = folhaPagamentoService.findAll(pageable);
		return ResponseEntity.ok(pages);
	}
}
