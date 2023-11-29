package ru.magarusik.microservice.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.service.UserService;

@RestController
@AllArgsConstructor
@Hidden
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/registration")
    public void registration(@RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
    }
}
