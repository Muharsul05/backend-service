package ru.magarusik.microservice.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.magarusik.microservice.dto.UserEntityDTO;
import ru.magarusik.microservice.exception.UserNotFoundException;
import ru.magarusik.microservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Hidden
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserEntityDTO> getAllUsers() {
        return userService
                .getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public UserEntityDTO getUserEntity(@PathVariable Long id) throws UserNotFoundException {
        var user = userService.getUserEntityById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id: %d not found", id));
        }
        return user;
    }

    @PostMapping("/register")
    public void registerUser(UserEntityDTO userEntityDTO) {
        userService
                .saveUser(userEntityDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException exception) {
        return exception
                .getMessage();
    }
}
