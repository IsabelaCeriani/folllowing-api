package com.folllowingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com/folllowingapi/repositories")
public class FolllowingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FolllowingApiApplication.class, args);
	}

}
