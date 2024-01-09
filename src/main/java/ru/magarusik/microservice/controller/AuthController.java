package ru.magarusik.microservice.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.magarusik.microservice.dto.UserEntityDTO;
import ru.magarusik.microservice.service.UserService;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Hidden
public class AuthController {
    private final UserService userService;

    @PostMapping(path = "/login")
    public UserEntityDTO getAuthUser(Authentication authentication) {
        return userService
                .getUserEntityByUsername(authentication.getName());
    }
}
