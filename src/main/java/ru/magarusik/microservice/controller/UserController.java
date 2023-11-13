package ru.magarusik.microservice.controller;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.magarusik.microservice.service.UserService;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @Timed("getAllTestDataController")
    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
}
