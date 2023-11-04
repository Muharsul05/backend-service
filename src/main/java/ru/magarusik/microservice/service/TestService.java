package ru.magarusik.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.magarusik.microservice.entity.Test;
import ru.magarusik.microservice.repository.TestRepository;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository repository;

    public List<Test> getAllTestData(){
        return repository.findAll();
    }

}
