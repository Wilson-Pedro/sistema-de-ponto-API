package com.wamk.sistemaponto.web.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wamk.sistemaponto.dtos.FolhaPagamentoDTO;
import com.wamk.sistemaponto.dtos.min.FolhaPagamentoMinDTO;
import com.wamk.sistemaponto.model.FolhaPagamento;

@RequestMapping("/pagamentos")
public interface FolhaPagamentoAPI {

	@GetMapping
	ResponseEntity<List<FolhaPagamentoMinDTO>> findAll();
	
	@GetMapping("/{id}")
	ResponseEntity<FolhaPagamentoDTO> findById(@PathVariable Long id);
	
	@GetMapping("/pages")
	ResponseEntity<Page<FolhaPagamento>> paginar(Pageable pageable);
}
