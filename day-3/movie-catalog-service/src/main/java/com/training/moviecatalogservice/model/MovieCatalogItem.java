package com.training.moviecatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  @NoArgsConstructor
public class MovieCatalogItem {
	
	private Movie movie;
	private int rating;
}
