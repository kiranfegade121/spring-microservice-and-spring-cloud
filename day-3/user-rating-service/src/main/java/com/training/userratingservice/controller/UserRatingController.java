package com.training.userratingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.userratingservice.model.UserRating;
import com.training.userratingservice.repository.UserRatingRepository;

@RestController
@RequestMapping("/api/v1")
@RefreshScope
public class UserRatingController {
	
	@Autowired
	private UserRatingRepository userRatingRepository;
	
	@Value("${my.greeting}")
	private String greetingMessage;
	
	@GetMapping("/ratings/{userId}")
	public ResponseEntity<UserRating> getMovieRatings(@PathVariable String userId) {
		
		UserRating ratings = userRatingRepository.findById(userId).get();
		return new ResponseEntity<UserRating>(ratings, HttpStatus.OK);
	}
	
	@PostMapping("/ratings")
	public ResponseEntity<UserRating> saveUserRating(@RequestBody UserRating userRating) {
		UserRating savedUserRating = userRatingRepository.save(userRating);
		return new ResponseEntity<UserRating>(savedUserRating, HttpStatus.CREATED);
	}
	
	@GetMapping("/greeting")
	public String greetingMessage() {
		return greetingMessage;
	}
}







