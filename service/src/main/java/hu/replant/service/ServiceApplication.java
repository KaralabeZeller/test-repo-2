package hu.replant.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"hu.replant.primary.controller",
		"hu.replant.primary.service",
		"hu.replant.primary.configuration"})
@EnableJpaRepositories(basePackages = "hu.replant.secondary.persistence.repository")
@EntityScan(basePackages = "hu.replant.secondary.persistence.entity")
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}
