package ru.magarusik.microservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Tag(name = "Пост", description = "Сущность поста")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Идентификатор", example = "123")
    private long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(name = "date",
            description = "Дата публикации поста: дд.мм.гггг (28.11.2023)",
            example = "29.11.2023",
            pattern = "dd.MM.yyyy")
    private LocalDate date;

    @Schema(name = "fullText", description = "Текст поста", example = "Это тестовый пост")
    private String fullText;

    @Schema(name = "title", description = "Заголовок поста", example = "Тестовый пост")
    private String title;

    @Schema(name = "type", description = "Тип поста", example = "news")
    @ManyToOne
    @JoinColumn(name = "type_id",referencedColumnName = "id")
    private PostType type;
}
