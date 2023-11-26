package ru.magarusik.microservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "post_entity")
@Data
@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date")
    private Date date;

    @Column(name = "full_text")
    private String fullText;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;
}
