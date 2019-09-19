package com.demo.weatherapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.weatherapp.ui.request.model.CityHourlyWeatherInfo;
import com.demo.weatherapp.ui.request.model.HourlyWeatherServiceResponse;
import com.demo.weatherapp.ui.response.model.WeatherDetails;
import com.demo.weatherapp.ui.response.model.WeatherFinalJSON;
import com.demo.weatherapp.util.WeatherAppUtility;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class WeatherInfoService {

	Logger logger = LoggerFactory.getLogger(WeatherInfoService.class);
	@Value("${external.weatherapi.url}")
	private String apiURL;
	@Value("${external.weatherapiapi.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	public HourlyWeatherServiceResponse process(String zipCode)
			throws JsonProcessingException {

		logger.info("Entered process method ");
		WeatherFinalJSON weatherFinalJSON = getWeatherInfo(zipCode);
		List<WeatherDetails> weatherDetailsList = weatherFinalJSON
				.getList()
				.stream()
				.filter(v -> v.getDt_txt().contains(WeatherAppUtility.dateFormatter())).sorted()
				.collect(Collectors.toList());

		List<CityHourlyWeatherInfo> cityHourlyWeatherInfoList = weatherDetailsList
				.stream()
				.map(v -> {
					CityHourlyWeatherInfo detailsWeather = new CityHourlyWeatherInfo();
					detailsWeather.setDateTime(v.getDt_txt());
					detailsWeather.setMinTemp(WeatherAppUtility.parseKelvin(v.getMain().getTemp_min()));
					detailsWeather.setMaxTemp(WeatherAppUtility.parseKelvin(v.getMain().getTemp_max()));
					detailsWeather.setHumidity(v.getMain().getHumidity());
					detailsWeather.setWeatherDescription(v.getWeather().get(0).getDescription());
					return detailsWeather;
				}).collect(Collectors.toList());
		
		HourlyWeatherServiceResponse hourlyWeatherServiceResponse = new HourlyWeatherServiceResponse();
		hourlyWeatherServiceResponse.setZipCode(zipCode);
		hourlyWeatherServiceResponse.setCity(weatherFinalJSON.getCity().getName());
		hourlyWeatherServiceResponse.setDetailsWeatherList(cityHourlyWeatherInfoList);
		return hourlyWeatherServiceResponse;
	}

	public WeatherFinalJSON getWeatherInfo(String zipCode) {
		logger.info("Enetred callWeatherAPI method ");
		String serviceURL = apiURL + "?zip=" + zipCode + ",us&appid="+ apiKey;
		return restTemplate.getForObject(serviceURL, WeatherFinalJSON.class);
	}
}
