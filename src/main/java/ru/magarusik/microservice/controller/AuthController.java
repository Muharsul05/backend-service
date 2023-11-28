package ru.magarusik.microservice.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.service.UserService;

@RestController
@RequestMapping("/auth")
@Hidden
public class AuthController {
    private final UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/login")
    public UserEntity getAuthUser(Authentication authentication) {
        return service.getUserEntityByName(authentication.getName());
    }
}
