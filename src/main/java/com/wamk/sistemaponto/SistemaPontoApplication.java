package com.wamk.sistemaponto;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wamk.sistemaponto.enums.TipoIdentificacao;
import com.wamk.sistemaponto.model.FolhaPagamento;
import com.wamk.sistemaponto.model.Funcionario;
import com.wamk.sistemaponto.repositories.FolhaPagamentoRepository;
import com.wamk.sistemaponto.repositories.FuncionarioRepository;

@SpringBootApplication
public class SistemaPontoApplication implements CommandLineRunner{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FolhaPagamentoRepository folhaPagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemaPontoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Funcionario func1 = new Funcionario(null, "Wilson Pedro", "931.776.910-10", TipoIdentificacao.CARTAO);
		Funcionario func2 = new Funcionario(null, "Maria Silva",  "382.861.950-94", TipoIdentificacao.RELOGIO);
		Funcionario func3 = new Funcionario(null, "Carlos Almeida",  "382.861.950-94", TipoIdentificacao.SENHA);
		
		funcionarioRepository.saveAll(Arrays.asList(func1, func2, func3));
		
		FolhaPagamento fpg1 = new FolhaPagamento(null, func1, 0, new BigDecimal(0.0));
		FolhaPagamento fpg2 = new FolhaPagamento(null, func2, 0, new BigDecimal(0.0));
		FolhaPagamento fpg3 = new FolhaPagamento(null, func3, 0, new BigDecimal(0.0));
		
		folhaPagamentoRepository.saveAll(Arrays.asList(fpg1, fpg2, fpg3));
	}

}
