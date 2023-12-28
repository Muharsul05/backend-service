package ru.magarusik.microservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "post_types")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private List<PostEntity> posts;
}
