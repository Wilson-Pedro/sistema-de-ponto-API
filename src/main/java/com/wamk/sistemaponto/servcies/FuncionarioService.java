package com.wamk.sistemaponto.servcies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}

	public Funcionario findById(Long id) {
		return funcionarioRepository.findById(id).get();
	}
}
