package ru.magarusik.microservice.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.exception.UserEntityNotFoundException;
import ru.magarusik.microservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Hidden
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public UserEntity getUserEntity(@PathVariable Long id) throws UserEntityNotFoundException {
        var user = userService.getUserEntityById(id);

        if (user.isEmpty()) {
            throw new UserEntityNotFoundException(String.format("User with id: %d not found", id));
        }

        return user.get();
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException exception) {
        return exception.getMessage();
    }
}
