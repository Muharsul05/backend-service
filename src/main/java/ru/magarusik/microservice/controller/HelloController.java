package ru.magarusik.microservice.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hello/")
public class HelloController {
    @Timed("sayHello")
    @GetMapping
    public String sayHello() {
        return "Hello, world!!";
    }
}
