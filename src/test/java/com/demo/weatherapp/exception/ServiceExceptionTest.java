package com.demo.weatherapp.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.demo.weatherapp.ui.response.model.WeatherFinalJSON;

@RunWith(MockitoJUnitRunner.class)
public class ServiceExceptionTest {
	RestTemplate restTemplate;

	@Before
	public void setup() {
		restTemplate = new RestTemplate();

	}

	@Test(expected = HttpClientErrorException.class)
	public void testDataValidationException() {
		restTemplate
				.getForObject(
						"https://api.openweathermap.org/data/2.5/forecast?zip=854,us&appid=216d5c19d291e72a58338541dbf97a01",
						WeatherFinalJSON.class);

	}

	@Test(expected = Exception.class)
	public void testHandleExcepttion() {
		restTemplate
				.getForObject(
						"https://api.openweathermap.org/data/2.5/forecast?zip=,us&appid=216d5c19d291e72a58338541dbf97a01",
						WeatherFinalJSON.class);

	}
}
