package ru.magarusik.microservice.utils;

import org.springframework.stereotype.Component;
import ru.magarusik.microservice.dto.PostEntityDTO;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.entity.PostType;
import ru.magarusik.microservice.entity.enums.PostTypes;

import java.util.Arrays;

import static ru.magarusik.microservice.entity.enums.PostTypes.*;

@Component
public class Converter {
    public static PostEntityDTO postEntityToPostEntityDTO(PostEntity postEntity) {
        return PostEntityDTO.builder()
                .id(Long.toString(postEntity.getId()))
                .title(postEntity.getTitle())
                .fullText(postEntity.getFullText())
                .date(postEntity.getDate())
                .type(postEntity.getType().getName())
                .build();
    }

    public static PostEntity postEntityDTOToPostEntity(PostEntityDTO postEntityDTO) {
        return PostEntity.builder()
                .id(Long.parseLong(postEntityDTO.id()))
                .title(postEntityDTO.title())
                .fullText(postEntityDTO.fullText())
                .date(postEntityDTO.date())
                .type(convertStringToType(postEntityDTO.type()))
                .build();
    }

    public static PostType convertTypeToPostType(PostTypes type) {
        return switch (type) {
            case NEWS -> new PostType(1, "news");
            case NOTE -> new PostType(2, "note");
        };
    }

    public static PostType convertStringToType(String typeName) {
        var type = Arrays.stream(values())
                .filter(types -> types.name().equalsIgnoreCase(typeName))
                .findFirst();

        return convertTypeToPostType(type.get());
    }

    private Converter() {

    }
}
