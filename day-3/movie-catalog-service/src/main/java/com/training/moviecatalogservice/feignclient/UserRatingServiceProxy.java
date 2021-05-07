package com.training.moviecatalogservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.training.moviecatalogservice.model.UserRating;

@FeignClient(name="user-rating-service")
public interface UserRatingServiceProxy {
	
	@GetMapping("api/v1/ratings/{userId}")
	public ResponseEntity<UserRating> getMovieRatings(@PathVariable String userId);	
		
}
