package ru.magarusik.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public UserEntity getUserEntity(@PathVariable Long id) throws RuntimeException {
        var user = userService.getUserEntityById(id);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with id: %d not found", id));
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
