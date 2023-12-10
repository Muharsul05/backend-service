package ru.magarusik.microservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostTypes {
    NEWS(1, "news"),
    NOTE(2, "note");

    private final long id;
    private final String name;
}
