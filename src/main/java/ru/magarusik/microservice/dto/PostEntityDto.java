package ru.magarusik.microservice.dto;

import lombok.Builder;

import java.util.Date;


@Builder
public record PostEntityDto(long id, Date date, String fullText, String title, String type) {
}