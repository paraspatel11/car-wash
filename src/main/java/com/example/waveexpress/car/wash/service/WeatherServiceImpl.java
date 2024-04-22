package com.example.waveexpress.car.wash.service;

import java.io.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.waveexpress.car.wash.dao.WeatherDAO;
import com.example.waveexpress.car.wash.beans.*;;

/**
 * Implementation of the WeatherService interface for retrieving weather data.
 */
@Service
public class WeatherServiceImpl implements WeatherService{
	
	@Autowired
	WeatherDAO wDAO;
	
	private String json;
	private Weather weather;
	
	/**
	 * Retrieves weather data for the specified city and country.
	 * 
	 * @param city the name of the city
	 * @param country the ISO code of the country
	 * @return the Weather object containing weather data for the specified location
	 * @throws IOException if an I/O error occurs while retrieving weather data
	 */
	@Override
	public Weather getWeatherDataCity(String city, String country) throws IOException {
		return jsonParseCityWeather(city, country);
	}
	
	/**
	 * Parses the JSON response from the weather API to retrieve weather data for the specified city and country.
	 * 
	 * @param city the name of the city
	 * @param country the ISO code of the country
	 * @return the Weather object containing weather data for the specified location
	 * @throws IOException if an I/O error occurs while parsing the JSON response
	 */
	private Weather jsonParseCityWeather(String city, String country) throws IOException {
		this.json = this.wDAO.getWeatherDataCity(city, country);
		setWeatherParameters();
		return this.weather;
	}
	
	/**
	 * Parses the JSON object to extract weather parameters and creates a Weather object.
	 */
	private void setWeatherParameters() {
		try {
			JSONObject obj = new JSONObject(this.json);
			String name = obj.getString("name").toString();
			String country = obj.getJSONObject("sys").getString("country");
			double humidity = obj.getJSONObject("main").getInt("humidity");
			double pressure = obj.getJSONObject("main").getInt("pressure");
			double temperature = obj.getJSONObject("main").getDouble("temp");
			double tempFeelsLike = obj.getJSONObject("main").getDouble("feels_like");
			double tempMax = obj.getJSONObject("main").getDouble("temp_max");
			double tempMin = obj.getJSONObject("main").getDouble("temp_min");
			double timeZone = obj.getDouble("timezone");
			String weather = obj.getJSONArray("weather").getJSONObject(0).getString("main");
			String weatherDesc = obj.getJSONArray("weather").getJSONObject(0).getString("description");
			this.weather = new Weather();
			this.weather.setCity(name);
			this.weather.setCountryISOCode(country);
			this.weather.setHumidity(humidity);
			this.weather.setPressure(pressure);
			this.weather.setTemperature(temperature);
			this.weather.setTempFeelsLike(tempFeelsLike);
			this.weather.setTempMax(tempMax);
			this.weather.setTempMin(tempMin);
			this.weather.setTimeZone(timeZone);
			this.weather.setWeather(weather);
			this.weather.setWeatherDesc(weatherDesc);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
