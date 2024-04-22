package com.example.waveexpress.car.wash.dao;

import java.io.*;
import org.springframework.stereotype.Repository;
import okhttp3.*;

/**
 * Implementation of the WeatherDAO interface for retrieving weather data from an external API.
 */
@Repository
public class WeatherDAOImpl implements WeatherDAO {

	/**
	 * Retrieves weather data for the specified city and country from an external API.
	 * 
	 * @param city the name of the city
	 * @param country the ISO code of the country
	 * @return the JSON response containing weather data
	 * @throws IOException if an I/O error occurs while connecting to the API
	 */
	@Override
	public String getWeatherDataCity(String city, String country) throws IOException {
		return connectAPICity(city, country);
	}

	/**
	 * Connects to the weather API to retrieve weather data for the specified city and country.
	 * 
	 * @param city the name of the city
	 * @param country the ISO code of the country
	 * @return the JSON response containing weather data
	 * @throws IOException if an I/O error occurs while connecting to the API
	 */
	private String connectAPICity(String city, String country) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://open-weather13.p.rapidapi.com/city/"+ city +"/EN")
				.get()
				.addHeader("X-RapidAPI-Key", "44331690a8mshfbd50eff9d75c11p15f615jsn59f8da5531df")
				.addHeader("X-RapidAPI-Host", "open-weather13.p.rapidapi.com")
				.build();
		return getResponse(client, request);
	}

	/**
	 * Sends a request to the weather API and retrieves the response body.
	 * 
	 * @param client the OkHttpClient instance
	 * @param request the request to be sent
	 * @return the response body as a String
	 * @throws IOException if an I/O error occurs while executing the request
	 */
	private String getResponse(OkHttpClient client, Request request) throws IOException {
		Response response = client.newCall(request).execute();
		String getResponseBody = response.body().string();
		return getResponseBody;
	}

}
