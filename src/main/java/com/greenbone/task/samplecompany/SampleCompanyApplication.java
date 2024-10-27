package com.greenbone.task.samplecompany;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sample Application to manage assigned computers in the organization
 */

@OpenAPIDefinition(
		info = @Info(
				title = "Sample Company microservice REST API Documentation",
				description = "Sample Application to manage assigned computers in the organization",
				version = "v1",
				contact = @Contact(
						name = "Mahidhar Gadi",
						email = "g.m.mahidharreddy@gmail.com"
				)
		)
)
@SpringBootApplication
public class SampleCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleCompanyApplication.class, args);
	}

}
