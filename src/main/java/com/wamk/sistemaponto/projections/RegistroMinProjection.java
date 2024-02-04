package com.wamk.sistemaponto.projections;

public interface RegistroMinProjection {

	Long getId();
	Integer getTipoRegsitro();
	String getDataHora();
	String getFrequencia();
	String getNome();
}
