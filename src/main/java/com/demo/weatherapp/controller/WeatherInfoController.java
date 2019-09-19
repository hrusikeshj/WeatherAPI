package com.demo.weatherapp.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.weatherapp.services.WeatherInfoService;
import com.demo.weatherapp.ui.request.model.HourlyWeatherServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/weatherapi")
@ApiOperation(value = "Find temperature by zip code", notes = "It will return coolest 3hrs of the day based on Zip code provided", response = HourlyWeatherServiceResponse.class)
public class WeatherInfoController {

	Logger logger = LoggerFactory.getLogger(WeatherInfoController.class);
	@Autowired
	private WeatherInfoService getWeatherService;

	@GetMapping(path = "/{zipCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HourlyWeatherServiceResponse> getWeatherDetailsByZipCode(
			@ApiParam(value = "ZipCode to look into the temp", required = true) @PathVariable String zipCode)
			throws JsonProcessingException {

		logger.info("Entered getWeatherDetails method, ZipCode enetred "
				+ zipCode);
		HourlyWeatherServiceResponse dayWeatherResponse = getWeatherService
				.process(zipCode);

		return new ResponseEntity<HourlyWeatherServiceResponse>(dayWeatherResponse,
				HttpStatus.OK);

	}

}
