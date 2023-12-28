package ru.magarusik.microservice.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.magarusik.microservice.dto.UserEntityDTO;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.repository.UserRepository;
import ru.magarusik.microservice.security.PasswordEncoder;
import ru.magarusik.microservice.utils.Converter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static ru.magarusik.microservice.utils.Converter.userEntityToUserEntityDTO;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserEntityDTO userEntityDTO) {
        var userEntity = UserEntity.builder()
                .username(userEntityDTO.username())
                .password(passwordEncoder.encode(userEntityDTO.password()))
                .email(userEntityDTO.email())
                .build();
        userRepository.save(userEntity);
    }

    public List<UserEntityDTO> getAllUsers() {
        return userRepository
                .findAll().stream()
                .map(Converter::userEntityToUserEntityDTO)
                .toList();
    }

    public UserEntityDTO getUserEntityById(long id) {
        return userEntityToUserEntityDTO(
                userRepository.getReferenceById(id)
        );
    }

    public UserEntityDTO getUserEntityByUsername(String username) {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with " + username + " not found");
        }
        return userEntityToUserEntityDTO(user.get());
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = getUserEntityByUsername(login);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }

        return new User(
                user.username(),
                user.password(),
                true,
                true,
                true,
                true,
                new HashSet<>()
        );
    }
}
