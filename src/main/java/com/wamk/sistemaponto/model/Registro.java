package com.wamk.sistemaponto.model;

import java.io.Serializable;
import java.util.Objects;

public class Registro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Funcionario funcionario;
	
	public Registro() {
	}

	public Registro(Long id, Funcionario funcionario) {
		this.id = id;
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		return Objects.equals(id, other.id);
	}
}
