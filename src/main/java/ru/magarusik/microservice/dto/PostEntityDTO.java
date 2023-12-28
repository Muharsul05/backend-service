package ru.magarusik.microservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PostEntityDTO(
        String id,
        @JsonFormat(pattern = "dd-MM-yyyy") LocalDate date,
        String fullText,
        String title,
        String type
) {
}