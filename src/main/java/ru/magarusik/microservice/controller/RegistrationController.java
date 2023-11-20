package ru.magarusik.microservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.magarusik.microservice.service.UserService;

@RestController
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private UserService userService;

//    @PostMapping("/registration")
//    public void registration(@PathVariable String name,
//                             @PathVariable String email,
//                             @PathVariable String password
//    ) {
//        userService.buildUserEntity(name, email, password);
//    }
}
