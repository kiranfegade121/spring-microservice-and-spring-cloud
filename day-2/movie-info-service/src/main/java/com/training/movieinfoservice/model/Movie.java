package com.training.movieinfoservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Movie {
	
	@Id
	@Column(name="movie_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int movieId;
	private String title;
	private String imageUrl;
	private int releaseYear;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Cast> casts;
	
}
