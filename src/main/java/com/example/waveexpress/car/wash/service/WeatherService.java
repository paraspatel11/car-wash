package com.example.waveexpress.car.wash.service;

import java.io.IOException;
import com.example.waveexpress.car.wash.beans.*;

/**
 * Service interface for retrieving weather data.
 */
public interface WeatherService {
	
	/**
	 * Retrieves weather data for the specified city and country.
	 * 
	 * @param city the name of the city
	 * @param country the ISO code of the country
	 * @return the Weather object containing weather data for the specified location
	 * @throws IOException if an I/O error occurs while retrieving weather data
	 */
	public Weather getWeatherDataCity(String city, String country) throws IOException;
}
