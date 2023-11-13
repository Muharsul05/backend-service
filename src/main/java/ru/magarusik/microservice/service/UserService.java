package ru.magarusik.microservice.service;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public void saveUser(UserEntity userEntity) {
        userEntity.setPassword(encoder().encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Timed("getAllTestDataService")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
