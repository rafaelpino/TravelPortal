package com.crossover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = { "com.crossover" })
@Configuration
@EnableAutoConfiguration
public class TravelPortalApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TravelPortalApplication.class);
    }
	
	public static void main(String[] args) {
	SpringApplication.run(TravelPortalApplication.class, args);
	}

}
