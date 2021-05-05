package com.training.userratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserRatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRatingServiceApplication.class, args);
	}

}
