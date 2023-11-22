package ru.magarusik.microservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public UserEntity getUserEntity(@PathVariable Long id) throws RuntimeException {
        var user = userService.getUserEntityById(id);

        if (user.isEmpty()) {
            throw new RuntimeException(String.format("User with id: %d not found", id));
        }

        return user.get();
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException exception) {
        return exception.getMessage();
    }
}
