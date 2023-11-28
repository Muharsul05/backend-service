package ru.magarusik.microservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Backend Service API",
                description = "Backend Service for blog", version = "1.0.0",
                contact = @Contact(
                        name = "Magarusik",
                        email = "@Magarusik"
                )
        )
)
public class OpenAPIConfig {
}
