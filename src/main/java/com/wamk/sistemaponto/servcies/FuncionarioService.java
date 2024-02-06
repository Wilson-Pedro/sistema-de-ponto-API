package com.wamk.sistemaponto.servcies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wamk.sistemaponto.exceptions.EntityNotFoundException;
import com.wamk.sistemaponto.exceptions.ExistingCepException;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;


@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Transactional
	public Funcionario save(Funcionario funcionario) {
		if (funcionarioRepository.existsByCpf(funcionario.getCpf())) 
			throw new ExistingCepException();
		return funcionarioRepository.save(funcionario);
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Funcionario> findAll(Pageable pageable) {
		return funcionarioRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Funcionario findById(Long id) {
		return funcionarioRepository.findById(id).orElseThrow(() -> 
				new EntityNotFoundException("Funcionario não encontrado."));
	}

	public Funcionario atulizarFuncionario(Long id, Funcionario funcionario) {
		funcionario.setId(id);
		return funcionarioRepository.save(funcionario);
	}

	public void deletarFuncionarioPorId(Long id) {
		funcionarioRepository.deleteById(id);
	}
}
