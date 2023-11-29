package ru.magarusik.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.magarusik.microservice.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity getPostEntityById(long id);
}
