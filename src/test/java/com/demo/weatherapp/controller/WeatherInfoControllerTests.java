package com.demo.weatherapp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.weatherapp.services.WeatherInfoService;
import com.demo.weatherapp.ui.request.model.HourlyWeatherServiceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class WeatherInfoControllerTests {
	@InjectMocks
	WeatherInfoController weatherInfoController;
	@Mock
	WeatherInfoService getWeatherService;

	@Test
	public void testgetWeatherDetailsByZipCode() throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();		
		 String responseJSOn = "{\"city\" : \"Scottsdale\",\"zipCode\" : \"85254\",\"detailsWeatherList\" : [ {\"dateTime\" : \"2019-09-19 00:00:00\",\"minTemp\" : \"98.87\",\"maxTemp\" : \"100.83\",\"humidity\" : \"16\",\"weatherDescription\" : \"clear sky\"  }, {\"dateTime\" :\"2019-09-19 03:00:00\",\"minTemp\" :\"96.89\",\"maxTemp\" : \"98.2\",\"humidity\" : \"17\",\"weatherDescription\" : \"clear sky\"}, {\"dateTime\" : \"2019-09-19 06:00:00\",\"minTemp\" : \"92.21\",\"maxTemp\" : \"92.86\",\"humidity\" : \"21\",\"weatherDescription\" : \"clear sky\" }, {\"dateTime\" : \"2019-09-19 09:00:00\",\"minTemp\" : \"89.51\",\"maxTemp\" : \"89.51\",\"humidity\" : \"23\",\"weatherDescription\" : \"clear sky\"}, {\"dateTime\" : \"2019-09-19 12:00:00\",\"minTemp\" : \"87.39\",\"maxTemp\" : \"87.39\",\"humidity\" : \"26\",\"weatherDescription\" : \"clear sky\" }, {\"dateTime\" : \"2019-09-19 15:00:00\",\"minTemp\" : \"88.07\",\"maxTemp\" : \"88.07\",\"humidity\" : \"26\",\"weatherDescription\" : \"clear sky\" }, {\"dateTime\" : \"2019-09-19 18:00:00\",\"minTemp\" : \"94.19\",\"maxTemp\" : \"94.19\",\"humidity\" : \"23\",\"weatherDescription\" : \"clear sky\"}, {\"dateTime\" : \"2019-09-19 21:00:00\",\"minTemp\" : \"99.41\",\"maxTemp\" : \"99.41\",\"humidity\" : \"16\",\"weatherDescription\" : \"clear sky\" } ]}"; 

		HourlyWeatherServiceResponse dayWeatherResponse = objectMapper.readValue(responseJSOn, HourlyWeatherServiceResponse.class);
		ResponseEntity<HourlyWeatherServiceResponse> expected = new ResponseEntity<HourlyWeatherServiceResponse>(
				dayWeatherResponse, HttpStatus.OK);

		when(getWeatherService.process("85254")).thenReturn(dayWeatherResponse);
		ResponseEntity<HourlyWeatherServiceResponse> actual = weatherInfoController.getWeatherDetailsByZipCode(dayWeatherResponse.getZipCode());

		assertEquals(expected, actual);
	}

}
