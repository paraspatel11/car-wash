package com.example.waveexpress.car.wash.dao;

import java.io.IOException;

/**
 * Data Access Object interface for retrieving weather data.
 */
public interface WeatherDAO {
	
	/**
	 * Retrieves weather data for the specified city and country.
	 * 
	 * @param city the name of the city
	 * @param country the ISO code of the country
	 * @return the JSON response containing weather data
	 * @throws IOException if an I/O error occurs while retrieving weather data
	 */
	public String getWeatherDataCity(String city, String country) throws IOException;
}
