package ru.magarusik.microservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {
    private static final String GREETINGS_USER = "Hello, ";

    @GetMapping("/")
    public String helloPrincipal(Principal principal) {
        return "%s%s!!!".formatted(GREETINGS_USER, principal);
    }
}
