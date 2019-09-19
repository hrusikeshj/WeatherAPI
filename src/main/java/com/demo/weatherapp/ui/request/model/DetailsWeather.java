package com.demo.weatherapp.ui.request.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class DetailsWeather implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes="Date & Time")
	private String dateTime;
	@ApiModelProperty(notes="Minimum Temperature")
	private String minTemp;
	@ApiModelProperty(notes="Maximum Temperature")
	private String maxTemp;
	@ApiModelProperty(notes="Humidity")
	private String humidity;
	@ApiModelProperty(notes="Weather Condition")
	private String weatherDescription;
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}
	public String getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getWeatherDescription() {
		return weatherDescription;
	}
	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	
}
