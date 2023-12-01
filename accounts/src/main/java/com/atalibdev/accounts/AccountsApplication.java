package com.atalibdev.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Developing a microservice REST API for account",
				version = "v1",
				contact = @Contact(
						name = "Almousleck Atalib Ag",
						email = "microservicestech97@gmail.com",
						url = "https://www.atalibdev.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.atalibdev.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Account microservices REST API Documentation",
				url = "https://www.atalibdev.com/swagger-ui.html"
		)
) // Cloud native application
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
