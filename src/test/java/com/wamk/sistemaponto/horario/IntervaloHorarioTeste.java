package com.wamk.sistemaponto.horario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class IntervaloHorarioTeste {

	@Test
	void devePegarOsDiasDoIntervaloEntreAsDatas() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String registroEntrada = "01/10/2023 10:00:00";
		String registroSaida = "04/10/2023 10:00:00";
		
		LocalDateTime dataHora1 = LocalDateTime.parse(registroEntrada, formatter);
		LocalDateTime dataHora2 = LocalDateTime.parse(registroSaida, formatter);
		
		Long dias = IntervaloHorarioCalculo.getDays(dataHora1, dataHora2);
		
		assertEquals(dias, 3);
	}
	
	@Test
	void devePegarAsHorasDoIntervaloEntreAsDatas() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String registroEntrada = "01/10/2023 10:00:00";
		String registroSaida = "04/10/2023 10:00:00";
		
		LocalDateTime dataHora1 = LocalDateTime.parse(registroEntrada, formatter);
		LocalDateTime dataHora2 = LocalDateTime.parse(registroSaida, formatter);
		
		long horas = IntervaloHorarioCalculo.getHours(dataHora1, dataHora2);
		
		assertEquals(horas, 72);
	}
	
	@Test
	void devePegarOsMinutosDoIntervaloEntreAsDatas() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String registroEntrada = "01/10/2023 10:00:00";
		String registroSaida = "01/10/2023 10:30:00";
		
		LocalDateTime dataHora1 = LocalDateTime.parse(registroEntrada, formatter);
		LocalDateTime dataHora2 = LocalDateTime.parse(registroSaida, formatter);
		
		long minutos = IntervaloHorarioCalculo.getMinutes(dataHora1, dataHora2);
		
		assertEquals(minutos, 30);
	}
	
	@Test
	void devePegarOsSegundosDoIntervaloEntreAsDatas() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String registroEntrada = "01/10/2023 10:00:00";
		String registroSaida = "01/10/2023 10:00:10";
		
		LocalDateTime dataHora1 = LocalDateTime.parse(registroEntrada, formatter);
		LocalDateTime dataHora2 = LocalDateTime.parse(registroSaida, formatter);
		
		long secundos = IntervaloHorarioCalculo.getSeconds(dataHora1, dataHora2);
		
		assertEquals(secundos, 10);
	}
}
