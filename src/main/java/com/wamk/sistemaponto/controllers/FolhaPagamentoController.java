package com.wamk.sistemaponto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.sistemaponto.servcies.FolhaPagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class FolhaPagamentoController {
	
	@Autowired
	private FolhaPagamentoService folhaPagamentoService;
}
