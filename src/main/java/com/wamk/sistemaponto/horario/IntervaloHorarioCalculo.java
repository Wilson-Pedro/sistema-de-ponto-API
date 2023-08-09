package com.wamk.sistemaponto.horario;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IntervaloHorarioCalculo {

	public static String intervalo(String registroEntrada, String registroSaida) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dataHora1 = LocalDateTime.parse(registroEntrada, formatter);
		LocalDateTime dataHora2 = LocalDateTime.parse(registroSaida, formatter);
		
		Duration duracao = Duration.between(dataHora1, dataHora2);
		
		long horas = duracao.toHours();
		long minutos = duracao.toMinutes() % 60;
		long segundos = duracao.toSeconds() % 60;
		
		String intervalo = horas + ":" + minutos + ":" + segundos;
		
		return intervalo;
	}
}
