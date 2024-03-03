package com.udea.vuelo.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.vuelo.model.Flight;

@Service
public class FlightService {
	//Ruta del archivo
	private final String filepath ="classpath:flight.json";
	
	//Metodo de la logica de busqueda de vuelos
	public List<List<Flight>> searchFlights(LocalDate startDate, LocalDate endDate){
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("flight.json");
			
			if(inputStream != null) {
				Flight[] flights = objectMapper.readValue(inputStream, Flight[].class);
				return 	Arrays.asList(
						Arrays.stream(flights)
						.filter(flight -> isDatateInRange(flight.getDepartureDate(),startDate,endDate))
						.collect(Collectors.toList()));
			}else {
				return null;
			}
		}catch(IOException e) {
			throw new RuntimeException("Error leyendo el archivo Json",e);
		}
	}
	
	
	private boolean isDatateInRange(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate) {
	//verifica si la fecha esta en el rango correcto
	return !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);
		
	}
}
