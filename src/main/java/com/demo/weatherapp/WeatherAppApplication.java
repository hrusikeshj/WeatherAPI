package com.demo.weatherapp;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
public class WeatherAppApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();

	}

	@Bean
	public Docket getSwaggerDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/weatherapi/*"))
				.apis(RequestHandlerSelectors
						.basePackage("com.demo.weatherapp")).build()
				.apiInfo(getSwaggerInfo());

	}

	public ApiInfo getSwaggerInfo() {
		return new ApiInfo("Weather Prediction API",
				"Predict tomorrow coolest hours", "1.0", "Free Version",
				new springfox.documentation.service.Contact("Hrusikesh Jena",
						"", "example@example.com"), "API License", "",
				Collections.emptyList());

	}

	public static void main(String[] args) {

		SpringApplication.run(WeatherAppApplication.class, args);
	}

}
