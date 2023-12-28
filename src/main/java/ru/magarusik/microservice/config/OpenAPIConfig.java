package ru.magarusik.microservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                version = "1.0.0",
                title = "Backend Service API",
                description = "Backend Service for blog",
                contact = @Contact(
                        name = "Magarusik",
                        email = "@Magarusik",
                        url = "https://github.com/Muharsul05"
                )
        )
)
public class OpenAPIConfig {
}
