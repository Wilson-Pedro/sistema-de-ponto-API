package com.wamk.sistemaponto.servcies;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wamk.sistemaponto.model.Funcionario;

public interface FuncionarioService {

	public Funcionario save(Funcionario funcionario);
	
	public List<Funcionario> findAll();
	
	public Page<Funcionario> findAll(Pageable pageable);
	
	public Funcionario findById(Long id);
	
	Funcionario atulizarFuncionario(Long id, Funcionario funcionario);
	
	public void deletarFuncionarioPorId(Long id);
}
