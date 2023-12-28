package ru.magarusik.microservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.magarusik.microservice.entity.PostType;

@AllArgsConstructor
@Getter
public enum PostTypes {
    NEWS(PostType.builder()
            .id(1)
            .name("news").
            build()
    ),
    NOTE(PostType.builder()
            .id(2)
            .name("note")
            .build()
    );
    private final PostType postType;
}
