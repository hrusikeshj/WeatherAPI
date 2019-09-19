package com.demo.weatherapp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.weatherapp.services.GetWeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(MockitoJUnitRunner.class)
public class WeatherInfoControllerTests {
	@InjectMocks
	WeatherInfoController weatherInfoController;
	@Mock
	GetWeatherService getWeatherService;

	@Test
	public void testGetWeatherDetails() {

		String responseJSOn = "{\"City\" : \"Scottsdale\",\"ZipCode\" : \"85254\",\"Details Weather Information\" : [ {\"Date & Time\" : \"2019-09-19 00:00:00\",\"Minimum Temperature\" : \"98.87\",\"Maximum Temperature\" : \"100.83\",\"Humidity\" : \"16\",\"Weather Condition\" : \"clear sky\"  }, {\"Date & Time\" :\"2019-09-19 03:00:00\",\"Minimum Temperature\" :\"96.89\",\"Maximum Temperature\" : \"98.2\",\"Humidity\" : \"17\",\"Weather Condition\" : \"clear sky\"}, {\"Date & Time\" : \"2019-09-19 06:00:00\",\"Minimum Temperature\" : \"92.21\",\"Maximum Temperature\" : \"92.86\",\"Humidity\" : \"21\",\"Weather Condition\" : \"clear sky\" }, {\"Date & Time\" : \"2019-09-19 09:00:00\",\"Minimum Temperature\" : \"89.51\",\"Maximum Temperature\" : \"89.51\",\"Humidity\" : \"23\",\"Weather Condition\" : \"clear sky\"}, {\"Date & Time\" : \"2019-09-19 12:00:00\",\"Minimum Temperature\" : \"87.39\",\"Maximum Temperature\" : \"87.39\",\"Humidity\" : \"26\",\"Weather Condition\" : \"clear sky\" }, {\"Date & Time\" : \"2019-09-19 15:00:00\",\"Minimum Temperature\" : \"88.07\",\"Maximum Temperature\" : \"88.07\",\"Humidity\" : \"26\",\"Weather Condition\" : \"clear sky\" }, {\"Date & Time\" : \"2019-09-19 18:00:00\",\"Minimum Temperature\" : \"94.19\",\"Maximum Temperature\" : \"94.19\",\"Humidity\" : \"23\",\"Weather Condition\" : \"clear sky\"}, {\"Date & Time\" : \"2019-09-19 21:00:00\",\"Minimum Temperature\" : \"99.41\",\"Maximum Temperature\" : \"99.41\",\"Humidity\" : \"16\",\"Weather Condition\" : \"clear sky\" } ]}";
		ResponseEntity<String> expected = new ResponseEntity<String>(
				responseJSOn, HttpStatus.OK);
		try {
			when(getWeatherService.process("85254")).thenReturn(responseJSOn);
			ResponseEntity<String> actual = weatherInfoController
					.getWeatherDetails("85254");

			assertEquals(expected, actual);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
