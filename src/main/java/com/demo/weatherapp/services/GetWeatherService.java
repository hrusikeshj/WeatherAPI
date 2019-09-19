package com.demo.weatherapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.weatherapp.ui.request.model.DayWeatherResponse;
import com.demo.weatherapp.ui.request.model.DetailsWeather;
import com.demo.weatherapp.ui.response.model.WeatherFinalJSON;
import com.demo.weatherapp.util.WeatherAppUtility;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class GetWeatherService {
	
	Logger logger = LoggerFactory.getLogger(GetWeatherService.class);
	@Value("${external.weatherapi.url}")
	private String apiURL;
	@Value("${external.weatherapiapi.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	public String process(String zipCode) throws JsonProcessingException {
		
	    logger.info("Entered process method ");
		String response = "";
		WeatherFinalJSON weatherFinalJSON = callWeatherAPI(zipCode);
		List<DetailsWeather> detailsWeatherList = weatherFinalJSON
				.getList()
				.stream()
				.filter(v -> v.getDt_txt().contains(
						WeatherAppUtility.getTomorrowDate()))
				.sorted()
				.map(v -> {
					DetailsWeather detailsWeather = new DetailsWeather();

					detailsWeather.setDateTime(v.getDt_txt());
					detailsWeather.setMinTemp(WeatherAppUtility
							.kelvinToFahrenheit(v.getMain().getTemp_min()));
					detailsWeather.setMaxTemp(WeatherAppUtility
							.kelvinToFahrenheit(v.getMain().getTemp_max()));
					detailsWeather.setHumidity(v.getMain().getHumidity());
					detailsWeather.setWeatherDescription(v.getWeather().get(0)
							.getDescription());
					return detailsWeather;
				}).collect(Collectors.toList());

		response = prepareWeatherResponse(zipCode, weatherFinalJSON.getCity()
				.getName(), detailsWeatherList);

		return response;
	}

	public WeatherFinalJSON callWeatherAPI(String zipCode) {
		logger.info("Enetred callWeatherAPI method ");
		String weatherAPIURL = apiURL + "?zip=" + zipCode + ",us&appid="
				+ apiKey;
		return restTemplate.getForObject(weatherAPIURL, WeatherFinalJSON.class);
		
	} 

	public String prepareWeatherResponse(String zipCode, String cityName,
			List<DetailsWeather> detailsWeatherList)
			throws JsonProcessingException {

		logger.info("Enetred prepareWeatherResponse method ");

		DayWeatherResponse dayWeatherResponse = new DayWeatherResponse();
		dayWeatherResponse.setZipCode(zipCode);
		dayWeatherResponse.setCity(cityName);
		dayWeatherResponse.setDetailsWeatherList(detailsWeatherList);
		return WeatherAppUtility.formatJSON(dayWeatherResponse);

	}
}
