package ru.magarusik.microservice.utils;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import ru.magarusik.microservice.dto.PostEntityDTO;
import ru.magarusik.microservice.dto.UserEntityDTO;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.entity.PostType;
import ru.magarusik.microservice.entity.UserEntity;
import ru.magarusik.microservice.entity.enums.PostTypes;

import java.util.Arrays;
import java.util.logging.Level;

import static ru.magarusik.microservice.entity.enums.PostTypes.NEWS;
import static ru.magarusik.microservice.entity.enums.PostTypes.NOTE;

@Component
@Log
public class Converter {
    public static PostEntityDTO postEntityToPostEntityDTO(PostEntity postEntity) {
        var postEntityDTO = PostEntityDTO.builder()
                .id(Long.toString(postEntity.getId()))
                .title(postEntity.getTitle())
                .fullText(postEntity.getFullText())
                .date(postEntity.getDate())
                .type(postEntity.getType().getName())
                .build();
        log.log(Level.INFO, "Convert postEntity to postEntityDTO: " + postEntity + " -> " + postEntityDTO);
        return postEntityDTO;
    }

    public static PostEntity postEntityDTOToPostEntity(PostEntityDTO postEntityDTO) {
        var postEntity = PostEntity.builder()
                .title(postEntityDTO.title())
                .fullText(postEntityDTO.fullText())
                .date(postEntityDTO.date())
                .type(convertStringToType(postEntityDTO.type()))
                .build();
        log.log(Level.INFO, "Convert postEntityDTO to postEntity: " + postEntityDTO + " -> " + postEntity);
        return postEntity;
    }

    public static PostType convertTypeToPostType(PostTypes type) {
        return switch (type) {
            case NEWS -> NEWS.getPostType();
            case NOTE -> NOTE.getPostType();
        };
    }

    public static UserEntityDTO userEntityToUserEntityDTO(UserEntity userEntity) {
        return UserEntityDTO.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .build();
    }

    public static UserEntity userEntityDTOToUserEntity(UserEntityDTO userEntityDTO) {
        return UserEntity.builder()
                .username(userEntityDTO.username())
                .password(userEntityDTO.password())
                .email(userEntityDTO.email())
                .build();
    }

    public static PostType convertStringToType(String typeName) {
        var type = Arrays.stream(PostTypes.values())
                .filter(types -> types
                        .getPostType().getName().equalsIgnoreCase(typeName))
                .findFirst();
        if (type.isEmpty()) {
            throw new IllegalArgumentException("Invalid type: " + typeName);
        }
        return convertTypeToPostType(type.get());
    }

    private Converter() {

    }
}
