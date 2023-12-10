package ru.magarusik.microservice.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.repository.UserRepository;
import ru.magarusik.microservice.security.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void saveUser(UserEntity userEntity) {
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository
                .findAll();
    }

    public Optional<UserEntity> getUserEntityById(long id) {
        return userRepository.findById(id);
    }

    public UserEntity getUserEntityByName(String name) {
        return userRepository
                .findByName(name)
                .orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = getUserEntityByName(login);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }

        return new User(
                user.getName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                new HashSet<>()
        );
    }
}
