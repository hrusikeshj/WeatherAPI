package com.demo.weatherapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherAppUtility {

	public static String kelvinToFahrenheit(String kelvin) {

		float celsius = Float.parseFloat(kelvin) - 273.15F;
		double fahrenheit = (9 * celsius / 5) + 32;

		return String.valueOf(Math.round(fahrenheit * 100.0) / 100.0);

	}

	public static String getTomorrowDate() {

		DateTimeFormatter customFormatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		LocalDate nextDay = localDate
				.withDayOfMonth(localDate.getDayOfMonth() + 1);

		return customFormatter.format(nextDay);

	}

	public static String formatJSON(Object jsonObj)
			throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
				jsonObj);

	}

}
