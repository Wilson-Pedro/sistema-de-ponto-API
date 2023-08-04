package com.wamk.sistemaponto.servcies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.sistemaponto.dto.FuncionarioDTO;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Transactional
	public void save(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}

	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}

	public Optional<Funcionario> findById(Long id) {
		return funcionarioRepository.findById(id);
	}

	public Funcionario novoFuncionario(FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = new Funcionario();
		BeanUtils.copyProperties(funcionarioDTO, funcionario);
		funcionario.setId(null);
		save(funcionario);
		return funcionario;
	}

	public Funcionario atulizarFuncionario(Long id, FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = findById(id).get();
		BeanUtils.copyProperties(funcionarioDTO, funcionario);
		funcionario.setId(id);
		save(funcionario);
		return funcionario;
	}

	public void deletarFuncionarioPorId(Long id) {
		funcionarioRepository.deleteById(id);
	}
}
