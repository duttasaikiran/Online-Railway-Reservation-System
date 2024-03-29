package com.RailwayReservationPaymentService.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration File For Implementing Swagger
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.RailwayReservationPaymentService"))
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("User API Documentation", "API for User Payment Microservice", "1.0", "Free to use",
				new springfox.documentation.service.Contact("Duttu", "http://Youtube.com",
						"duttasaikiran@gmail.com"),
				"API Licence", "http://Youtube.com", Collections.emptyList());
	}

}
