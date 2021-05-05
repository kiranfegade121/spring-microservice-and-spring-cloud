package com.training.movieinfoservice.exception;

public class MovieNotFoundException extends RuntimeException {
	
	public MovieNotFoundException() { }
	
	public MovieNotFoundException(String message) {
		super(message);
	}
}
