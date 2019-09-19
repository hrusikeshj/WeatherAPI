package com.demo.weatherapp.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.demo.weatherapp.ui.request.model.DayWeatherResponse;
import com.demo.weatherapp.ui.response.model.WeatherFinalJSON;
import com.demo.weatherapp.util.WeatherAppUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class GetWeatherServiceTest {

	@InjectMocks
	GetWeatherService getWeatherService;
	@Mock
	RestTemplate restTemplate;

	ObjectMapper mapper;

	@Before
	public void setup() {
		mapper = new ObjectMapper();
	}
/*
	@Test
	public void testProcess() {

		try {
			String expected = "{\"city\" : \"Scottsdale\",\"zipCode\" : \"85254\",\"detailsWeatherList\" : [ {\"dateTime\" : \"2019-09-19 00:00:00\",\"minTemp\" : \"98.87\",\"maxTemp\" : \"100.83\",\"humidity\" : \"16\",\"weatherDescription\" : \"clear sky\"  }, {\"dateTime\" :\"2019-09-19 03:00:00\",\"minTemp\" :\"96.89\",\"maxTemp\" : \"98.2\",\"humidity\" : \"17\",\"weatherDescription\" : \"clear sky\"}, {\"dateTime\" : \"2019-09-19 06:00:00\",\"minTemp\" : \"92.21\",\"maxTemp\" : \"92.86\",\"humidity\" : \"21\",\"weatherDescription\" : \"clear sky\" }, {\"dateTime\" : \"2019-09-19 09:00:00\",\"minTemp\" : \"89.51\",\"maxTemp\" : \"89.51\",\"humidity\" : \"23\",\"weatherDescription\" : \"clear sky\"}, {\"dateTime\" : \"2019-09-19 12:00:00\",\"minTemp\" : \"87.39\",\"maxTemp\" : \"87.39\",\"humidity\" : \"26\",\"weatherDescription\" : \"clear sky\" }, {\"dateTime\" : \"2019-09-19 15:00:00\",\"minTemp\" : \"88.07\",\"maxTemp\" : \"88.07\",\"humidity\" : \"26\",\"weatherDescription\" : \"clear sky\" }, {\"dateTime\" : \"2019-09-19 18:00:00\",\"minTemp\" : \"94.19\",\"maxTemp\" : \"94.19\",\"humidity\" : \"23\",\"weatherDescription\" : \"clear sky\"}, {\"dateTime\" : \"2019-09-19 21:00:00\",\"minTemp\" : \"99.41\",\"maxTemp\" : \"99.41\",\"humidity\" : \"16\",\"weatherDescription\" : \"clear sky\" } ]}";
			WeatherFinalJSON weatherFinalJSON = mapper.readValue(expected,
					WeatherFinalJSON.class);
			when(
					restTemplate
							.getForObject(
									"https://api.openweathermap.org/data/2.5/forecast?zip=85254,us&appid=216d5c19d291e72a58338541dbf97a01",
									WeatherFinalJSON.class)).thenReturn(
					weatherFinalJSON);

			String actual = getWeatherService.process("85254");
			assertEquals(expected, actual);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	@Test
	public void testPrepareWeatherResponse() {

		String expected = "{\"city\" : \"Scottsdale\",\"zipCode\" : \"85254\",\"detailsWeatherList\" : [ {\"dateTime\" : \"2019-09-19 00:00:00\",\"minTemp\" : \"98.87\",\"maxTemp\" : \"100.83\",\"humidity\" : \"16\",\"weatherDescription\" : \"clear sky\"  }, {\"dateTime\" :\"2019-09-19 03:00:00\",\"minTemp\" :\"96.89\",\"maxTemp\" : \"98.2\",\"humidity\" : \"17\",\"weatherDescription\" : \"clear sky\"}, {\"dateTime\" : \"2019-09-19 06:00:00\",\"minTemp\" : \"92.21\",\"maxTemp\" : \"92.86\",\"humidity\" : \"21\",\"weatherDescription\" : \"clear sky\" }, {\"dateTime\" : \"2019-09-19 09:00:00\",\"minTemp\" : \"89.51\",\"maxTemp\" : \"89.51\",\"humidity\" : \"23\",\"weatherDescription\" : \"clear sky\"}, {\"dateTime\" : \"2019-09-19 12:00:00\",\"minTemp\" : \"87.39\",\"maxTemp\" : \"87.39\",\"humidity\" : \"26\",\"weatherDescription\" : \"clear sky\" }, {\"dateTime\" : \"2019-09-19 15:00:00\",\"minTemp\" : \"88.07\",\"maxTemp\" : \"88.07\",\"humidity\" : \"26\",\"weatherDescription\" : \"clear sky\" }, {\"dateTime\" : \"2019-09-19 18:00:00\",\"minTemp\" : \"94.19\",\"maxTemp\" : \"94.19\",\"humidity\" : \"23\",\"weatherDescription\" : \"clear sky\"}, {\"dateTime\" : \"2019-09-19 21:00:00\",\"minTemp\" : \"99.41\",\"maxTemp\" : \"99.41\",\"humidity\" : \"16\",\"weatherDescription\" : \"clear sky\" } ]}";
		try {
			
			expected = WeatherAppUtility.formatJSON(expected);
			DayWeatherResponse dayWeatherResponse = mapper.readValue(expected,
					DayWeatherResponse.class);
			assertEquals(expected, getWeatherService.prepareWeatherResponse(
					dayWeatherResponse.getZipCode(), dayWeatherResponse.getCity(),
					dayWeatherResponse.getDetailsWeatherList()));

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
