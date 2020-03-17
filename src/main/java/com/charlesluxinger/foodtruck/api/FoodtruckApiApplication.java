package com.charlesluxinger.foodtruck.api;

import com.charlesluxinger.foodtruck.api.domain.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class FoodtruckApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodtruckApiApplication.class, args);
	}

}
