package com.wamk.sistemaponto.servcies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.sistemaponto.repositories.FolhaPagamentoRepository;

@Service
public class FolhaPagamentoService {

	@Autowired
	private FolhaPagamentoRepository folhaPagamentoRepository;
}
