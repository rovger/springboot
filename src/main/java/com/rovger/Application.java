package com.rovger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JPA：Java Persistence API(Java持久层API)
 */
@SpringBootApplication
@EntityScan(basePackages = "com.rovger.entity")
@ImportResource("classpath:consumer.xml")
@EnableJpaRepositories(basePackages= "com.rovger.dao.jpa")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
