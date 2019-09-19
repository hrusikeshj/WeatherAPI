package com.demo.weatherapp.ui.request.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class DayWeatherResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes="City name of the ZipCode")
	private String city;
	
	@ApiModelProperty(notes="City ZipCode")
	private String zipCode;
	
	@ApiModelProperty(notes="List of weather data for each three hours")
	List<DetailsWeather> detailsWeatherList;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<DetailsWeather> getDetailsWeatherList() {
		return detailsWeatherList;
	}

	public void setDetailsWeatherList(List<DetailsWeather> detailsWeatherList) {
		this.detailsWeatherList = detailsWeatherList;
	}

}
