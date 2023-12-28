package ru.magarusik.microservice.dto;

import lombok.Builder;

@Builder
public record UserEntityDTO(
        String username,
        String email,
        String password
) {
}
