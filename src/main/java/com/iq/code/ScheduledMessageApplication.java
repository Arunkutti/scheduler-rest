package com.iq.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Root class to start scheduler application
 * @author arunkumar.pushparaj
 * 
 */
@EnableJpaRepositories(basePackages = "com.iq.code.repository")
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.iq.code" })
public class ScheduledMessageApplication {

	/**
	 * Main method to execute the scheduler application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ScheduledMessageApplication.class, args);
	}

	/**
	 * Bean to create swagger definition settings.
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build();
	}

}
