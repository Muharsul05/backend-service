package ru.magarusik.microservice.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Timed("main")
    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
