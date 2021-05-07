package com.training.userratingservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.training.userratingservice.model.UserRating;

public interface UserRatingRepository extends MongoRepository<UserRating, String> {

}
