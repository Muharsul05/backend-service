package ru.magarusik.microservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Entity(name = "post_entity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Tag(name = "Пост", description = "Сущность поста")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Идентификатор", example = "123")
    @Min(1)
    private long id;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Schema(name = "date", description = "Дата публикации поста: дд.мм.гггг (28.11.2023)", example = "29.11.2023", pattern = "dd.MM.yyyy")
    @NotBlank
    private Date date;

    @Schema(name = "fullText", description = "Текст поста", example = "Это тестовый пост")
    private String fullText;

    @Schema(name = "title", description = "Заголовок поста", example = "Тестовый пост")
    private String title;

    @Schema(name = "type", description = "Тип поста", example = "type")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private PostType type;
}
