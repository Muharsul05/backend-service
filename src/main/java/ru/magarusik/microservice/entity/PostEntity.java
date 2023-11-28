package ru.magarusik.microservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity(name = "post_entity")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Tag(name = "Пост", description = "Сущность поста")
public record PostEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Schema(
                name = "ID",
                description = "Идентификатор",
                example = "123"
        ) long id,
        @JsonFormat(pattern = "dd.MM.yyyy")
        @Schema(
                name = "Дата",
                description = "Дата публикации поста: дд.мм.гггг (28.11.2023)"
        ) Date date,
        @Schema(
                name = "Текст",
                description = "Текст поста",
                example = "Это тестовый пост"
        ) String fullText,
        @Schema(
                name = "Заголовок",
                description = "Заголовок поста",
                example = "Тестовый пост"
        ) String title,
        @Schema(
                name = "Тип",
                description = "Тип поста",
                example = "Тестовый пост"
        ) String type
) {

}
