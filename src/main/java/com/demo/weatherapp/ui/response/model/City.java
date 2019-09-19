package com.demo.weatherapp.ui.response.model;

import java.io.Serializable;

public class City implements Serializable {

	private static final long serialVersionUID = 19084937837L;

	private String id;
	private String name;
	private CoOrdinate coord;
	private String country;
	private String timezone;
	private String sunrise;
	private String sunset;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CoOrdinate getCoord() {
		return coord;
	}

	public void setCoord(CoOrdinate coord) {
		this.coord = coord;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

}
