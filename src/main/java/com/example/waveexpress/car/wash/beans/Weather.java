package com.example.waveexpress.car.wash.beans;

import java.math.*;
import java.text.DecimalFormat;

import lombok.*;

/**
 * Model class representing weather data.
 */
@Getter
@Setter
@AllArgsConstructor
public class Weather {
	
	private DecimalFormat df; // Decimal format for rounding temperature values
	
	private String city; // Name of the city
	private String country; // Name of the country
	private String countryISOCode; // ISO code of the country
	private double timeZone; // Time zone offset
	private double temperature; // Current temperature
	private String weather; // General weather condition
	private String weatherDesc; // Detailed weather description
	private double tempFeelsLike; // Temperature feels like
	private double tempMin; // Minimum temperature
	private double tempMax; // Maximum temperature
	private double pressure; // Atmospheric pressure
	private double humidity; // Humidity level

	/**
	 * Default constructor.
	 * Initializes the decimal format for temperature rounding.
	 */
	public Weather() {
		this.df = new DecimalFormat("#.00");
		this.df.setRoundingMode(RoundingMode.CEILING);
	}
}
