package com.wamk.sistemaponto.enums;

import java.util.stream.Stream;

public enum TipoIdentificacao {

	RELOGIO(1, "Relógio"),
	CARTAO(2, "Cartão"),
	SENHA(3, "Senha"),
	BIOMETRIA(4, "Biometria");
	
	private int cod;
	private String descricao;
	
	private TipoIdentificacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoIdentificacao toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoIdentificacao x : TipoIdentificacao.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
	public static TipoIdentificacao toEnum(String descricao) {
		return Stream.of(TipoIdentificacao.values())
				.filter(tipo -> tipo.getDescricao().equals(descricao))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
