package com.training.moviecatalogservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  @NoArgsConstructor
public class MovieCatalog {
	
	private String userId;
	private List<MovieCatalogItem> catalogItems;
}
