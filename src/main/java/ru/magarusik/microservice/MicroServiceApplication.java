package ru.magarusik.microservice;

import io.micrometer.core.annotation.Timed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroServiceApplication {
    @Timed("Main run")
    public static void main(String[] args) {
        SpringApplication.run(MicroServiceApplication.class, args);
    }
}
