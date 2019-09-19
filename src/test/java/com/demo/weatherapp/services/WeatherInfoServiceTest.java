package com.demo.weatherapp.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.demo.weatherapp.ui.request.model.HourlyWeatherServiceResponse;
import com.demo.weatherapp.ui.response.model.WeatherFinalJSON;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class WeatherInfoServiceTest {

	@InjectMocks
	WeatherInfoService weatherInfoService;
	@Mock
	RestTemplate restTemplate;
	ObjectMapper mapper;

	@Before
	public void setup() {
		mapper = new ObjectMapper();
	}

	@Test
	public void testProcess() {
		try {
			String apiResponse = "{\"cod\":\"200\",\"message\":\"0.0133\",\"cnt\":\"40\",\"city\":{\"id\":null,\"name\":\"Scottsdale\",\"coord\":{\"lon\":\"-111.9554\",\"lat\":\"33.6165\"},\"country\":\"US\",\"timezone\":\"-25200\",\"sunrise\":\"1568898829\",\"sunset\":\"1568942978\"},  \"list\":[    { \"dt\":\"1568916000\", \"main\":{\"temp\":\"307.68\",\"temp_min\":\"307.1\",\"temp_max\":\"307.68\",\"pressure\":\"1008.77\",\"sea_level\":\"1008.77\",\"grnd_level\":\"963.31\",\"humidity\":\"20\",\"temp_kf\":\"0.58\"},\"weather\":[{\"id\":\"801\",\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"clouds\":{\"all\":\"11\"},\"wind\":{\"speed\":\"3.76\",\"deg\":\"162.82\"},\"rain\":null,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2019-09-19 18:00:00\"}]}";
			WeatherFinalJSON weatherFinalJSON = mapper.readValue(apiResponse,
					WeatherFinalJSON.class);
			when(weatherInfoService.getWeatherInfo("85254")).thenReturn(
					weatherFinalJSON);
			HourlyWeatherServiceResponse actual = weatherInfoService
					.process("85254");

			String expectedAPIResponse = "{\"city\":\"Scottsdale\",\"zipCode\":\"85254\",\"detailsWeatherList\":[]}";
			HourlyWeatherServiceResponse expected = mapper.readValue(
					expectedAPIResponse, HourlyWeatherServiceResponse.class);
			
			assertEquals(expected,actual);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
