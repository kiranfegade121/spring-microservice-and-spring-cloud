package com.training.moviecatalogservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.training.moviecatalogservice.model.Movie;

@FeignClient(name="movie-info-service")
public interface MovieInfoServiceProxy {

	@GetMapping("/api/v1/movies/{movieId}")
	public ResponseEntity<Movie> getMovieDetails(@PathVariable int movieId); 
}
