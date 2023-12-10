package ru.magarusik.microservice.utils;

import org.springframework.stereotype.Component;
import ru.magarusik.microservice.dto.PostEntityDTO;
import ru.magarusik.microservice.entity.PostEntity;
import ru.magarusik.microservice.entity.PostType;

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
                .type(convertStringTypeNameToPostType(postEntityDTO.type()))
                .build();
    }

    public static PostType convertStringTypeNameToPostType(String name) {
        return switch (name) {
            case "news" -> new PostType(1, "news");
            case "note" -> new PostType(2, "note");
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }

    private Converter() {

    }
}
