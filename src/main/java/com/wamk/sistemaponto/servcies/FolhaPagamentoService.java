package com.wamk.sistemaponto.servcies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.repositories.FolhaPagamentoRepository;

@Service
public class FolhaPagamentoService {

	@Autowired
	private FolhaPagamentoRepository folhaPagamentoRepository;

	public List<FolhaPagamento> findAll() {
		return folhaPagamentoRepository.findAll();
	}

	public Optional<FolhaPagamento> findById(Long id) {
		return folhaPagamentoRepository.findById(id);
	}
}
