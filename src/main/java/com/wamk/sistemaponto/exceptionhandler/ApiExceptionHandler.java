package com.wamk.sistemaponto.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wamk.sistemaponto.exceptions.EntityNotFoundException;
import com.wamk.sistemaponto.exceptions.ExistingCepException;
import com.wamk.sistemaponto.exceptions.RegistroSaidaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<Campo> campos = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError)error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Campo(nome, mensagem));
		}
		
		Problema problema = new Problema();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("One or more fileds are invalid! Please fill them correctly.");
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Problema> entityNotFoundException(){
		var status = HttpStatus.NOT_FOUND;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("Entity not found!");
		
		return ResponseEntity.status(status).body(problema);
	}
	
	@ExceptionHandler(RegistroSaidaException.class)
	public ResponseEntity<Problema> registroSaidaException(){
		var status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("is needed register an ENTRANCE before register an EXIT");
		
		return ResponseEntity.status(status).body(problema);
	}
	
	@ExceptionHandler(ExistingCepException.class)
	public ResponseEntity<Problema> existingCepException(){
		var status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo("Existing Cep!");
		
		return ResponseEntity.status(status).body(problema);
	}
}
