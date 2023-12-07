package ru.magarusik.microservice.utils;

import org.springframework.stereotype.Component;
import ru.magarusik.microservice.dto.PostEntityDto;
import ru.magarusik.microservice.entity.PostEntity;

@Component
public class Converter {
    public static PostEntityDto postEntityToPostEntityDTO(PostEntity postEntity) {
        return PostEntityDto.builder()
                .id(Long.toString(postEntity.getId()))
                .title(postEntity.getTitle())
                .fullText(postEntity.getFullText())
                .date(postEntity.getDate())
                .type(postEntity.getType().getName())
                .build();
    }

    private Converter() {

    }
}
