package com.training.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.training.moviecatalogservice.feignclient.MovieInfoServiceProxy;
import com.training.moviecatalogservice.feignclient.UserRatingServiceProxy;
import com.training.moviecatalogservice.model.Movie;
import com.training.moviecatalogservice.model.MovieCatalog;
import com.training.moviecatalogservice.model.MovieCatalogItem;
import com.training.moviecatalogservice.model.Rating;
import com.training.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/api/v1")
public class MovieCatalogController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MovieInfoServiceProxy movieInfoServiceProxy;
	
	@Autowired
	private UserRatingServiceProxy userRatingServiceProxy;
	

	@GetMapping("/catalog/{userId}")
	public ResponseEntity<MovieCatalog> getMovieCatalog(String userId) {

		// Call user-rating-service to get movie ratings given by user.

		ResponseEntity<UserRating> response = restTemplate
				.getForEntity("http://USER-RATING-SERVICE/api/v1/ratings/alexB", UserRating.class);
		UserRating userRating = response.getBody();
		List<Rating> ratings = userRating.getRatings();

		// Prepare MovieCatalog
		MovieCatalog catalog = new MovieCatalog();
		catalog.setUserId("alexB");

		List<MovieCatalogItem> catalogItems = new ArrayList<>();

		// Call movie-info-service to get movie details

		for (Rating rating : ratings) {

			Movie movie = restTemplate
					.getForEntity("http://MOVIE-INFO-SERVICE/api/v1/movies/" + rating.getMovieId(), Movie.class)
					.getBody();
			catalogItems.add(new MovieCatalogItem(movie, rating.getRating()));
		}

		catalog.setCatalogItems(catalogItems);
		return new ResponseEntity<MovieCatalog>(catalog, HttpStatus.OK);
	}
	
	@GetMapping("/catalog-feign/{userId}")
	public ResponseEntity<MovieCatalog> getMovieCatalogUsingFeignClient(String userId) {

		// Call user-rating-service to get movie ratings given by user.

		UserRating userRating = userRatingServiceProxy.getMovieRatings("alexB").getBody();
		List<Rating> ratings = userRating.getRatings();

		// Prepare MovieCatalog
		MovieCatalog catalog = new MovieCatalog();
		catalog.setUserId("alexB");

		List<MovieCatalogItem> catalogItems = new ArrayList<>();

		// Call movie-info-service to get movie details

		for (Rating rating : ratings) {

			Movie movie = movieInfoServiceProxy.getMovieDetails(rating.getMovieId()).getBody();
			catalogItems.add(new MovieCatalogItem(movie, rating.getRating()));
		}

		catalog.setCatalogItems(catalogItems);
		return new ResponseEntity<MovieCatalog>(catalog, HttpStatus.OK);
	}
}
