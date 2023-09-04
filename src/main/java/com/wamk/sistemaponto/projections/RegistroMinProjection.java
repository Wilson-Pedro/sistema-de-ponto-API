package com.wamk.sistemaponto.projections;

public interface RegistroMinProjection {

	Long getId();
	Integer getTipoRegsitro();
	String getDataHora();
	Integer getFrequencia();
	String getNome();
}
