package com.iq.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories(basePackages = "com.iq.code.repository")
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.iq.code"})
public class ScheduledMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduledMessageApplication.class, args);
	}

}
