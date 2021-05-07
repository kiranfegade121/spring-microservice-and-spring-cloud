package com.training.userratingservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="userrating")
@Data
@AllArgsConstructor  @NoArgsConstructor
public class UserRating {
	
	@Id
	private String userId;
	private List<Rating> ratings;
}
