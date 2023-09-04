package com.wamk.sistemaponto.servcies;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wamk.sistemaponto.dtos.FuncionarioDTO;
import com.wamk.sistemaponto.dtos.inputs.FuncionarioInputDTO;
import com.wamk.sistemaponto.exceptions.EntidadeNaoEncontradaException;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;


@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Transactional
	public void save(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}
	
	@Transactional(readOnly = true)
	public List<FuncionarioDTO> findAll() {
		List<Funcionario> list = funcionarioRepository.findAll();
		return list.stream().map(x -> new FuncionarioDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public Page<Funcionario> findAll(Pageable pageable) {
		return funcionarioRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Funcionario findById(Long id) {
		return funcionarioRepository.findById(id)
				.orElseThrow(() -> 
				new EntidadeNaoEncontradaException("Funcionario não encontrado."));
	}

	public Funcionario novoFuncionario(FuncionarioInputDTO funcionarioDTO) {
		Funcionario funcionario = new Funcionario();
		BeanUtils.copyProperties(funcionarioDTO, funcionario);
		funcionario.setId(null);
		save(funcionario);
		return funcionario;
	}

	public FuncionarioDTO atulizarFuncionario(Long id, FuncionarioInputDTO funcionarioDTO) {
		Funcionario funcionario = findById(id);
		BeanUtils.copyProperties(funcionarioDTO, funcionario);
		funcionario.setId(id);
		save(funcionario);
		var obj = new FuncionarioDTO();
		BeanUtils.copyProperties(funcionario, obj);
		return obj;
	}

	public void deletarFuncionarioPorId(Long id) {
		funcionarioRepository.deleteById(id);
	}
}
