package com.rovger.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JPA：Java Persistence API(Java持久层API)
 */
@SpringBootApplication
@EntityScan(basePackages = "com.rovger.mybatis.entity")
@EnableJpaRepositories(basePackages= "com.rovger.mybatis.dao")
public class JPAApplication {

	public static void main(String[] args) {
		SpringApplication.run(JPAApplication.class, args);
	}
}
