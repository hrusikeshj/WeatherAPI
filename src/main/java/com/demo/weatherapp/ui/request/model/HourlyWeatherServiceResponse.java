package com.demo.weatherapp.ui.request.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class HourlyWeatherServiceResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes="City name of the ZipCode")
	private String city;
	
	@ApiModelProperty(notes="City ZipCode")
	private String zipCode;
	
	@ApiModelProperty(notes="List of weather data for each three hours")
	List<CityHourlyWeatherInfo> detailsWeatherList;

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

	public List<CityHourlyWeatherInfo> getDetailsWeatherList() {
		return detailsWeatherList;
	}

	public void setDetailsWeatherList(List<CityHourlyWeatherInfo> detailsWeatherList) {
		this.detailsWeatherList = detailsWeatherList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime
				* result
				+ ((detailsWeatherList == null) ? 0 : detailsWeatherList
						.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HourlyWeatherServiceResponse other = (HourlyWeatherServiceResponse) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (detailsWeatherList == null) {
			if (other.detailsWeatherList != null)
				return false;
		} else if (!detailsWeatherList.equals(other.detailsWeatherList))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
}
