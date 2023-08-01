package com.wamk.sistemaponto.enums;

public enum TipoRegistro {

	ENTRADA(1, "Entrada"),
	SAIDA(2, "Saída"),
	INDEFINIDO(3, "Indefinido");
	
	private int cod;
	private String descricao;
	
	private TipoRegistro(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoRegistro toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(TipoRegistro x : TipoRegistro.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
