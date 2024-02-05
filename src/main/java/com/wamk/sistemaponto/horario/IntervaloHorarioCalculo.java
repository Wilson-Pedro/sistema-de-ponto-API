package com.wamk.sistemaponto.horario;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IntervaloHorarioCalculo {

	public static String intervalo(LocalDateTime registroEntrada, LocalDateTime registroSaida) {
		
		Duration duracao = Duration.between(registroEntrada, registroSaida);
		
		long horas = duracao.toHours();
		long minutos = duracao.toMinutes() % 60;
		long segundos = duracao.toSeconds() % 60;
		
		String intervalo = horas + ":" + minutos + ":" + segundos;
		
		return intervalo;
	}
	
	public static String intervaloPersonalidado(String registroEntrada, String registroSaida, DateTimeFormatter formatter) {
		
		LocalDateTime dataHora1 = LocalDateTime.parse(registroEntrada, formatter);
		LocalDateTime dataHora2 = LocalDateTime.parse(registroSaida, formatter);
		
		Duration duracao = Duration.between(dataHora1, dataHora2);
		
		long horas = duracao.toHours();
		long minutos = duracao.toMinutes() % 60;
		long segundos = duracao.toSeconds() % 60;
		
		String intervalo = horas + ":" + minutos + ":" + segundos;
		
		return intervalo;
	}
	
	public static Long getDays(LocalDateTime data1, LocalDateTime data2) {
		
		Duration duracao = Duration.between(data1, data2);
	    return Math.abs(duracao.toDays());
	}
	
	public static Long getHours(LocalDateTime data1, LocalDateTime data2) {
		
		Duration duracao = Duration.between(data1, data2);
	    return Math.abs(duracao.toHours());
	}
	
	public static Long getMinutes(LocalDateTime data1, LocalDateTime data2) {
		
		Duration duracao = Duration.between(data1, data2);
	    return Math.abs(duracao.toMinutes());
	}
	
	public static Long getSeconds(LocalDateTime data1, LocalDateTime data2) {
		
		Duration duracao = Duration.between(data1, data2);
	    return Math.abs(duracao.toSeconds());
	}
}
