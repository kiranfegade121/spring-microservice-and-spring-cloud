package com.training.moviecatalogservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.training.moviecatalogservice.model.Cast;
import com.training.moviecatalogservice.model.Movie;
import com.training.moviecatalogservice.model.Rating;

@Service
public class MovieInfoService {
	
	@Autowired
	private RestTemplate restTemplate;
	 
	@HystrixCommand(fallbackMethod="fetchMovieDetailsFallback", commandProperties= {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="8"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000")
	})
	public Movie fetchMovieDetails(Rating rating) {
		
		Movie movie = restTemplate
				.getForEntity("http://MOVIE-INFO-SERVICE/api/v1/movies/" + rating.getMovieId(), Movie.class)
				.getBody();
		
		return movie;
	}
	
	public Movie fetchMovieDetailsFallback(Rating rating) {
		List<Cast> casts = new ArrayList<>();
		return new Movie(rating.getMovieId(), "No Title", "No URL", 0, casts);
	}
}








