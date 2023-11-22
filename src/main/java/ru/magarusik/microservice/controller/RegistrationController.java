package ru.magarusik.microservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.service.UserService;

@RestController
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private UserService userService;
  
    @PostMapping("/registration")
    public void registration(@RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
    }
}
