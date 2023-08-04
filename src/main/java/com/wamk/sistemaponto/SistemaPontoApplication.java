package com.wamk.sistemaponto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;

@SpringBootApplication
public class SistemaPontoApplication implements CommandLineRunner{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemaPontoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Funcionario func1 = new Funcionario(null, "Wilson Pedro", TipoIdentificacao.CARTAO);
		Funcionario func2 = new Funcionario(null, "Maria Silva", TipoIdentificacao.RELOGIO);
		Funcionario func3 = new Funcionario(null, "Carlos Almeida", TipoIdentificacao.SENHA);
		
		funcionarioRepository.saveAll(Arrays.asList(func1, func2, func3));
		
	}

}
