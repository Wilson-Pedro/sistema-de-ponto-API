package com.wamk.sistemaponto.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}
}
