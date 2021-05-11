package com.training.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.training.moviecatalogservice.model.Rating;
import com.training.moviecatalogservice.model.UserRating;

@Service
public class UserRatingService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getUserRatingsFallback", commandProperties= {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="8"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000")
	})
	public UserRating getUserRatings(String userId) {
		
		ResponseEntity<UserRating> response = restTemplate
				.getForEntity("http://USER-RATING-SERVICE/api/v1/ratings/alexB", UserRating.class);
		UserRating userRating = response.getBody();
		return userRating;
	}
	
	public UserRating getUserRatingsFallback(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setRatings(Arrays.asList(new Rating(0, 0)));
		return userRating;
	}
	
	
//	public int getMoieCounts(String userId) {
//		int count = restTemplate.getForEntity("http://USER-RATING-SERVICE/api/v1/movies/" + userId + "/count", Integer.class).getBody();
//		return count;
//	}
}	







