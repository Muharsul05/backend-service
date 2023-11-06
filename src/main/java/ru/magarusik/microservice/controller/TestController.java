package ru.magarusik.microservice.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.magarusik.microservice.entity.Test;
import ru.magarusik.microservice.service.TestService;

import java.util.List;

@RestController
@RequestMapping(value = "test/")
public class TestController {
    @Autowired
    private TestService service;

    @Timed("getAllTestDataController")
    @GetMapping
    public List<Test> getAllTestData() {
        return service.getAllTestData();
    }
}
