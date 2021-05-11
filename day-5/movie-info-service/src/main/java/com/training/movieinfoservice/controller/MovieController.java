package com.training.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.movieinfoservice.exception.MovieNotFoundException;
import com.training.movieinfoservice.model.Movie;
import com.training.movieinfoservice.repository.MovieRepository;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	@Autowired
	private Environment env;

	@Autowired
	private MovieRepository movieRepository;
	
	@GetMapping("/movies/{movieId}")
	public ResponseEntity<Movie> findMovieById(@PathVariable int movieId) {
		
		System.out.println("Port number: " + env.getProperty("local.server.port"));
		Movie movie = movieRepository.findById(movieId)
		                        .orElseThrow(() -> new MovieNotFoundException("Movie Not Found"));
		
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
    @ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<Object> handleMovieNotFoundException(MovieNotFoundException ex) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
    
    @PostMapping("/movies")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
    	Movie savedMovie = movieRepository.save(movie);
    	return new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
    }
	
}









